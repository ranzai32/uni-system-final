package UniversitySystem;

import UniversitySystem.Admin;
import UniversitySystem.DataBase;

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

        // Создание преподавателей
        Teacher teacher1 = new Teacher(
                "T001",
                null,
                false, // isResearcher
                true,  // status (активен)
                45,    // age
                "Петров",
                "Иван",
                "teachPass1",
                LocalDate.of(2010, 9, 1),
                null,
                TypeTeacher.Tutor,
                4.5,    // rate
                null,
                Faculty.FIT
        );

        // Добавление преподавателей в DataBase
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

        Course course3 = new Course(
                "MATH201",
                "Дифференциальные уравнения",
                3,
                TypeCourse.Required,
                Faculty.FIT,
                30
        );

        // Добавление курсов в DataBase
        db.addCourse(course1);
        db.addCourse(course2);
        db.addCourse(course3);

        // Создание организации
        Organization org = new Organization("Технологический Клуб", student1);
        System.out.println("Организация '" + org.getOrgName() + "' создана с главой " + org.getHead().getFirstName() + " " + org.getHead().getLastName());

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

        // Просмотр транскриптов студентов
        System.out.println("\nТранскрипты студентов:");
        managerViewStudentTranscript(student1);
        managerViewStudentTranscript(student2);
    }

    private static void managerViewStudentTranscript(Student student) {
        Transcript transcript = student.getTranscript();
        if (transcript == null) {
            System.out.println("Транскрипт студента " + student.getFirstName() + " " + student.getLastName() + " недоступен.");
        } else {
            System.out.println("Транскрипт студента " + student.getFirstName() + " " + student.getLastName() + ":");
            System.out.println(transcript);
        }
    }
}

