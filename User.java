package UniversitySystem;

import java.util.List;
import java.util.Objects;
import java.util.Vector;

/**
 * Абстрактный класс User является базовым для всех типов пользователей в системе.
 */
public abstract class User {
	private String id;
	private String password;
	private String firstName;
	private String lastName;
	private int age;
	private boolean status; // Онлайн/Оффлайн
	private boolean isResearcher;
	private Vector<Message> messages;
	private Vector<Order> orders;

	public User(String id, String password, String firstName, String lastName, int age, boolean isResearcher) {
		this.id = id;
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setAge(age);
		this.status = false;
		this.isResearcher = isResearcher;
		this.messages = new Vector<>();
		this.orders = new Vector<>();
	}

	public User(String id, List<Message> messages, boolean isResearcher, boolean status, int age, String lastName, String firstName, String password, List<Order> orders) {
	}

	// Геттеры и сеттеры

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(id != null && !id.isEmpty()) {
			this.id = id;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password != null && password.length() >= 6) { // Простая проверка пароля
			this.password = password;
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName != null && !firstName.isEmpty()) {
			this.firstName = firstName;
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName != null && !lastName.isEmpty()) {
			this.lastName = lastName;
		}
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if(age > 0) {
			this.age = age;
		}
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isResearcher() {
		return isResearcher;
	}

	public void setResearcher(boolean researcher) {
		isResearcher = researcher;
	}

	public Vector<Message> getMessages() {
		return messages;
	}

	public void setMessages(Vector<Message> messages) {
		if(messages != null) {
			this.messages = messages;
		}
	}

	public Vector<Order> getOrders() {
		return orders;
	}

	public void setOrders(Vector<Order> orders) {
		if(orders != null) {
			this.orders = orders;
		}
	}

	// Переопределение методов equals, hashCode и toString

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		return age == user.age &&
				status == user.status &&
				isResearcher == user.isResearcher &&
				Objects.equals(id, user.id) &&
				Objects.equals(password, user.password) &&
				Objects.equals(firstName, user.firstName) &&
				Objects.equals(lastName, user.lastName) &&
				Objects.equals(messages, user.messages) &&
				Objects.equals(orders, user.orders);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, password, firstName, lastName, age, status, isResearcher, messages, orders);
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", имя='" + firstName + " " + lastName + '\'' +
				", возраст=" + age +
				", статус=" + (status ? "Онлайн" : "Оффлайн") +
				", исследователь=" + (isResearcher ? "Да" : "Нет") +
				", сообщений=" + messages.size() +
				", заказов=" + orders.size() +
				'}';
	}

	// Методы

	/**
	 * Выводит меню пользователя.
	 */
	public void viewMenu() {
		System.out.println("----- Меню пользователя -----");
		System.out.println("1. Просмотреть профиль");
		System.out.println("2. Отправить сообщение");
		System.out.println("3. Просмотреть сообщения");
		System.out.println("4. Просмотреть новости");
		System.out.println("5. Написать комментарий");
		System.out.println("6. Удалить комментарий");
		System.out.println("7. Создать заказ");
		System.out.println("8. Выйти");
		System.out.println("-----------------------------");
	}

	/**
	 * Выводит информацию о пользователе.
	 */
	public void viewInfo() {
		System.out.println("----- Информация пользователя -----");
		System.out.println("ID: " + id);
		System.out.println("Имя: " + firstName + " " + lastName);
		System.out.println("Возраст: " + age);
		System.out.println("Исследователь: " + (isResearcher ? "Да" : "Нет"));
		System.out.println("Статус: " + (status ? "Онлайн" : "Оффлайн"));
		System.out.println("-----------------------------------");
	}

	/**
	 * Отправляет сообщение другому пользователю.
	 *
	 * @param m  Сообщение для отправки.
	 * @param to Получатель сообщения.
	 */
	public void sendMessage(Message m, User to) {
		if (to != null) {
			to.getMessages().add(m);
			System.out.println("Сообщение отправлено пользователю " + to.getFirstName() + " " + to.getLastName() + ".");
			Admin.getInstance().addLog("Пользователь " + this.getId() + " отправил сообщение пользователю " + to.getId() + ".");
		} else {
			System.out.println("Получатель не найден.");
		}
	}

	/**
	 * Просматривает все сообщения пользователя.
	 */
	public void viewMessages() {
		if (messages.isEmpty()) {
			System.out.println("У вас нет сообщений.");
		} else {
			System.out.println("----- Ваши сообщения -----");
			for (Message msg : messages) {
				System.out.println(msg);
			}
			System.out.println("---------------------------");
		}
	}

	/**
	 * Просматривает все новости.
	 */
	public void viewNews() {
		Vector<News> allNews = News.getAllNews();
		if (allNews.isEmpty()) {
			System.out.println("Нет новостей.");
		} else {
			System.out.println("----- Новости университета -----");
			for (News news : allNews) {
				System.out.println(news);
				System.out.println("Комментарии:");
				news.viewComments();
				System.out.println("-------------------------------");
			}
		}
	}

	/**
	 * Пишет комментарий к новости.
	 *
	 * @param content  Текст комментария.
	 * @param newsItem Новость, к которой пишется комментарий.
	 */
	public void writeComment(String content, News newsItem) {
		if (newsItem != null && content != null && !content.isEmpty()) {
			Comment comment = new Comment(this, content, newsItem);
			newsItem.addComment(comment);
			System.out.println("Комментарий добавлен к новости: " + newsItem.getTitle());
			Admin.getInstance().addLog("Пользователь " + this.getId() + " добавил комментарий к новости " + newsItem.getTitle() + ".");
		} else {
			System.out.println("Не удалось добавить комментарий.");
		}
	}

	/**
	 * Удаляет комментарий из новости.
	 *
	 * @param c Комментарий для удаления.
	 */
	public void deleteComment(Comment c) {
		if (c != null) {
			News news = c.getNews();
			if (news != null && c.getUser().equals(this)) {
				news.removeComment(c);
				System.out.println("Комментарий удалён.");
				Admin.getInstance().addLog("Пользователь " + this.getId() + " удалил комментарий из новости " + news.getTitle() + ".");
			} else {
				System.out.println("Вы можете удалить только свои комментарии или новость не найдена.");
			}
		} else {
			System.out.println("Комментарий не найден.");
		}
	}

	/**
	 * Создаёт заказ.
	 *
	 * @param description Описание заказа.
	 */
	public void createOrder(String description) {
		if (description != null && !description.isEmpty()) {
			Order order = new Order(OrderStatus.PENDING, description);
			this.orders.add(order);
			System.out.println("Заказ создан: " + order);
			Admin.getInstance().addLog("Пользователь " + this.getId() + " создал заказ: " + description + ".");
		} else {
			System.out.println("Описание заказа не может быть пустым.");
		}
	}
}
