package UniversitySystem;

import java.util.Objects;

/**
 * Класс Complaint представляет жалобу студента с описанием, уровнем срочности и ссылкой на студента.
 */
public class Complaint {

	private String description;
	private UrgencyLevel urgency;
	private Student student;

	/**
	 * Конструктор класса Complaint.
	 *
	 * @param description Описание жалобы.
	 * @param student     Студент, подавший жалобу.
	 * @param urgency     Уровень срочности жалобы.
	 */
	public Complaint(String description, Student student, UrgencyLevel urgency) {
		this.description = description;
		this.student = student;
		this.urgency = urgency;
	}

	// Геттеры и сеттеры

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public UrgencyLevel getUrgency() {
		return urgency;
	}

	public void setUrgency(UrgencyLevel urgency) {
		this.urgency = urgency;
	}

	/**
	 * Проверяет уровень срочности жалобы.
	 *
	 * @return Уровень срочности.
	 */
	public UrgencyLevel checkUrgency() {
		// Например, можно добавить логику для обработки срочности
		return this.urgency;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Complaint)) return false;
		Complaint complaint = (Complaint) o;
		return Objects.equals(getDescription(), complaint.getDescription()) &&
				getUrgency() == complaint.getUrgency() &&
				Objects.equals(getStudent(), complaint.getStudent());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getDescription(), getUrgency(), getStudent());
	}

	@Override
	public String toString() {
		return "Complaint{" +
				"description='" + description + '\'' +
				", urgency=" + urgency +
				", student=" + student +
				'}';
	}
}

