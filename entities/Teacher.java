package UniversitySystem.entities;

import UniversitySystem.common.Message;
import UniversitySystem.common.Order;
import UniversitySystem.enums.Faculty;
import UniversitySystem.enums.TypeTeacher;
import UniversitySystem.common.Lesson;
import UniversitySystem.users.Employee;

import java.time.LocalDate;
import java.util.*;

/**
 * Класс Teacher представляет преподавателя в системе и расширяет класс Employee.
 */
public class Teacher extends Employee {

	private TypeTeacher type;
	private double rate;
	private Map<Lesson, List<Student>> lessons;
	private Faculty faculty;
	private List<Course> courses; // Добавлено поле для курсов

	/**
	 * Конструктор класса Teacher.
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
		this.courses = new ArrayList<>(); // Инициализация списка курсов
	}

	// Методы для управления курсами

	/**
	 * Добавляет курс преподавателю.
	 *
	 * @param course Курс для добавления.
	 * @return true, если курс успешно добавлен, иначе false.
	 */
	public boolean addCourse(Course course) {
		if (course == null) {
			throw new IllegalArgumentException("Курс не может быть null.");
		}
		if (courses.contains(course)) {
			return false; // Курс уже назначен преподавателю
		}
		courses.add(course);
		return true;
	}

	/**
	 * Удаляет курс у преподавателя.
	 *
	 * @param course Курс для удаления.
	 * @return true, если курс успешно удален, иначе false.
	 */
	public boolean removeCourse(Course course) {
		return courses.remove(course);
	}

	/**
	 * Получает список курсов преподавателя.
	 *
	 * @return список курсов.
	 */
	public List<Course> getCourses() {
		return new ArrayList<>(courses); // Возвращаем копию списка для безопасности
	}

	// Остальные геттеры и сеттеры остаются без изменений
	public TypeTeacher getType() {
		return type;
	}

	public void setType(TypeTeacher type) {
		if (type == null) {
			throw new IllegalArgumentException("Тип преподавателя не может быть null.");
		}
		this.type = type;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		if (rate < 0.0 || rate > 5.0) {
			throw new IllegalArgumentException("Рейтинг должен быть между 0.0 и 5.0.");
		}
		this.rate = rate;
	}

	public Map<Lesson, List<Student>> getLessons() {
		return new HashMap<>(lessons);
	}

	public void setLessons(Map<Lesson, List<Student>> lessons) {
		if (lessons == null) {
			throw new IllegalArgumentException("Расписание уроков не может быть null.");
		}
		this.lessons = new HashMap<>(lessons);
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		if (faculty == null) {
			throw new IllegalArgumentException("Факультет не может быть null.");
		}
		this.faculty = faculty;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Teacher)) return false;
		Teacher teacher = (Teacher) o;
		return Objects.equals(this.getId(), teacher.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId());
	}

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
				", courses=" + courses +
				'}';
	}

	public void viewRate() {
		System.out.println("Рейтинг преподавателя " + this.getFirstName() + " " + this.getLastName() + ": " + this.rate);
	}
}
