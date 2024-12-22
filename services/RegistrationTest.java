package UniversitySystem.services;
import UniversitySystem.DataBase;
import UniversitySystem.entities.*;
import UniversitySystem.enums.Degree;
import UniversitySystem.enums.Faculty;
import UniversitySystem.enums.TypeManager;
import UniversitySystem.enums.TypeCourse;
import UniversitySystem.users.Manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class RegistrationTest {
    public static void main(String[] args) {
        DataBase db = DataBase.getInstance();

        // Создаём студента
        Student student = new Student(
                "S001",
                "password123",
                "Иван",
                "Иванов",
                20,
                false,
                Degree.Bachelor,
                Faculty.FIT,
                2,
                30, // Максимальные кредиты
                new ArrayList<>(),
                new Transcript(),
                LocalDate.of(2024, 6, 30),
                new ArrayList<>(),
                new HashMap<>()
        );
        db.addStudent(student);

        // Создаём курс
        Course course = new Course(
                "CS101",
                "Программирование",
                5, // Кредиты курса
                TypeCourse.Required,
                Faculty.FIT,
                50
        );
        db.addCourse(course);

        // Студент отправляет заявку на курс
        boolean requestSubmitted = db.submitRegistrationRequest(student.getId(), course.getCourseCode());
        if (requestSubmitted) {
            System.out.println("Заявка студента " + student.getFirstName() + " на курс " + course.getCourseName() + " отправлена.");
        } else {
            System.out.println("Ошибка при отправке заявки.");
        }

        // Создаём менеджера
        Manager manager = new Manager(
                "M001",
                new ArrayList<>(),
                false,
                true,
                35,
                "Петров",
                "Алексей",
                "manager123",
                LocalDate.of(2015, 1, 15),
                new ArrayList<>(), TypeManager.OR

        );

        // Менеджер подтверждает заявку
        RegistrationRequest request = db.getRegistrationRequests().get(0); // Получаем первую заявку
        manager.processRegistrationRequest(request, true); // Подтверждаем заявку

        // Проверяем курсы студента
        System.out.println("\nКурсы студента после обработки заявки:");
        manager.viewStudentCourses(student);
    }
}
