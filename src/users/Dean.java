//package users;
//
//import java.util.List;
//
///**
// * Класс Dean представляет декана факультета и расширяет класс Employee.
// */
//public class Dean extends Employee {
//
//	private Faculty faculty;
//
//	/**
//	 * Конструктор класса Dean.
//	 *
//	 * @param id           Идентификатор декана.
//	 * @param messages     Список сообщений декана.
//	 * @param isResearcher Является ли декан исследователем.
//	 * @param status       Статус декана (например, онлайн/офлайн).
//	 * @param age          Возраст декана.
//	 * @param lastName     Фамилия декана.
//	 * @param firstName    Имя декана.
//	 * @param password     Пароль декана.
//	 * @param hireDate     Дата найма декана.
//	 * @param faculty      Факультет, которым управляет декан.
//	 * @throws IllegalArgumentException если faculty null.
//	 */
//	public Dean(String id, List<Message> messages, boolean isResearcher, boolean status, int age, String lastName, String firstName, String password, LocalDate hireDate, Faculty faculty) {
//		super(id, messages, isResearcher, status, age, lastName, firstName, password, hireDate);
//		if (faculty == null) {
//			throw new IllegalArgumentException("Факультет не может быть null.");
//		}
//		this.faculty = faculty;
//	}
//
//	/**
//	 * Получает факультет, которым управляет декан.
//	 *
//	 * @return факультет.
//	 */
//	public Faculty getFaculty() {
//		return faculty;
//	}
//
//	/**
//	 * Устанавливает факультет, которым управляет декан.
//	 *
//	 * @param faculty новый факультет.
//	 * @throws IllegalArgumentException если faculty null.
//	 */
//	public void setFaculty(Faculty faculty) {
//		if (faculty == null) {
//			throw new IllegalArgumentException("Факультет не может быть null.");
//		}
//		this.faculty = faculty;
//	}
//
//	/**
//	 * Просмотр рейтинга преподавателя.
//	 *
//	 * @param t Преподаватель, рейтинг которого нужно просмотреть.
//	 */
//	public void viewTeacherRate(Teacher t) {
//		if (t == null) {
//			System.out.println("Преподаватель не найден.");
//			return;
//		}
//		System.out.println("Рейтинг преподавателя " + t.getFirstName() + " " + t.getLastName() + ": " + t.getRating());
//	}
//
//	/**
//	 * Просмотр курсов студента.
//	 *
//	 * @param s Студент, курсы которого нужно просмотреть.
//	 */
//	public void viewStudentCourses(Student s) {
//		if (s == null) {
//			System.out.println("Студент не найден.");
//			return;
//		}
//		System.out.println("Курсы студента " + s.getFirstName() + " " + s.getLastName() + ":");
//		for (Course course : s.getCourses()) {
//			System.out.println("- " + course.getName());
//		}
//	}
//
//	/**
//	 * Просмотр транскрипта студента.
//	 *
//	 * @param s Студент, транскрипт которого нужно просмотреть.
//	 */
//	public void viewStudentTranscript(Student s) {
//		if (s == null) {
//			System.out.println("Студент не найден.");
//			return;
//		}
//		System.out.println("Транскрипт студента " + s.getFirstName() + " " + s.getLastName() + ":");
//		System.out.println(s.getTranscript());
//	}
//
//	/**
//	 * Просмотр аттестации студента.
//	 *
//	 * @param s Студент, аттестацию которого нужно просмотреть.
//	 */
//	public void viewAttestation(Student s) {
//		if (s == null) {
//			System.out.println("Студент не найден.");
//			return;
//		}
//		System.out.println("Аттестация студента " + s.getFirstName() + " " + s.getLastName() + ":");
//		System.out.println(s.getAttestation());
//	}
//
//	/**
//	 * Просмотр расписания студента.
//	 *
//	 * @param s Студент, расписание которого нужно просмотреть.
//	 */
//	public void viewSchedule(Student s) {
//		if (s == null) {
//			System.out.println("Студент не найден.");
//			return;
//		}
//		System.out.println("Расписание студента " + s.getFirstName() + " " + s.getLastName() + ":");
//		System.out.println(s.getSchedule());
//	}
//
//	/**
//	 * Отправка запроса.
//	 */
//	public void sendRequest() {
//		// Реализуйте логику отправки запроса
//		System.out.println("Запрос отправлен.");
//	}
//
//	/**
//	 * Обработка жалобы.
//	 *
//	 * @param c Жалоба для обработки.
//	 */
//	public void processComplaint(Complaint c) {
//		if (c == null) {
//			System.out.println("Жалоба не найдена.");
//			return;
//		}
//		// Реализуйте логику обработки жалобы в зависимости от уровня срочности
//		UrgencyLevel urgency = c.checkUrgency();
//		switch (urgency) {
//			case LOW:
//				System.out.println("Обработка жалобы на низком уровне срочности.");
//				break;
//			case MEDIUM:
//				System.out.println("Обработка жалобы на среднем уровне срочности.");
//				break;
//			case HIGH:
//				System.out.println("Обработка жалобы на высоком уровне срочности.");
//				break;
//			default:
//				System.out.println("Неизвестный уровень срочности.");
//		}
//	}
//
//	@Override
//	protected void viewMenu() {
//		// Реализуйте логику отображения меню для декана
//		System.out.println("Отображение меню декана факультета " + faculty);
//	}
//
//	@Override
//	protected void viewInfo() {
//		// Реализуйте логику отображения информации о декане
//		System.out.println(this.toString());
//	}
//
//	@Override
//	public String toString() {
//		return "Dean{" +
//				"hireDate=" + getHireDate() +
//				", faculty=" + faculty +
//				", " + super.toString() +
//				'}';
//	}
//}
//
