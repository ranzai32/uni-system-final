package UniversitySystem;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Класс Organization управляет студентами, включая главу и членов организации.
 */
public class Organization {

	private Student head;
	private List<Student> members;
	private String name;

	/**
	 * Конструктор класса Organization.
	 *
	 * @param name Название организации.
	 * @param head Глава организации.
	 * @throws IllegalArgumentException если название или глава null или пустые.
	 */
	public Organization(String name, Student head) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Название организации не может быть пустым.");
		}
		if (head == null) {
			throw new IllegalArgumentException("Глава организации не может быть null.");
		}
		this.name = name;
		this.head = head;
		this.members = new ArrayList<>();
		this.members.add(head); // Добавляем главу как первого члена
		head.setOrganization(this); // Устанавливаем связь в Student
		Admin.getInstance().addLog("Создана организация " + this.name + " с главой " + head.getId());
	}

	/**
	 * Добавляет нового члена в организацию.
	 *
	 * @param student Студент для добавления.
	 * @return true, если студент успешно добавлен, иначе false.
	 */
	public boolean addMember(Student student) {
		if (student == null) {
			throw new IllegalArgumentException("Студент не может быть null.");
		}
		if (members.contains(student)) {
			System.out.println("Студент уже является членом организации.");
			return false;
		}
		members.add(student);
		student.setOrganization(this); // Устанавливаем связь в Student
		Admin.getInstance().addLog("Студент " + student.getId() + " добавлен в организацию " + this.name);
		return true;
	}

	/**
	 * Получает список членов организации.
	 *
	 * @return неизменяемый список членов.
	 */
	public List<Student> getMembers() {
		return Collections.unmodifiableList(members);
	}

	/**
	 * Получает название организации.
	 *
	 * @return название организации.
	 */
	public String getOrgName() {
		return name;
	}

	/**
	 * Устанавливает название организации.
	 *
	 * @param name Новое название организации.
	 * @throws IllegalArgumentException если название пустое.
	 */
	public void setName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Название организации не может быть пустым.");
		}
		this.name = name;
		Admin.getInstance().addLog("Название организации изменено на " + this.name);
	}

	/**
	 * Устанавливает главу организации.
	 *
	 * @param head Новый глава организации.
	 * @throws IllegalArgumentException если глава null.
	 */
	public void setHead(Student head) {
		if (head == null) {
			throw new IllegalArgumentException("Глава организации не может быть null.");
		}
		this.head = head;
		if (!members.contains(head)) {
			addMember(head);
		}
		Admin.getInstance().addLog("Глава организации изменен на " + head.getId());
	}

	/**
	 * Получает главу организации.
	 *
	 * @return глава организации.
	 */
	public Student getHead() {
		return head;
	}

	@Override
	public String toString() {
		return "Organization{" +
				"name='" + name + '\'' +
				", head=" + head.getFirstName() + " " + head.getLastName() +
				", membersCount=" + members.size() +
				'}';
	}
}
