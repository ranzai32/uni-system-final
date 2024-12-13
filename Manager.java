//package UniversitySystem;
//
//import java.time.LocalDate;
//import java.util.List;
//
//
///**
// * Класс Manager представляет менеджера в системе и расширяет класс Employee.
// */
////public class Manager extends Employee {
//
//	private TypeManager type;
//
//	/**
//	 * Конструктор класса Manager.
//	 *
//	 * @param id           Уникальный идентификатор менеджера.
//	 * @param password     Пароль менеджера.
//	 * @param firstName    Имя менеджера.
//	 * @param lastName     Фамилия менеджера.
//	 * @param age          Возраст менеджера.
//	 * @param isResearcher Является ли менеджер исследователем.
//	 * @param hireDate     Дата найма менеджера.
//	 * @param type         Тип менеджера.
//	 * @throws IllegalArgumentException если тип менеджера null.
//	 */
//	public Manager(String id, List<Message> messages, boolean isResearcher, boolean status, int age, String lastName, String firstName, String password, LocalDate hireDate, List<Order> orders, TypeManager type) {
//		super(id, messages, isResearcher, status, age, lastName, firstName, password, hireDate, orders);
//		this.type = type;
//	}
//
//	/**
//	 * Получает тип менеджера.
//	 *
//	 * @return тип менеджера.
//	 */
//	public TypeManager getType() {
//		return type;
//	}
//
//	/**
//	 * Устанавливает тип менеджера.
//	 *
//	 * @param type Новый тип менеджера.
//	 * @throws IllegalArgumentException если тип менеджера null.
//	 */
//	public void setType(TypeManager type) {
//		if (type == null) {
//			throw new IllegalArgumentException("Тип менеджера не может быть null.");
//		}
//		this.type = type;
//	}
//
//	/**
//	 * Добавляет новый курс в систему.
//	 *
//	 * @param course Курс для добавления.
//	 */
//	public void addCourse(Course course) {
//		if (course == null) {
//			throw new IllegalArgumentException("Курс не может быть null.");
//		}
//		DataBase db = DataBase.getInstance();
//		boolean success = db.addCourse(course);
//		if (success) {
//			Admin.getInstance().addLog("Менеджер " + this.getId() + " добавил курс: " + course.getCourseName());
//			System.out.println("Курс добавлен успешно.");
//		} else {
//			System.out.println("Не удалось добавить курс. Возможно, курс уже существует.");
//		}
//	}
//
//	/**
//	 * Принимает или отклоняет запрос на добавление/удаление курса для студента.
//	 *
//	 * @param student    Студент, для которого происходит запрос.
//	 * @param courseAdd  Курс для добавления.
//	 * @param courseDrop Курс для удаления.
//	 */
////	public void acceptAddDrop(Student student, Course courseAdd, Course courseDrop) {
////		if (student == null) {
////			throw new IllegalArgumentException("Студент не может быть null.");
////		}
////		if (courseAdd == null && courseDrop == null) {
////			System.out.println("Нечего добавить или удалить.");
////			return;
////		}
////
////		DataBase db = DataBase.getInstance();
////
////		if (courseAdd != null) {
////			boolean added = db.assignCourseToStudent(student.getId(), courseAdd.getCourseCode());
////			if (added) {
////				Admin.getInstance().addLog("Менеджер " + this.getId() + " добавил курс " + courseAdd.getCourseCode() + " студенту " + student.getId());
////				System.out.println("Курс " + courseAdd.getCourseName() + " добавлен студенту.");
////			} else {
////				System.out.println("Не удалось добавить курс. Возможно, курс уже назначен студенту.");
////			}
////		}
////
////		if (courseDrop != null) {
////			boolean removed = db.removeCourseFromStudent(student.getId(), courseDrop.getCourseCode());
////			if (removed) {
////				Admin.getInstance().addLog("Менеджер " + this.getId() + " удалил курс " + courseDrop.getCourseCode() + " студенту " + student.getId());
////				System.out.println("Курс " + courseDrop.getCourseName() + " удален у студента.");
////			} else {
////				System.out.println("Не удалось удалить курс. Возможно, курс не назначен студенту.");
////			}
////		}
////	}
//
//	/**
//	 * Назначает курс преподавателю.
//	 *
//	 * @param teacher Преподаватель, которому назначается курс.
//	 * @param course  Курс для назначения.
//	 */
////	public void assignCourseToTeacher(Teacher teacher, Course course) {
////		if (teacher == null) {
////			throw new IllegalArgumentException("Преподаватель не может быть null.");
////		}
////		if (course == null) {
////			throw new IllegalArgumentException("Курс не может быть null.");
////		}
////
////		DataBase db = DataBase.getInstance();
////		boolean success = db.assignCourseToTeacher(teacher.getId(), course.getCourseCode());
////		if (success) {
////			Admin.getInstance().addLog("Менеджер " + this.getId() + " назначил курс " + course.getCourseCode() + " преподавателю " + teacher.getId());
////			System.out.println("Курс назначен преподавателю успешно.");
////		} else {
////			System.out.println("Не удалось назначить курс. Возможно, курс уже назначен преподавателю.");
////		}
////	}
//
//	/**
//	 * Просматривает курсы, назначенные студенту.
//	 *
//	 * @param student Студент, курсы которого просматриваются.
//	 */
//	public void viewStudentCourses(Student student) {
//		if (student == null) {
//			throw new IllegalArgumentException("Студент не может быть null.");
//		}
//		List<Course> courses = student.getCourses();
//		if (courses.isEmpty()) {
//			System.out.println("У студента " + student.getFirstName() + " " + student.getLastName() + " нет назначенных курсов.");
//		} else {
//			System.out.println("Курсы студента " + student.getFirstName() + " " + student.getLastName() + ":");
//			for (Course course : courses) {
//				System.out.println("- " + course.getCourseName() + " (" + course.getCourseCode() + ")");
//			}
//		}
//	}
//
//	/**
//	 * Просматривает транскрипт студента.
//	 *
//	 * @param student Студент, транскрипт которого просматривается.
//	 */
//	public void viewStudentTranscript(Student student) {
//		if (student == null) {
//			throw new IllegalArgumentException("Студент не может быть null.");
//		}
//		Transcript transcript = student.getTranscript();
//		if (transcript == null) {
//			System.out.println("Транскрипт студента не доступен.");
//		} else {
//			System.out.println("Транскрипт студента " + student.getFirstName() + " " + student.getLastName() + ":");
//			System.out.println(transcript);
//		}
//	}
//
//	/**
//	 * Просматривает аттестацию студента.
//	 *
//	 * @param student Студент, аттестация которого просматривается.
//	 */
////	public void viewAttestation(Student student) {
////		if (student == null) {
////			throw new IllegalArgumentException("Студент не может быть null.");
////		}
////		Attestation attestation = student.getAttestation();
////		if (attestation == null) {
////			System.out.println("Аттестация студента не доступна.");
////		} else {
////			System.out.println("Аттестация студента " + student.getFirstName() + " " + student.getLastName() + ":");
////			System.out.println(attestation);
////		}
////	}
//
//	/**
//	 * Просматривает расписание студента.
//	 *
//	 * @param student Студент, расписание которого просматривается.
//	 */
////	public void viewSchedule(Student student) {
////		if (student == null) {
////			throw new IllegalArgumentException("Студент не может быть null.");
////		}
////		Schedule schedule = student.getSchedule();
////		if (schedule == null) {
////			System.out.println("Расписание студента не доступно.");
////		} else {
////			System.out.println("Расписание студента " + student.getFirstName() + " " + student.getLastName() + ":");
////			System.out.println(schedule);
////		}
////	}
//
//	/**
//	 * Просматривает все запросы менеджера.
//	 *
//	 * @param requestType Тип запроса для просмотра.
//	 */
////	public void seeRequest(RequestType requestType) {
////		if (requestType == null) {
////			throw new IllegalArgumentException("Тип запроса не может быть null.");
////		}
////		List<Request> requests = DataBase.getInstance().getRequestsByType(requestType);
////		if (requests.isEmpty()) {
////			System.out.println("Нет запросов типа: " + requestType);
////		} else {
////			System.out.println("Запросы типа " + requestType + ":");
////			for (Request request : requests) {
////				System.out.println(request);
////			}
////		}
////	}
//
//	/**
//	 * Переопределяет метод toString() для класса Manager.
//	 *
//	 * @return строковое представление объекта Manager.
//	 */
//	@Override
//	public String toString() {
//		return "Manager{" +
//				"type=" + type +
//				'}';
//	}
//	// При необходимости, переопределите методы equals() и hashCode()
//}

