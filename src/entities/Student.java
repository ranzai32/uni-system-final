package entities;

import common.*;
import enums.*;
import users.*;
import decorators.*;
import exceptions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static database.DataBase.logger;

/**
 * Класс Student представляет студента в системе.
 */
public class Student extends User {
	private Degree degree;
	private Faculty faculty;
	private int year;
	private ResearcherDecorator supervisor;
	private double gpa;
	private List<Course> courses;
	private Transcript transcript;
	private LocalDate graduationDate;
	private List<Lesson> lessons;
	private Map<String, Complaint> complaints;
	private Organization organization; // Связь с организацией
    private int CreditsForSemester;
	/**
	 * Конструктор класса Student.
	 *
	 * @param id             Уникальный идентификатор студента.
	 * @param password       Пароль студента.
	 * @param firstName      Имя студента.
	 * @param lastName       Фамилия студента.
	 * @param age            Возраст студента.
	 * @param isResearcher   Является ли студент исследователем.
	 * @param degree         Степень студента.
	 * @param faculty        Факультет студента.
	 * @param year           Год обучения.
	 * @param gpa            Средний балл.
	 * @param courses        Список курсов студента.
	 * @param transcript     Транскрипт студента.
	 * @param graduationDate Дата выпуска.
	 * @param lessons        Список уроков.
	 * @param complaints     Список жалоб.
	 */
	public Student(String id, String password, String firstName, String lastName, int age, boolean isResearcher,
				   Degree degree, Faculty faculty, int year, double gpa, List<Course> courses,
				   Transcript transcript, LocalDate graduationDate, List<Lesson> lessons,
				   Map<String, Complaint> complaints) {
		super(id, password, firstName, lastName, age, isResearcher);
		this.degree = degree;
		this.faculty = faculty;
		this.year = year;
		this.gpa = gpa;
		this.courses = new ArrayList<>(courses);
		this.transcript = transcript;
		this.graduationDate = graduationDate;
		this.lessons = new ArrayList<>(lessons);
		this.complaints = complaints;
		this.organization = null;
		this.CreditsForSemester = 30;
	}

	
	public void assignSupervisor(ResearcherDecorator supervisor) throws InvalidSupervisorException, InvalidStudentYearException {
	    if (this.year != 4) {
	        throw new InvalidStudentYearException("Only 4th-year students can have a supervisor.");
	    }
	    if (supervisor.getHIndex() < 3) {
	        throw new InvalidSupervisorException("Supervisor's h-index is less than 3. Cannot assign as research supervisor.");
	    }
	    this.supervisor = supervisor;
	    System.out.println("Supervisor assigned successfully: " + supervisor.getUser().getFullName());
	}


	
	// Геттеры и сеттеры

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		if (degree == null) {
			throw new IllegalArgumentException("Степень не может быть null.");
		}
		this.degree = degree;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if (year <= 0) {
			throw new IllegalArgumentException("Год обучения должен быть положительным числом.");
		}
		this.year = year;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		if (gpa < 0.0 || gpa > 4.0) {
			throw new IllegalArgumentException("GPA должно быть между 0.0 и 4.0.");
		}
		this.gpa = gpa;
	}

	/**
	 * Получает неизменяемый список курсов студента.
	 *
	 * @return список курсов.
	 */
	public List<Course> getCourses() {
		return Collections.unmodifiableList(courses);
	}

	/**
	 * Устанавливает список курсов студента.
	 *
	 * @param courses Список курсов.
	 * @throws IllegalArgumentException если список курсов null.
	 */
	public void setCourses(List<Course> courses) {
		if (courses == null) {
			throw new IllegalArgumentException("Список курсов не может быть null.");
		}
		this.courses = new ArrayList<>(courses);
	}

	public Transcript getTranscript() {
		return transcript;
	}

	public void setTranscript(Transcript transcript) {
		if (transcript == null) {
			throw new IllegalArgumentException("Транскрипт не может быть null.");
		}
		this.transcript = transcript;
	}

	public LocalDate getGraduationDate() {
		return graduationDate;
	}

	public void setGraduationDate(LocalDate graduationDate) {
		if (graduationDate == null) {
			throw new IllegalArgumentException("Дата выпуска не может быть null.");
		}
		this.graduationDate = graduationDate;
	}

	public int getCreditsForSemester() {
		return CreditsForSemester;
	}

	public void setCreditsForSemester(int creditsForSemester) {
		this.CreditsForSemester = creditsForSemester;
	}

	public List<Lesson> getLessons() {
		return Collections.unmodifiableList(lessons);
	}

	public void setLessons(List<Lesson> lessons) {
		if (lessons == null) {
			throw new IllegalArgumentException("Список уроков не может быть null.");
		}
		this.lessons = new ArrayList<>(lessons);
	}

	public Map<String, Complaint> getComplaints() {
		return Collections.unmodifiableMap(complaints);
	}

	public void setComplaints(Map<String, Complaint> complaints) {
		if (complaints == null) {
			throw new IllegalArgumentException("Список жалоб не может быть null.");
		}
		this.complaints = complaints;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		if (organization == null) {
			throw new IllegalArgumentException("Организация не может быть null.");
		}
		this.organization = organization;
	}

	public boolean removeCourse(String courseCode) {
		if (courseCode == null || courseCode.isEmpty()) {
			throw new IllegalArgumentException("Код курса не может быть пустым.");
		}
		for (Course course : courses) {
			if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
				courses.remove(course);
				Admin.getInstance().addLog("Студент " + this.getId() + " удалил курс: " + course.getCourseName());
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Student student = (Student) o;

		return getId().equals(student.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public String toString() {
		return "Student{" +
				"degree=" + degree +
				", faculty=" + faculty +
				", year=" + year +
				", gpa=" + gpa +
				", courses=" + courses +
				", transcript=" + transcript +
				", graduationDate=" + graduationDate +
				", lessons=" + lessons +
				", complaints=" + complaints +
				", organization=" + organization +
				'}';
	}
}
