package services;

import common.News;
import database.*;
import decorators.ResearcherDecorator;
import entities.*;
import enums.*;
import exceptions.InvalidStudentYearException;
import exceptions.InvalidSupervisorException;
import users.Manager;
import users.User;
//import users.*;
//import decorators.ResearcherDecorator;

import java.time.LocalDate;
import java.util.*;

public class ConsoleApplication {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DataBase db = DataBase.getInstance();

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в систему управления университетом!");

        while (true) {
            System.out.println("\nВыберите роль для авторизации:");
            System.out.println("1. Студент");
            System.out.println("2. Преподаватель");
            System.out.println("3. Менеджер");
            System.out.println("4. Администратор");
            System.out.println("5. Выход");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    studentMenu();
                    break;
                case 2:
                    teacherMenu();
                    break;
                case 3:
                    managerMenu();
                    break;
                case 4:
                    adminMenu();
                    break;
                case 5:
                    System.out.println("Выход из программы. До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void studentMenu() {

        System.out.println("\nДобро пожаловать, студент!");
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Просмотреть транскрипт");
            System.out.println("2. Просмотреть доступные дисциплины");
            System.out.println("3. Зарегистрироваться на дисциплину");
            System.out.println("4. Отказаться от дисциплины");
            System.out.println("5. Назначить эдвайзера");
            System.out.println("6. Управление организацией");
            System.out.println("7. Выйти в главное меню");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Введите свой ID: ");
                    String studentId = scanner.nextLine();
                    Student student = db.findStudentById(studentId);
                    if (student != null) {
                        student.getTranscript().printTranscript();
                    } else {
                        System.out.println("Студент не найден.");
                    }
                    break;
                case 2:
                    System.out.println("Доступные курсы:");
                    for (Course course : db.getAllCourses()) {
                        System.out.println(course);
                    }
                    break;
                case 3:
                    System.out.print("Введите свой ID: ");
                    String studentIdForRegistration = scanner.nextLine();
                    Student registeringStudent = db.findStudentById(studentIdForRegistration);
                    if (registeringStudent == null) {
                        System.out.println("Студент не найден.");
                        break;
                    }

                    System.out.println("Доступные курсы:");
                    List<Course> availableCourses = db.getAllCourses();
                    for (int i = 0; i < availableCourses.size(); i++) {
                        System.out.println((i + 1) + ". " + availableCourses.get(i).getCourseName());
                    }

                    System.out.print("Выберите номер курса для регистрации: ");
                    int courseChoice = Integer.parseInt(scanner.nextLine());
                    if (courseChoice < 1 || courseChoice > availableCourses.size()) {
                        System.out.println("Неверный выбор.");
                        break;
                    }

                    Course selectedCourse = availableCourses.get(courseChoice - 1);
                    if (registeringStudent.getCourses().contains(selectedCourse)) {
                        System.out.println("Вы уже зарегистрированы на этот курс.");
                    } else {
                        boolean isRequestSubmitted = db.submitRegistrationRequest(registeringStudent.getId(), selectedCourse.getCourseCode());
                        if (isRequestSubmitted) {
                            System.out.println("Заявка на регистрацию успешно отправлена на курс: " + selectedCourse.getCourseName());
                        } else {
                            System.out.println("Не удалось отправить заявку на регистрацию. Проверьте данные.");
                        }
                    }
                    break;

                case 4:
                    System.out.print("Введите свой ID: ");
                    String studentIdForDropping = scanner.nextLine();
                    Student droppingStudent = db.findStudentById(studentIdForDropping);
                    if (droppingStudent == null) {
                        System.out.println("Студент не найден.");
                        break;
                    }

                    System.out.println("Ваши текущие курсы:");
                    List<Course> enrolledCourses = droppingStudent.getCourses();
                    if (enrolledCourses.isEmpty()) {
                        System.out.println("Вы не зарегистрированы на какие-либо курсы.");
                        break;
                    }

                    for (int i = 0; i < enrolledCourses.size(); i++) {
                        System.out.println((i + 1) + ". " + enrolledCourses.get(i).getCourseName());
                    }

                    System.out.print("Выберите номер курса, от которого вы хотите отказаться: ");
                    int dropCourseChoice = Integer.parseInt(scanner.nextLine());
                    if (dropCourseChoice < 1 || dropCourseChoice > enrolledCourses.size()) {
                        System.out.println("Неверный выбор.");
                        break;
                    }

                    Course courseToDrop = enrolledCourses.get(dropCourseChoice - 1);
                    boolean isDropped = droppingStudent.removeCourse(courseToDrop.getCourseCode());
                    if (isDropped) {
                        System.out.println("Вы успешно отказались от курса: " + courseToDrop.getCourseName());
                    } else {
                        System.out.println("Не удалось отказаться от курса. Попробуйте снова.");
                    }
                    break;
                case 5:
                    System.out.print("Введите свой ID: ");
                    String studentIdForAdvisor = scanner.nextLine();
                    Student advisingStudent = db.findStudentById(studentIdForAdvisor);

                    if (advisingStudent == null) {
                        System.out.println("Студент не найден.");
                        break;
                    }

                    System.out.println("Доступные преподаватели для назначения:");
                    List<Teacher> availableTeachers = db.getAllTeachers();
                    for (int i = 0; i < availableTeachers.size(); i++) {
                        System.out.println((i + 1) + ". " + availableTeachers.get(i).getFullName());
                    }

                    System.out.print("Выберите номер преподавателя для назначения адвайзера: ");
                    int teacherChoice = Integer.parseInt(scanner.nextLine());
                    if (teacherChoice < 1 || teacherChoice > availableTeachers.size()) {
                        System.out.println("Неверный выбор.");
                        break;
                    }

                    Teacher selectedTeacher = availableTeachers.get(teacherChoice - 1);
                    ResearcherDecorator advisor = new ResearcherDecorator(selectedTeacher, selectedTeacher.getFaculty());

                    try {
                        advisingStudent.assignSupervisor(advisor);
                    } catch (InvalidSupervisorException | InvalidStudentYearException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;
                case 6:
                    manageOrganization();
                case 7:
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void teacherMenu() {
        System.out.println("\nДобро пожаловать, преподаватель!");
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить оценку студенту");
            System.out.println("2. Просмотреть список своих курсов");
            System.out.println("3. Опубликовать научную статью");
            System.out.println("4. Выпустить новость");
            System.out.println("5. Выйти в главное меню");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Введите ID студента: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Введите код курса: ");
                    String courseCode = scanner.nextLine();
                    System.out.print("Введите баллы за первую аттестацию (0-30): ");
                    double firstAttestation = Double.parseDouble(scanner.nextLine());
                    System.out.print("Введите баллы за вторую аттестацию (0-30): ");
                    double secondAttestation = Double.parseDouble(scanner.nextLine());
                    System.out.print("Введите баллы за финальный экзамен (0-40): ");
                    double finalExam = Double.parseDouble(scanner.nextLine());

                    Student student = db.findStudentById(studentId);
                    Course course = db.findCourseByCode(courseCode);

                    if (student != null && course != null) {
                        student.getTranscript().addGrade(studentId, course, firstAttestation, secondAttestation, finalExam);
                        System.out.println("Оценка успешно добавлена!");
                    } else {
                        System.out.println("Студент или курс не найдены.");
                    }
                    break;
                case 2:
                    System.out.println("Список ваших курсов:");
                    for (Course c : db.getAllCourses()) {
                        System.out.println(c);
                    }
                    break;

                case 3:
                    System.out.print("Введите название статьи: ");
                    String articleTitle = scanner.nextLine();
                    System.out.print("Введите тему статьи: ");
                    String articleTopic = scanner.nextLine();
                    System.out.print("Введите контент статьи: ");
                    String articleContent = scanner.nextLine();

                    ResearchPaper paper = new ResearchPaper(
                            articleTitle,
                            0, // Initial citations
                            new Date(), // Published date
                            10, // Default pages
                            articleContent,
                            articleTopic
                    );

                    System.out.println("Научная статья опубликована:\n" + paper);
                    break;

                case 4:
                    System.out.print("Введите заголовок новости: ");
                    String newsTitle = scanner.nextLine();
                    System.out.print("Введите тему новости: ");
                    String newsTopic = scanner.nextLine();
                    System.out.print("Введите контент новости: ");
                    String newsContent = scanner.nextLine();
                    System.out.print("Выберите язык новости (например, EN, RU): ");
                    Languages newsLanguage = Languages.valueOf(scanner.nextLine().toUpperCase());

                    News news = new News(newsTitle, newsTopic, newsContent, newsLanguage, null);
                    db.addNews(news);

                    System.out.println("Новость успешно выпущена:\n" + news);
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void managerMenu() {
        System.out.println("\nДобро пожаловать, менеджер!");
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Управление студентами");
            System.out.println("2. Управление курсами");
            System.out.println("3. Выпустить новость");
            System.out.println("4. Выйти в главное меню");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    manageStudents();
                    break;
                case 2:
                    manageCourses();
                    break;
                case 3:
                    manageNews();
                case 4:
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void adminMenu() {
        System.out.println("\nДобро пожаловать, администратор!");
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Управление преподавателями");
            System.out.println("2. Управление студентами");
            System.out.println("3. Управление новостями");
            System.out.println("4. Управление курсами");
            System.out.println("5. Управление паролями пользователей");
            System.out.println("6. Управление менеджерами");
            System.out.println("7. Выйти в главное меню");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    manageTeachers();
                    break;
                case 2:
                    manageStudents();
                    break;
                case 3:
                    manageNews();
                    break;
                case 4:
                    manageCourses();
                    break;
                case 5:
                    managePasswords();
                    break;
                case 6:
                    manageManagers();
                case 7:
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }


    // Вспомогательные методы управления
    private static void manageStudents() {
        System.out.println("\nУправление студентами:");
        System.out.println("1. Добавить студента");
        System.out.println("2. Показать всех студентов");
        System.out.println("3. Удалить студента");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.print("Введите ID студента: ");
                String id = scanner.nextLine();
                System.out.print("Введите имя: ");
                String firstName = scanner.nextLine();
                System.out.print("Введите фамилию: ");
                String lastName = scanner.nextLine();
                System.out.print("Введите факультет (например, FIT): ");
                Faculty faculty = Faculty.valueOf(scanner.nextLine().toUpperCase());

                Student student = new Student(
                        id,                            // ID студента
                        "password123",                 // Пароль (по умолчанию)
                        firstName,                     // Имя студента
                        lastName,                      // Фамилия студента
                        18,                            // Возраст (по умолчанию)
                        false,                         // Не является исследователем
                        Degree.Bachelor,               // Степень (бакалавр)
                        faculty,                       // Факультет
                        1,                             // Год обучения (по умолчанию 1)
                        0.0,                           // GPA (по умолчанию)
                        new ArrayList<>(),             // Курсы
                        new Transcript(),              // Транскрипт
                        null,                          // Дата выпуска (по умолчанию null)
                        new ArrayList<>(),             // Уроки
                        new HashMap<>()                // Жалобы
                );
                db.addStudent(student);

                System.out.println("Студент успешно добавлен!");
                break;

            case 2:
                System.out.println("Все студенты:");
                for (Student s : db.getAllStudents()) {
                    System.out.println(s);
                }
                break;

            case 3:
                System.out.print("Введите ID студента, которого хотите удалить: ");
                String studentId = scanner.nextLine();
                Student studentToRemove = db.findStudentById(studentId);

                if (studentToRemove != null) {
                    db.removeStudent(studentId);
                    System.out.println("Студент успешно удалён.");
                } else {
                    System.out.println("Студент с указанным ID не найден.");
                }
                break;

            default:
                System.out.println("Неверный выбор.");
        }
    }


    private static void manageTeachers() {
        System.out.println("\nУправление преподавателями:");
        System.out.println("1. Добавить преподавателя");
        System.out.println("2. Показать всех преподавателей");
        System.out.println("3. Удалить преподавателя");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.print("Введите ID преподавателя: ");
                String id = scanner.nextLine();
                System.out.print("Введите имя: ");
                String firstName = scanner.nextLine();
                System.out.print("Введите фамилию: ");
                String lastName = scanner.nextLine();
                System.out.print("Введите дату найма (в формате ГГГГ-ММ-ДД): ");
                String inputDate = scanner.nextLine();
                LocalDate hireDate;
                try {
                    String[] parts = inputDate.split("-");
                    String year = parts[0];
                    String month = parts[1].length() == 1 ? "0" + parts[1] : parts[1];
                    String day = parts[2].length() == 1 ? "0" + parts[2] : parts[2];
                    String formattedDate = year + "-" + month + "-" + day;

                    hireDate = LocalDate.parse(formattedDate);
                } catch (Exception e) {
                    System.out.println("Неверный формат даты. Используйте формат ГГГГ-ММ-ДД.");
                    return;
                }

                System.out.print("Введите факультет преподавателя (например, FIT): ");
                Faculty faculty = Faculty.valueOf(scanner.nextLine().toUpperCase());

                Teacher teacher = new Teacher(
                        id,
                        new ArrayList<>(),   // Сообщения
                        false,               // Не исследователь
                        true,                // Активный статус
                        35,                  // Возраст (по умолчанию)
                        firstName,
                        lastName,
                        "password123",       // Пароль по умолчанию
                        hireDate,
                        new ArrayList<>(),   // Заказы
                        TypeTeacher.Tutor,   // Тип преподавателя
                        4.5,                 // Рейтинг
                        new HashMap<>(),     // Расписание
                        faculty              // Факультет
                );

                db.addTeacher(teacher);
                System.out.println("DEBUG: Teacher ID before adding to database = " + teacher.getId()); // Debugging line
                System.out.println("Преподаватель успешно добавлен!");
                break;

            case 2:
                System.out.println("Все преподаватели:");
                for (Teacher t : db.getAllTeachers()) {
                    System.out.println(t);
                }
                break;

            case 3:
                System.out.print("Введите ID преподавателя, которого хотите удалить: ");
                String teacherId = scanner.nextLine();
                Teacher teacherToRemove = db.findTeacherById(teacherId);

                if (teacherToRemove != null) {
                    db.removeTeacher(teacherId);
                    System.out.println("Преподаватель успешно удалён.");
                } else {
                    System.out.println("Преподаватель с указанным ID не найден.");
                }
                break;

            default:
                System.out.println("Неверный выбор.");
        }
    }

    private static void manageCourses() {
        System.out.println("\nУправление курсами:");
        System.out.println("1. Добавить курс");
        System.out.println("2. Назначить преподавателя на курс");
        System.out.println("3. Заявки регистрации на курс");
        System.out.println("4. Удалить курс");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.print("Введите код курса: ");
                String courseCode = scanner.nextLine();
                System.out.print("Введите название курса: ");
                String courseName = scanner.nextLine();
                System.out.print("Введите количество кредитов: ");
                int credits = Integer.parseInt(scanner.nextLine());
                System.out.print("Введите факультет курса (например, FIT): ");
                Faculty faculty = Faculty.valueOf(scanner.nextLine().toUpperCase());
                System.out.print("Введите максимальное количество студентов: ");
                int maxStudents = Integer.parseInt(scanner.nextLine());

                Course course = new Course(courseCode, courseName, credits, TypeCourse.Required, faculty, maxStudents);
                db.addCourse(course);
                System.out.println("Курс успешно добавлен!");
                break;

            case 2:
                System.out.print("Введите код курса: ");
                String assignCourseCode = scanner.nextLine();
                Course courseToAssign = db.findCourseByCode(assignCourseCode);

                if (courseToAssign == null) {
                    System.out.println("Курс с указанным кодом не найден.");
                    break;
                }

                System.out.print("Введите ID преподавателя: ");
                String teacherId = scanner.nextLine();
                Teacher teacherToAssign = db.findTeacherById(teacherId);

                if (teacherToAssign == null) {
                    System.out.println("Преподаватель с указанным ID не найден.");
                    break;
                }

                courseToAssign.addTeacher(teacherToAssign);
                System.out.println("Преподаватель успешно назначен на курс!");
                break;

            case 3:
                System.out.println("Заявки на регистрацию:");
                List<RegistrationRequest> requests = db.getRegistrationRequests();
                if (requests.isEmpty()) {
                    System.out.println("Нет активных заявок на регистрацию.");
                    break;
                }

                for (int i = 0; i < requests.size(); i++) {
                    RegistrationRequest request = requests.get(i);
                    System.out.println((i + 1) + ". Студент: " + request.getStudent().getFullName() +
                            ", Курс: " + request.getCourse().getCourseName());
                }

                System.out.print("Выберите номер заявки для обработки или 0 для выхода: ");
                int requestChoice = Integer.parseInt(scanner.nextLine());

                if (requestChoice == 0) {
                    break;
                }

                if (requestChoice < 1 || requestChoice > requests.size()) {
                    System.out.println("Неверный выбор.");
                    break;
                }

                RegistrationRequest selectedRequest = requests.get(requestChoice - 1);
                System.out.print("Одобрить заявку? (yes/no): ");
                String decision = scanner.nextLine().toLowerCase();

                if (decision.equals("yes")) {
                    boolean isAssigned = db.assignCourseToStudent(selectedRequest.getStudent().getId(),
                            selectedRequest.getCourse().getCourseCode());
                    if (isAssigned) {
                        System.out.println("Заявка одобрена. Курс успешно назначен студенту.");
                        requests.remove(selectedRequest); // Remove the processed request
                    } else {
                        System.out.println("Не удалось назначить курс студенту.");
                    }
                } else {
                    System.out.println("Заявка отклонена.");
                    requests.remove(selectedRequest); // Remove the rejected request
                }
                break;

            case 4:
                System.out.print("Введите код курса, который нужно удалить: ");
                String courseCodeToDelete = scanner.nextLine();
                boolean isDeleted = db.removeCourse(courseCodeToDelete);

                if (isDeleted) {
                    System.out.println("Курс успешно удалён.");
                } else {
                    System.out.println("Курс с указанным кодом не найден.");
                }
                break;

            default:
                System.out.println("Неверный выбор.");
        }
    }

    private static void manageNews() {
        System.out.println("\nУправление новостями:");
        System.out.println("1. Создать новость");
        System.out.println("2. Удалить новость");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.print("Введите заголовок новости: ");
                String title = scanner.nextLine();
                System.out.print("Введите тему новости: ");
                String topic = scanner.nextLine();
                System.out.print("Введите содержание новости: ");
                String content = scanner.nextLine();

                News news = new News(title, topic, content, Languages.EN, null); // Добавить источник (например, админ)
                db.addNews(news);
                System.out.println("Новость успешно добавлена!");
                break;

            case 2:
                System.out.println("Выберите новость для удаления:");
                List<News> newsList = db.getAllNews();
                for (int i = 0; i < newsList.size(); i++) {
                    System.out.println((i + 1) + ". " + newsList.get(i).getTitle());
                }
                int newsChoice = Integer.parseInt(scanner.nextLine());
                if (newsChoice >= 1 && newsChoice <= newsList.size()) {
                    db.removeNews(newsList.get(newsChoice - 1));
                    System.out.println("Новость успешно удалена!");
                } else {
                    System.out.println("Неверный выбор.");
                }
                break;

            default:
                System.out.println("Неверный выбор.");
        }
    }
    private static void managePasswords() {
        System.out.println("\nУправление паролями пользователей:");
        System.out.print("Введите ID пользователя, для которого нужно изменить пароль: ");
        String userId = scanner.nextLine();

        // Search for the user in students, teachers, and other user types
        User user = db.findStudentById(userId);
        if (user == null) {
            user = db.findTeacherById(userId);
        }
        // Add other user type searches here if necessary (e.g., managers, admins)

        if (user == null) {
            System.out.println("Пользователь с указанным ID не найден.");
            return;
        }

        System.out.print("Введите новый пароль для пользователя " + user.getFullName() + ": ");
        String newPassword = scanner.nextLine();

        user.setPassword(newPassword); // Assuming User class has a setPassword method
        System.out.println("Пароль успешно изменён для пользователя: " + user.getFullName());
    }

    private static void manageManagers() {
        System.out.println("\nУправление менеджерами:");
        System.out.println("1. Добавить менеджера");
        System.out.println("2. Показать всех менеджеров");
        System.out.println("3. Удалить менеджера");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.print("Введите ID менеджера: ");
                String id = scanner.nextLine();
                System.out.print("Введите имя: ");
                String firstName = scanner.nextLine();
                System.out.print("Введите фамилию: ");
                String lastName = scanner.nextLine();
                System.out.print("Введите дату найма (в формате ГГГГ-ММ-ДД): ");
                String inputDate = scanner.nextLine();
                LocalDate hireDate;
                try {
                    hireDate = LocalDate.parse(inputDate);
                } catch (Exception e) {
                    System.out.println("Неверный формат даты. Используйте формат ГГГГ-ММ-ДД.");
                    return;
                }

                Manager manager = new Manager(
                        id,
                        new ArrayList<>(),      // Пустой список сообщений
                        false,                  // Не исследователь
                        true,                   // Активный статус
                        30,                     // Возраст (по умолчанию)
                        lastName,
                        firstName,
                        "password123",          // Пароль по умолчанию
                        hireDate,
                        new ArrayList<>(),      // Пустой список заказов
                        TypeManager.OR          // Тип менеджера
                );

                db.addManager(manager); // Assuming a `db.addManager()` method exists
                System.out.println("Менеджер успешно добавлен!");
                break;

            case 2:
                System.out.println("Все менеджеры:");
                for (Manager m : db.getAllManagers()) { // Assuming `db.getAllManagers()` returns a list of managers
                    System.out.println(m);
                }
                break;

            case 3:
                System.out.print("Введите ID менеджера, которого хотите удалить: ");
                String managerId = scanner.nextLine();
                boolean isRemoved = db.removeManager(managerId); // Assuming `db.removeManager()` exists
                if (isRemoved) {
                    System.out.println("Менеджер успешно удалён.");
                } else {
                    System.out.println("Менеджер с указанным ID не найден.");
                }
                break;

            default:
                System.out.println("Неверный выбор.");
        }
    }
    private static void manageOrganization() {
        System.out.println("\nУправление организацией:");
        System.out.println("1. Создать организацию");
        System.out.println("2. Просмотреть текущую организацию");
        System.out.println("3. Присоединиться к существующей организации");
        System.out.println("4. Поменять главу организации");
        System.out.println("5. Вернуться");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.print("Введите свой ID: ");
                String studentId = scanner.nextLine();
                Student student = db.findStudentById(studentId);

                if (student == null) {
                    System.out.println("Студент не найден.");
                    break;
                }

                if (student.getOrganization() != null) {
                    System.out.println("Вы уже состоите в организации: " + student.getOrganization().getOrgName());
                    break;
                }

                System.out.print("Введите название организации: ");
                String orgName = scanner.nextLine();

                Organization organization = new Organization(orgName, student);
                db.addOrganization(organization);
                System.out.println("Организация '" + orgName + "' успешно создана с главой " + student.getFirstName() + " " + student.getLastName());
                break;

            case 2:
                System.out.print("Введите свой ID: ");
                String currentStudentId = scanner.nextLine();
                Student currentStudent = db.findStudentById(currentStudentId);

                if (currentStudent == null) {
                    System.out.println("Студент не найден.");
                    break;
                }

                Organization currentOrg = currentStudent.getOrganization();
                if (currentOrg == null) {
                    System.out.println("Вы не состоите в организации.");
                } else {
                    System.out.println("Вы состоите в организации '" + currentOrg.getOrgName() + "'.");
                    System.out.println("Члены организации:");
                    for (Student member : currentOrg.getMembers()) {
                        System.out.println("- " + member.getFirstName() + " " + member.getLastName());
                    }
                }
                break;

            case 3:
                System.out.print("Введите свой ID: ");
                String joinStudentId = scanner.nextLine();
                Student joiningStudent = db.findStudentById(joinStudentId);

                if (joiningStudent == null) {
                    System.out.println("Студент не найден.");
                    break;
                }

                if (joiningStudent.getOrganization() != null) {
                    System.out.println("Вы уже состоите в организации: " + joiningStudent.getOrganization().getOrgName());
                    break;
                }

                System.out.println("Доступные организации:");
                List<Organization> organizations = db.getAllOrganizations();
                for (int i = 0; i < organizations.size(); i++) {
                    System.out.println((i + 1) + ". " + organizations.get(i).getOrgName());
                }

                System.out.print("Выберите номер организации для присоединения: ");
                int orgChoice = Integer.parseInt(scanner.nextLine());
                if (orgChoice < 1 || orgChoice > organizations.size()) {
                    System.out.println("Неверный выбор.");
                    break;
                }

                Organization selectedOrg = organizations.get(orgChoice - 1);
                selectedOrg.addMember(joiningStudent);
                System.out.println("Вы успешно присоединились к организации '" + selectedOrg.getOrgName() + "'.");
                break;

            case 4:
                System.out.print("Введите свой ID: ");
                String currentId = scanner.nextLine();
                Student current = db.findStudentById(currentId);

                if (current == null) {
                    System.out.println("Студент не найден.");
                    break;
                }

                Organization org = current.getOrganization();
                if (org == null) {
                    System.out.println("Вы не состоите в организации.");
                    break;
                }

                if (!org.getHead().equals(current)) {
                    System.out.println("Только глава организации может сменить главу.");
                    break;
                }

                System.out.println("Члены организации:");
                List<Student> members = org.getMembers();
                for (int i = 0; i < members.size(); i++) {
                    System.out.println((i + 1) + ". " + members.get(i).getFirstName() + " " + members.get(i).getLastName());
                }

                System.out.print("Выберите номер нового главы организации: ");
                int newHeadIndex = Integer.parseInt(scanner.nextLine()) - 1;
                if (newHeadIndex < 0 || newHeadIndex >= members.size()) {
                    System.out.println("Неверный выбор.");
                    break;
                }

                Student newHead = members.get(newHeadIndex);
                org.setHead(newHead);
                System.out.println("Новый глава организации: " + newHead.getFirstName() + " " + newHead.getLastName());
                break;

            case 5:
                return;

            default:
                System.out.println("Неверный выбор.");
        }
    }

}
