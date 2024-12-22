package users;

import database.*;
import common.*;
import entities.*;
import enums.*;
import users.*;
import interfaces.ManageNews;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

/**
 * Класс Manager представляет менеджера в системе и расширяет класс Employee.
 */
public class Manager extends Employee implements ManageNews {

    private TypeManager type;
    private static final Logger logger = Logger.getLogger(Manager.class.getName());

    /**
     * Конструктор класса Manager.
     *
     * @param id           Уникальный идентификатор менеджера.
     * @param messages     Список сообщений менеджера.
     * @param isResearcher Является ли менеджер исследователем.
     * @param status       Статус менеджера.
     * @param age          Возраст менеджера.
     * @param lastName     Фамилия менеджера.
     * @param firstName    Имя менеджера.
     * @param password     Пароль менеджера.
     * @param hireDate     Дата найма менеджера.
     * @param orders       Список заказов или заданий менеджера.
     * @param type         Тип менеджера.
     */
    public Manager(String id, List<Message> messages, boolean isResearcher, boolean status, int age, String lastName,
                   String firstName, String password, LocalDate hireDate, List<Order> orders, TypeManager type) {
        super(id, messages, isResearcher, status, age, lastName, firstName, password, hireDate, orders);
        if (type == null) {
            throw new IllegalArgumentException("Тип менеджера не может быть null.");
        }
        this.type = type;
    }

    /**
     * Получает тип менеджера.
     *
     * @return тип менеджера.
     */
    public TypeManager getType() {
        return type;
    }

    /**
     * Устанавливает тип менеджера.
     *
     * @param type Новый тип менеджера.
     * @throws IllegalArgumentException если тип менеджера null.
     */
    public void setType(TypeManager type) {
        if (type == null) {
            throw new IllegalArgumentException("Тип менеджера не может быть null.");
        }
        this.type = type;
    }
    
    
    
    public void makeNew(String title, String topic, String content, Languages language) {
		News news = new News(title, topic, content, language, this);
	}
	public void deleteNew(News n) {
		DataBase.getInstance().deleteNew(n);
	}
	public void deleteComment(Comment c) {
		if (c != null){
			News news = c.getNews();
				news.removeComment(c);
				System.out.println("Комментарий удалён.");
		}
		else{
			System.out.println("Комментарий не найден.");
		}
	}
	
    

    /**
     * Подтверждает или отклоняет заявку студента на курс.
     *
     * @param request Заявка на регистрацию.
     * @param approve true, если заявка одобрена; false, если отклонена.
     */

    public void processRegistrationRequest(RegistrationRequest request, boolean approve) {
        if (request == null) {
            throw new IllegalArgumentException("Заявка не может быть null.");
        }

        DataBase db = DataBase.getInstance();
        Student student = request.getStudent();
        Course course = request.getCourse();

        if (approve) {
            int totalCredits = student.getCourses().stream().mapToInt(Course::getCredits).sum();

            if (totalCredits + course.getCredits() > student.getCreditsForSemester()) {
                System.out.println("Невозможно одобрить заявку. Превышение лимита кредитов на семестр.");
                return;
            }

            // Используем базу данных для добавления курса студенту
            boolean success = db.assignCourseToStudent(student.getId(), course.getCourseCode());
            if (success) {
                db.getRegistrationRequests().remove(request);
                logger.info("Заявка одобрена: Студент " + student.getId() + " добавлен на курс " + course.getCourseName());
                System.out.println("Заявка одобрена: курс добавлен.");
            } else {
                System.out.println("Не удалось добавить курс. Возможно, курс уже назначен студенту.");
            }
        } else {
            db.getRegistrationRequests().remove(request);
            logger.info("Заявка отклонена: Студент " + student.getId() + " на курс " + course.getCourseName());
            System.out.println("Заявка отклонена.");
        }
    }

    /**
     * Добавляет новый курс в систему.
     *
     * @param course Курс для добавления.
     */
    public void addCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Курс не может быть null.");
        }
        DataBase db = DataBase.getInstance();
        boolean success = db.addCourse(course);
        if (success) {
            logger.info("Менеджер " + this.getId() + " добавил курс: " + course.getCourseName());
            System.out.println("Курс добавлен успешно.");
        } else {
            System.out.println("Не удалось добавить курс. Возможно, курс уже существует.");
        }
    }

    /**
     * Просматривает курсы, назначенные студенту.
     *
     * @param student Студент, курсы которого просматриваются.
     */
    public void viewStudentCourses(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Студент не может быть null.");
        }
        List<Course> courses = student.getCourses();
        if (courses.isEmpty()) {
            System.out.println("У студента " + student.getFirstName() + " " + student.getLastName() + " нет назначенных курсов.");
        } else {
            System.out.println("Курсы студента " + student.getFirstName() + " " + student.getLastName() + ":");
            for (Course course : courses) {
                System.out.println("- " + course.getCourseName() + " (" + course.getCourseCode() + ")");
            }
        }
    }

    /**
     * Просматривает транскрипт студента.
     *
     * @param student Студент, транскрипт которого просматривается.
     */
    public void viewStudentTranscript(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Студент не может быть null.");
        }
        Transcript transcript = student.getTranscript();
        if (transcript == null) {
            System.out.println("Транскрипт студента не доступен.");
        } else {
            System.out.println("Транскрипт студента " + student.getFirstName() + " " + student.getLastName() + ":");
            System.out.println(transcript);
        }
    }

    @Override
    public String toString() {
        return "Manager{" +
                "type=" + type +
                '}';
    }
}

