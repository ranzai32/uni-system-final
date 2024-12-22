package services;

import database.*;
import entities.*;
import enums.*;
import users.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class RegistrationTest {
    public static void main(String[] args) {
        DataBase db = DataBase.getInstance();

        // Создаем студента
        Student student = new Student(
                "S001", "password123", "Иван", "Иванов", 20, false,
                Degree.Bachelor, Faculty.FIT, 2, 0.0, new ArrayList<>(),
                null, LocalDate.of(2025, 6, 30), new ArrayList<>(), new HashMap<>()
        );
        db.addStudent(student);

        // Создаем курс
        Course course = new Course("CS101", "Программирование", 5, TypeCourse.Required, Faculty.FIT, 50);
        db.addCourse(course);

        // Создаем менеджера
        Manager manager = new Manager(
                "M001", new ArrayList<>(), false, true, 35, "Петров", "Алексей",
                "manager123", LocalDate.of(2015, 1, 15), new ArrayList<>(), TypeManager.OR
        );

        // Студент подает заявку
        db.submitRegistrationRequest(student.getId(), course.getCourseCode());

        // Менеджер обрабатывает заявку
        RegistrationRequest request = db.getRegistrationRequests().get(0); // Берем первую заявку
        manager.processRegistrationRequest(request, true); // Одобряем заявку

        // Проверяем, добавился ли курс студенту
        System.out.println("\nКурсы студента после обработки заявки:");
        for (Course c : student.getCourses()) {
            System.out.println("- " + c.getCourseName() + " (" + c.getCourseCode() + ")");
        }

        db.saveAllListsToFiles();
    }
}

