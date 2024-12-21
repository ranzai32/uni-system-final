package UniversitySystem.entities;

import UniversitySystem.DataBase;
import UniversitySystem.users.User;

import java.util.HashMap;
import java.util.Vector;

/**
 * Класс Admin отвечает за управление пользователями и логированием в системе.
 * Реализован как синглтон.
 */
public class Admin extends User {

	private HashMap<String, Vector<String>> requests;
	private Vector<String> logFiles;

	// Единственный экземпляр Admin
	private static Admin instance = null;

	// Приватный конструктор для реализации паттерна Singleton
	private Admin(String id, String password, String firstName, String lastName, int age, boolean isResearcher) {
		super(id, password, firstName, lastName, age, isResearcher);
		this.requests = new HashMap<>();
		this.logFiles = new Vector<>();
	}

	/**
	 * Метод для получения единственного экземпляра Admin.
	 *
	 * @return экземпляр Admin.
	 */
	public static synchronized Admin getInstance() {
		if (instance == null) {
			// Получаем базу данных и ищем администратора с ID "admin"
			DataBase db = DataBase.getInstance();
			Admin admin = db.findAdminById("admin");
			if (admin == null) {
				// Создаем начального администратора, если его нет
				admin = new Admin("admin", "adminpass", "Админ", "Системы", 35, true);
				db.addAdmin(admin);
				admin.setStatus(true); // Администратор всегда онлайн
			}
			instance = admin;
		}
		return instance;
	}

	// Геттеры и сеттеры

	public HashMap<String, Vector<String>> getRequests() {
		return requests;
	}

	public void setRequests(HashMap<String, Vector<String>> requests) {
		this.requests = requests;
	}

	public Vector<String> getLogFiles() {
		return logFiles;
	}

	public void setLogFiles(Vector<String> logFiles) {
		this.logFiles = logFiles;
	}

	// Методы для управления пользователями через DataBase

	/**
	 * Добавляет пользователя в базу данных.
	 *
	 * @param user Пользователь для добавления.
	 */
	public void addUser(User user) {
		DataBase db = DataBase.getInstance();
		if (user instanceof Student) {
			db.addStudent((Student) user);
		} else if (user instanceof Teacher) {
			db.addTeacher((Teacher) user);
		} else if (user instanceof Admin) {
			db.addAdmin((Admin) user);
//		} else if (user instanceof Manager) {
//			db.addManager((Manager) user); //
//		} else if (user instanceof Dean) {
//			db.addDean((Dean) user); //
		} else {
			System.out.println("Неизвестный тип пользователя.");
		}
	}

	/**
	 * Удаляет пользователя из базы данных по ID.
	 *
	 * @param userId ID пользователя для удаления.
	 * @return true, если удаление прошло успешно, иначе false.
	 */
	public boolean removeUser(String userId) {
		DataBase db = DataBase.getInstance();
		if (db.removeStudent(userId)) return true;
		if (db.removeTeacher(userId)) return true;
		if (db.removeAdmin(userId)) return true;
//		if (db.removeManager(userId)) return true; //
//		if (db.removeDean(userId)) return true; //
		System.out.println("Пользователь с ID " + userId + " не найден.");
		return false;
	}

	/**
	 * Ищет пользователя по ID.
	 *
	 * @param userId ID пользователя.
	 * @return объект User, если найден, иначе null.
	 */
	public User findUserById(String userId) {
		DataBase db = DataBase.getInstance();
		User user = db.findStudentById(userId);
		if (user != null) return user;
		user = db.findTeacherById(userId);
		if (user != null) return user;
		user = db.findAdminById(userId);
		if (user != null) return user;
//		user = db.findManagerById(userId); //
//		if (user != null) return user;
//		user = db.findDeanById(userId); //
		return user;
	}

	// Методы для логирования

	/**
	 * Добавляет запись в лог-файл.
	 *
	 * @param log Запись для добавления.
	 */
	public void addLog(String log) {
		if(log != null && !log.isEmpty()) {
			logFiles.add(log);
			System.out.println("Лог добавлен: " + log);
		}
	}

	/**
	 * Просматривает все лог-файлы.
	 */
	public void seeLogFiles() {
		if(logFiles.isEmpty()) {
			System.out.println("Нет лог-файлов.");
		} else {
			System.out.println("----- Лог-файлы системы -----");
			for(String log : logFiles) {
				System.out.println(log);
			}
			System.out.println("------------------------------");
		}
	}

	/**
	 * Просматривает все запросы пользователей.
	 */
	public void seeRequests() {
		if(requests.isEmpty()) {
			System.out.println("Нет запросов пользователей.");
		} else {
			System.out.println("----- Запросы пользователей -----");
			for(String userId : requests.keySet()) {
				System.out.println("Пользователь ID: " + userId);
				Vector<String> userRequests = requests.get(userId);
				for(String req : userRequests) {
					System.out.println("- " + req);
				}
			}
			System.out.println("---------------------------------");
		}
	}

	// Переопределение метода toString для администратора
	@Override
	public String toString() {
		return "Admin{" +
				"id='" + getId() + '\'' +
				", имя='" + getFirstName() + " " + getLastName() + '\'' +
				", возраст=" + getAge() +
				", статус=" + (isStatus() ? "Онлайн" : "Оффлайн") +
				", исследователь=" + (isResearcher() ? "Да" : "Нет") +
				", запросов=" + requests.size() +
				", лог-файлов=" + logFiles.size() +
				'}';
	}
}

