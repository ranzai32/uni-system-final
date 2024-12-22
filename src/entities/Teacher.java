package entities;

import common.*;
import database.DataBase;
import enums.*;
import users.*;

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

	public void addGrade(String studentId, Course course, double firstAttestation, double secondAttestation, double finalExam) {
		DataBase db = DataBase.getInstance();

		// Проверка: существует ли студент
		Student student = db.findStudentById(studentId);
		if (student == null) {
			throw new IllegalArgumentException("Student with ID " + studentId + " does not exist.");
		}

		// Проверка: является ли преподаватель назначенным на курс
		if (!course.getTeachers().contains(this)) {
			throw new IllegalArgumentException("Teacher is not assigned to the course: " + course.getCourseCode());
		}

		// Проверка: назначен ли курс студенту
		if (!student.getCourses().contains(course)) {
			throw new IllegalArgumentException("Course " + course.getCourseCode() + " is not assigned to the student: " + studentId);
		}

		// Проверка: существует ли курс в базе данных
		if (!db.getAllCourses().contains(course)) {
			throw new IllegalArgumentException("Course " + course.getCourseCode() + " does not exist in the database.");
		}

		// Добавляем оценку в транскрипт через базу данных
		db.getTranscriptByStudentId(studentId).addGrade(studentId, course, firstAttestation, secondAttestation, finalExam);

		// Выводим информацию о добавлении
		System.out.println("Оценка добавлена в транскрипт: " + studentId +
				", Курс: " + course.getCourseName() +
				", Первая аттестация: " + firstAttestation +
				", Вторая аттестация: " + secondAttestation +
				", Финальный экзамен: " + finalExam);
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

	public void getTranscript(Student student) {
		if (student == null) {
			System.out.println("Студент не найден.");
			return;
		}

		Transcript transcript = student.getTranscript();
		if (transcript == null) {
			System.out.println("Транскрипт для студента " + student.getFirstName() + " " + student.getLastName() + " недоступен.");
			return;
		}

		System.out.println("Транскрипт студента " + student.getFirstName() + " " + student.getLastName() + ":");
		transcript.printTranscript();
	}

	public void viewRate() {
		System.out.println("Рейтинг преподавателя " + this.getFirstName() + " " + this.getLastName() + ": " + this.rate);
	}
}