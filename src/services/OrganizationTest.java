package services;

import database.*;
import enums.Degree;
import enums.Faculty;
import entities.*;
import enums.TypeCourse;
import enums.TypeTeacher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class OrganizationTest {
    public static void main(String[] args) {
        // Инициализация DataBase и Admin
        DataBase db = DataBase.getInstance();
        Admin admin = Admin.getInstance();

        // Создание студентов
        Student student1 = new Student(
                "S001",
                "password123",
                "Мария",
                "Иванова",
                20,
                false,
                Degree.Bachelor,
                Faculty.FIT,
                2,
                3.8,
                new ArrayList<>(),
                new Transcript(),
                LocalDate.of(2024, 6, 30),
                new ArrayList<>(),
                new HashMap<>()
        );

        Student student2 = new Student(
                "S002",
                "password456",
                "Алекс",
                "Смирнов",
                21,
                false,
                Degree.Bachelor,
                Faculty.FIT,
                3,
                3.5,
                new ArrayList<>(),
                new Transcript(),
                LocalDate.of(2025, 6, 30),
                new ArrayList<>(),
                new HashMap<>()
        );

        // Добавление студентов в DataBase
        db.addStudent(student1);
        db.addStudent(student2);

        // Создание преподавателя
        Teacher teacher1 = new Teacher(
                "T001",
                new ArrayList<>(), // Пустой список сообщений
                false, // isResearcher
                true,  // status (активен)
                45,    // age
                "Петров",
                "Иван",
                "teachPass1",
                LocalDate.of(2010, 9, 1),
                new ArrayList<>(), // Пустой список заказов
                TypeTeacher.Tutor,
                4.5,    // rate
                new HashMap<>(), // Пустое расписание
                Faculty.FIT
        );

        // Добавление преподавателя в DataBase
        db.addTeacher(teacher1);

        // Создание курсов
        Course course1 = new Course(
                "CS101",
                "Введение в программирование",
                3,
                TypeCourse.Required,
                Faculty.FIT,
                50
        );

        Course course2 = new Course(
                "CS102",
                "Алгоритмы и структуры данных",
                4,
                TypeCourse.Required,
                Faculty.FIT,
                40
        );

        // Назначение преподавателей на курсы
        course1.addTeacher(teacher1);
        course2.addTeacher(teacher1);

        // Добавление курсов в DataBase
        db.addCourse(course1);
        db.addCourse(course2);

        // Назначение курсов студентам
        db.assignCourseToStudent(student1.getId(), course1.getCourseCode());
        db.assignCourseToStudent(student2.getId(), course2.getCourseCode());

        // Добавление оценки студенту
        System.out.println("\nДобавляем оценку студенту Мария Иванова:");
        teacher1.addGrade(student1.getId(), course1, 50.0);
        teacher1.addGrade(student2.getId(), course2, 78.0);

        // Показ транскриптов студентов
        System.out.println("\nТранскрипты студентов:");
        teacher1.getTranscript(student1); // !!!!!

        // Показ исходной функциональности (организации)
        // Создание организации
        Organization org = new Organization("Технологический Клуб", student1);
        System.out.println("\nОрганизация '" + org.getOrgName() + "' создана с главой " + org.getHead().getFirstName() + " " + org.getHead().getLastName());
        db.addOrganization(org);

        // Добавление члена в организацию
        boolean addMember = org.addMember(student2);
        if (addMember) {
            System.out.println("Студент " + student2.getFirstName() + " добавлен в организацию '" + org.getOrgName() + "'");
        }

        // Просмотр членов организации
        System.out.println("\nЧлены организации '" + org.getOrgName() + "':");
        for (Student s : org.getMembers()) {
            System.out.println("- " + s.getFirstName() + " " + s.getLastName());
        }

        // Переопределение главы организации
        org.setHead(student2);
        System.out.println("\nГлава организации изменен на " + org.getHead().getFirstName() + " " + org.getHead().getLastName());

        // Просмотр обновленной информации об организации
        System.out.println("\nИнформация об организации:");
        System.out.println(org);

        db.saveAllListsToFiles();
    }


}
