package UniversitySystem.entities;

import UniversitySystem.enums.Faculty;
import UniversitySystem.common.Lesson;
import UniversitySystem.enums.TypeCourse;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Класс Course представляет курс в системе.
 */
public class Course {

	private String name;
	private int credits;
	private TypeCourse type;
	private Faculty faculty;
	private List<Lesson> lessons;
	private String courseCode;
	private int maxStudentNumber;
	private List<Teacher> teachers;

	/**
	 * Конструктор класса Course.
	 *
	 * @param courseCode       Уникальный код курса.
	 * @param name             Название курса.
	 * @param credits          Количество кредитов.
	 * @param type             Тип курса (например, лекция, лабораторное занятие).
	 * @param faculty          Факультет, к которому относится курс.
	 * @param maxStudentNumber Максимальное количество студентов в курсе.
	 */
	public Course(String courseCode, String name, int credits, TypeCourse type, Faculty faculty, int maxStudentNumber) {
		this.courseCode = courseCode;
		this.name = name;
		this.credits = credits;
		this.type = type;
		this.faculty = faculty;
		this.maxStudentNumber = maxStudentNumber;
		this.lessons = new ArrayList<>();
		this.teachers = new ArrayList<>();
	}

	// Геттеры и сеттеры

	public String getCourseCode() {
		return courseCode;
	}

	public String getCourseName() {
		return name;
	}

	public int getCredits() {
		return credits;
	}

	public TypeCourse getType() {
		return type;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public List<Lesson> getLessons() {
		return Collections.unmodifiableList(lessons);
	}

	public int getMaxStudentNumber() {
		return maxStudentNumber;
	}

	public List<Teacher> getTeachers() {
		return Collections.unmodifiableList(teachers);
	}

	/**
	 * Добавляет учителя в курс.
	 *
	 * @param t Учитель для добавления.
	 */
	public void addTeacher(Teacher t) {
		if (t == null) {
			throw new IllegalArgumentException("Учитель не может быть null.");
		}
		if (teachers.contains(t)) {
			System.out.println("Учитель уже назначен на этот курс.");
			return;
		}
		teachers.add(t);
		Admin.getInstance().addLog("Учитель " + t.getId() + " добавлен в курс " + this.courseCode);
		System.out.println("Учитель " + t.getFirstName() + " " + t.getLastName() + " добавлен в курс " + this.name);
	}

	/**
	 * Выводит список учителей, назначенных на курс.
	 */
	public void viewTeachers() {
		if (teachers.isEmpty()) {
			System.out.println("На курс " + this.name + " не назначено ни одного учителя.");
			return;
		}
		System.out.println("Учителя курса " + this.name + ":");
		for (Teacher t : teachers) {
			System.out.println("- " + t.getFirstName() + " " + t.getLastName() + " (ID: " + t.getId() + ")");
		}
	}

	@Override
	public String toString() {
		return "Course{" +
				"courseCode='" + courseCode + '\'' +
				", name='" + name + '\'' +
				", credits=" + credits +
				", type=" + type +
				", faculty=" + faculty +
				", lessons=" + lessons.size() +
				", maxStudentNumber=" + maxStudentNumber +
				", teachers=" + teachers.size() +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Course course = (Course) o;

		return courseCode.equals(course.courseCode);
	}

	@Override
	public int hashCode() {
		return courseCode.hashCode();
	}
}
