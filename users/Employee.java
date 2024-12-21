package UniversitySystem.users;

import UniversitySystem.common.Message;
import UniversitySystem.common.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Абстрактный класс Employee представляет сотрудников университета.
 */
public class Employee extends User {

	private LocalDate hireDate;

	/**
	 * Конструктор класса Employee.
	 *
	 * @param hireDate     Дата найма сотрудника.
	 * @throws IllegalArgumentException если hireDate null.
	 */
	public Employee(String id, List<Message> messages, boolean isResearcher, boolean status, int age, String lastName, String firstName, String password, LocalDate hireDate, List<Order> orders) {
		super(id, messages, isResearcher, status, age, lastName, firstName, password, orders);
//		private String id;
//		private String password;
//		private String firstName;
//		private String lastName;
//		private int age;
//		private boolean status; // Онлайн/Оффлайн
//		private boolean isResearcher;
//		private Vector<Message> messages;
//		private Vector<Order> orders;
		if (hireDate == null) {
			throw new IllegalArgumentException("Дата найма не может быть null.");
		}
		this.hireDate = hireDate;
	}

	/**
	 * Получает дату найма сотрудника.
	 *
	 * @return дата найма.
	 */
	public LocalDate getHireDate() {
		return hireDate;
	}

	/**
	 * Устанавливает дату найма сотрудника.
	 *
	 * @param hireDate новая дата найма.
	 * @throws IllegalArgumentException если hireDate null.
	 */
	public void setHireDate(LocalDate hireDate) {
		if (hireDate == null) {
			throw new IllegalArgumentException("Дата найма не может быть null.");
		}
		this.hireDate = hireDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Employee employee)) return false;
		if (!super.equals(o)) return false;
		return Objects.equals(getHireDate(), employee.getHireDate());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getHireDate());
	}

	@Override
	public String toString() {
		return "Employee{" +
				"hireDate=" + hireDate +
				", " + super.toString() +
				'}';
	}

    /**
     * Отображает меню сотрудника.
     */
    public void viewMenu() {

    }

    /**
     * Отображает информацию о сотруднике.
     */
    public void viewInfo() {

    }
}

