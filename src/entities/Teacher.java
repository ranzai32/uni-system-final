package entities;

import common.Message;
import common.Order;
import enums.Faculty;
import enums.TypeTeacher;
import common.Lesson;
import users.Employee;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Класс Teacher представляет преподавателя в системе и расширяет класс Employee.
 */
public class Teacher extends Employee {

	private TypeTeacher type;
	private double rate;
	private Map<Lesson, List<Student>> lessons;
	private Faculty faculty;

	/**
	 * Конструктор класса Teacher.
	 *
	 * @param id        Уникальный идентификатор преподавателя.
	 * @param messages  Список сообщений, связанных с преподавателем.
	 * @param isResearcher Является ли преподаватель исследователем.
	 * @param status    Статус преподавателя (активен или нет).
	 * @param age       Возраст преподавателя.
	 * @param lastName  Фамилия преподавателя.
	 * @param firstName Имя преподавателя.
	 * @param password  Пароль преподавателя.
	 * @param hireDate  Дата найма преподавателя.
	 * @param orders    Список заказов или заданий, связанных с преподавателем.
	 * @param type      Тип преподавателя (например, полный рабочий день, частичная занятость).
	 * @param rate      Рейтинг или ставка преподавателя.
	 * @param lessons   Расписание уроков, где каждый урок связан с списком студентов.
	 * @param faculty   Факультет, к которому относится преподаватель.
	 */
	public Teacher(String id, List<Message> messages, boolean isResearcher, boolean status, int age, String lastName,
				   String firstName, String password, LocalDate hireDate, List<Order> orders, TypeTeacher type,
				   double rate, Map<Lesson, List<Student>> lessons, Faculty faculty) {
		super(id, messages, isResearcher, status, age, lastName, firstName, password, hireDate, orders);
		if (type == null) {
			throw new IllegalArgumentException("Тип преподавателя не может быть null.");
		}
		if (faculty == null) {
			throw new IllegalArgumentException("Факультет не может быть null.");
		}
		this.type = type;
		this.rate = rate;
		this.lessons = (lessons != null) ? new HashMap<>(lessons) : new HashMap<>();
		this.faculty = faculty;
	}

	// Геттеры и сеттеры

	/**
	 * Получает тип преподавателя.
	 *
	 * @return тип преподавателя.
	 */
	public TypeTeacher getType() {
		return type;
	}

	/**
	 * Устанавливает тип преподавателя.
	 *
	 * @param type Новый тип преподавателя.
	 * @throws IllegalArgumentException если тип null.
	 */
	public void setType(TypeTeacher type) {
		if (type == null) {
			throw new IllegalArgumentException("Тип преподавателя не может быть null.");
		}
		this.type = type;
	}

	/**
	 * Получает рейтинг преподавателя.
	 *
	 * @return рейтинг преподавателя.
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * Устанавливает рейтинг преподавателя.
	 *
	 * @param rate Новый рейтинг преподавателя.
	 */
	public void setRate(double rate) {
		if (rate < 0.0 || rate > 5.0) {
			throw new IllegalArgumentException("Рейтинг должен быть между 0.0 и 5.0.");
		}
		this.rate = rate;
	}

	/**
	 * Получает расписание уроков преподавателя.
	 *
	 * @return карта уроков и связанных с ними студентов.
	 */
	public Map<Lesson, List<Student>> getLessons() {
		return new HashMap<>(lessons);
	}

	/**
	 * Устанавливает расписание уроков преподавателя.
	 *
	 * @param lessons Новое расписание уроков.
	 * @throws IllegalArgumentException если расписание null.
	 */
	public void setLessons(Map<Lesson, List<Student>> lessons) {
		if (lessons == null) {
			throw new IllegalArgumentException("Расписание уроков не может быть null.");
		}
		this.lessons = new HashMap<>(lessons);
	}

	/**
	 * Получает факультет преподавателя.
	 *
	 * @return факультет преподавателя.
	 */
	public Faculty getFaculty() {
		return faculty;
	}

	/**
	 * Устанавливает факультет преподавателя.
	 *
	 * @param faculty Новый факультет преподавателя.
	 * @throws IllegalArgumentException если факультет null.
	 */
	public void setFaculty(Faculty faculty) {
		if (faculty == null) {
			throw new IllegalArgumentException("Факультет не может быть null.");
		}
		this.faculty = faculty;
	}

	/**
	 * Добавляет урок в расписание преподавателя.
	 *
	 * @param lesson   Урок для добавления.
	 * @param students Список студентов, связанных с уроком.
	 * @return true, если урок успешно добавлен, иначе false.
	 */
//	public boolean addLesson(Lesson lesson, List<Student> students) {
//		if (lesson == null || students == null) {
//			throw new IllegalArgumentException("Урок и список студентов не могут быть null.");
//		}
//		if (lessons.containsKey(lesson)) {
//			System.out.println("Урок уже существует в расписании.");
//			return false;
//		}
//		lessons.put(lesson, new ArrayList<>(students));
//		Admin.getInstance().addLog("Преподаватель " + this.getId() + " добавил урок: " + lesson.getLessonName());
//		return true;
//	}

	/**
	 * Удаляет урок из расписания преподавателя.
	 *
	 * @param lesson Урок для удаления.
	 * @return true, если урок успешно удален, иначе false.
	 */
//	public boolean removeLesson(Lesson lesson) {
//		if (lesson == null) {
//			throw new IllegalArgumentException("Урок не может быть null.");
//		}
//		if (!lessons.containsKey(lesson)) {
//			System.out.println("Урок не найден в расписании.");
//			return false;
//		}
//		lessons.remove(lesson);
//		Admin.getInstance().addLog("Преподаватель " + this.getId() + " удалил урок: " + lesson.getLessonName());
//		return true;
//	}

	/**
	 * Просматривает расписание преподавателя.
	 */
//	public void viewSchedule() {
//		if (lessons.isEmpty()) {
//			System.out.println("Расписание преподавателя " + this.getFirstName() + " " + this.getLastName() + " пусто.");
//			return;
//		}
//		System.out.println("Расписание преподавателя " + this.getFirstName() + " " + this.getLastName() + ":");
//		for (Map.Entry<Lesson, List<Student>> entry : lessons.entrySet()) {
//			Lesson lesson = entry.getKey();
//			List<Student> students = entry.getValue();
//			System.out.println("- " + lesson.getLessonName() + " (ID: " + lesson.getLessonId() + ")");
//			System.out.println("  Студенты:");
//			for (Student student : students) {
//				System.out.println("    * " + student.getFirstName() + " " + student.getLastName() + " (ID: " + student.getId() + ")");
//			}
//		}
//	}

	/**
	 * Переопределяет метод equals() для класса Teacher.
	 * Сравнивает только по уникальному идентификатору.
	 *
	 * @param o Объект для сравнения.
	 * @return true, если объекты равны, иначе false.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Teacher)) return false;
		Teacher teacher = (Teacher) o;
		return Objects.equals(this.getId(), teacher.getId());
	}

	/**
	 * Переопределяет метод hashCode() для класса Teacher.
	 *
	 * @return хэш-код объекта.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.getId());
	}

	/**
	 * Переопределяет метод toString() для класса Teacher.
	 *
	 * @return строковое представление объекта Teacher.
	 */
	@Override
	public String toString() {
		return "Teacher{" +
				"id='" + getId() + '\'' +
				", имя='" + getFirstName() + " " + getLastName() + '\'' +
				", возраст=" + getAge() +
				", статус=" + (isStatus() ? "Активен" : "Неактивен") +
				", исследователь=" + (isResearcher() ? "Да" : "Нет") +
				", hireDate=" + getHireDate() +
				", type=" + type +
				", rate=" + rate +
				", faculty=" + faculty +
				", lessonsCount=" + lessons.size() +
				'}';
	}

	/**
	 * Метод для управления курсами преподавателя.
	 * Реализуйте логику управления курсами здесь.
	 */
	public void manageCourse() {
		// Реализуйте логику управления курсами (например, добавление/удаление курсов)
	}

	/**
	 * Метод для просмотра рейтинга преподавателя.
	 */
	public void viewRate() {
		System.out.println("Рейтинг преподавателя " + this.getFirstName() + " " + this.getLastName() + ": " + this.rate);
	}

	@Override
	public void viewMenu() {

	}


	// Дополнительные методы, если необходимо
}


