package database;


import decorators.ResearcherDecorator;
import entities.*;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import common.News;

/**
 * Класс DataBase является синглтоном и служит центральным хранилищем данных для системы университета.
 */
public class DataBase {

	public static final Logger logger = Logger.getLogger(DataBase.class.getName());

	// Единственный экземпляр DataBase
	private static volatile DataBase instance = null;
	
	private final Vector<News> allNews = new Vector<>();
	// Хранилища различных типов пользователей и объектов
	private final List<Teacher> allTeachers;
	//    private final List<Manager> allManagers;
	private final List<Admin> allAdmins;
	//    private final List<Dean> allDeans;
	private final List<Organization> allOrganizations;
	private final List<ResearcherDecorator> allResearchers;
	private final List<RegistrationRequest> registrationRequests;
	private List<Student> allStudents = new ArrayList<>();
	private List<Course> allCourses = new ArrayList<>();
	private List<Transcript> allTranscripts;

	// Приватный конструктор для предотвращения создания экземпляров извне
	private DataBase() {
		allStudents = new CopyOnWriteArrayList<>();
		allTeachers = new CopyOnWriteArrayList<>();
//        allManagers = new CopyOnWriteArrayList<>();
		allAdmins = new CopyOnWriteArrayList<>();
//        allDeans = new CopyOnWriteArrayList<>();
		allOrganizations = new CopyOnWriteArrayList<>();
		allResearchers = new CopyOnWriteArrayList<>();
		allCourses = new CopyOnWriteArrayList<>();
		allTranscripts = new CopyOnWriteArrayList<>();
		logger.setLevel(Level.INFO);
		registrationRequests = new CopyOnWriteArrayList<>();
	}

	/**
	 * Метод для получения единственного экземпляра DataBase.
	 *
	 * @return экземпляр DataBase.
	 */
	public static DataBase getInstance() {
		if (instance == null) {
			synchronized (DataBase.class) {
				if (instance == null) {
					instance = new DataBase();
					logger.info("Создан новый экземпляр DataBase.");
				}
			}
		}
		return instance;
	}

	// Методы для работы со студентами

	/**
	 * Получает все студенты.
	 *
	 * @return неизменяемый список всех студентов.
	 */
	public List<Student> getAllStudents() {
		return Collections.unmodifiableList(allStudents);
	}

	/**
	 * Добавляет нового студента.
	 *
	 * @param student объект Student для добавления.
	 * @return true, если студент добавлен, иначе false.
	 */
	public boolean addStudent(Student student) {
		if (student == null) {
			logger.warning("Попытка добавить null студента.");
			return false;
		}
		if (allStudents.contains(student)) {
			logger.warning("Студент с ID " + student.getId() + " уже существует.");
			return false;
		}
		allStudents.add(student);
		logger.info("Студент " + student.getId() + " добавлен в базу данных.");
		return true;
	}

	/**
	 * Удаляет студента по ID.
	 *
	 * @param studentId ID студента для удаления.
	 * @return true, если удаление прошло успешно, иначе false.
	 */
	public boolean removeStudent(String studentId) {
		Student student = findStudentById(studentId);
		if (student != null) {
			allStudents.remove(student);
			logger.info("Студент " + studentId + " удален из базы данных.");
			return true;
		}
		logger.warning("Студент " + studentId + " не найден.");
		return false;
	}

	/**
	 * Ищет студента по ID.
	 *
	 * @param studentId ID студента.
	 * @return объект Student, если найден, иначе null.
	 */
	public Student findStudentById(String studentId) {
		for (Student s : allStudents) {
			if (s.getId().equalsIgnoreCase(studentId)) {
				return s;
			}
		}
		return null;
	}

	public List<RegistrationRequest> getRegistrationRequests() {
		return registrationRequests;
	}

	public boolean submitRegistrationRequest(String studentId, String courseCode) {
		Student student = findStudentById(studentId);
		Course course = findCourseByCode(courseCode);

		if (student == null) {
			logger.warning("Студент с ID " + studentId + " не найден.");
			return false;
		}
		if (course == null) {
			logger.warning("Курс с кодом " + courseCode + " не найден.");
			return false;
		}

		RegistrationRequest request = new RegistrationRequest(student, course);
		registrationRequests.add(request); // Добавление заявки в список
		logger.info("Заявка на регистрацию студента " + studentId + " на курс " + courseCode + " отправлена.");
		return true;
	}

	public boolean assignCourseToStudent(String studentId, String courseCode) {
		Student student = findStudentById(studentId);
		Course course = findCourseByCode(courseCode);

		if (student == null || course == null) {
			logger.warning("Студент или курс не найдены.");
			return false;
		}

		// Создаем изменяемую копию списка курсов
		List<Course> modifiableCourses = new ArrayList<>(student.getCourses());
		if (modifiableCourses.contains(course)) {
			logger.warning("Курс уже назначен студенту.");
			return false;
		}

		modifiableCourses.add(course); // Добавляем курс
		student.setCourses(modifiableCourses); // Устанавливаем новый список
		logger.info("Курс " + course.getCourseName() + " назначен студенту " + student.getId());
		return true;
	}




	// Методы для работы с преподавателями

	public List<Teacher> getAllTeachers() {
		return Collections.unmodifiableList(allTeachers);
	}

	public boolean addTeacher(Teacher teacher) {
		if (teacher == null) {
			logger.warning("Попытка добавить null преподавателя.");
			return false;
		}
		if (allTeachers.contains(teacher)) {
			logger.warning("Преподаватель с ID " + teacher.getId() + " уже существует.");
			return false;
		}
		allTeachers.add(teacher);
		logger.info("Преподаватель " + teacher.getId() + " добавлен в базу данных.");
		return true;
	}

	public boolean removeTeacher(String teacherId) {
		Teacher teacher = findTeacherById(teacherId);
		if (teacher != null) {
			allTeachers.remove(teacher);
			logger.info("Преподаватель " + teacherId + " удален из базы данных.");
			return true;
		}
		logger.warning("Преподаватель " + teacherId + " не найден.");
		return false;
	}

	public Teacher findTeacherById(String teacherId) {
		for (Teacher t : allTeachers) {
			if (t.getId().equalsIgnoreCase(teacherId)) {
				return t;
			}
		}
		return null;
	}

	// Методы для работы с менеджерами

//    public List<Manager> getAllManagers() {
//        return Collections.unmodifiableList(allManagers);
//    }

//    public boolean addManager(Manager manager) {
//        if (manager == null) {
//            logger.warning("Попытка добавить null менеджера.");
//            return false;
//        }
//        if (allManagers.contains(manager)) {
//            logger.warning("Менеджер с ID " + manager.getId() + " уже существует.");
//            return false;
//        }
//        allManagers.add(manager);
//        logger.info("Менеджер " + manager.getId() + " добавлен в базу данных.");
//        return true;
//    }

//    public boolean removeManager(String managerId) {
//        Manager manager = findManagerById(managerId);
//        if (manager != null) {
//            allManagers.remove(manager);
//            logger.info("Менеджер " + managerId + " удален из базы данных.");
//            return true;
//        }
//        logger.warning("Менеджер " + managerId + " не найден.");
//        return false;
//    }

//    public Manager findManagerById(String managerId) {
//        for (Manager m : allManagers) {
//            if (m.getId().equalsIgnoreCase(managerId)) {
//                return m;
//            }
//        }
//        return null;
//    }

	// Методы для работы с администраторами

	public List<Admin> getAllAdmins() {
		return Collections.unmodifiableList(allAdmins);
	}

	public boolean addAdmin(Admin admin) {
		if (admin == null) {
			logger.warning("Попытка добавить null администратора.");
			return false;
		}
		if (allAdmins.contains(admin)) {
			logger.warning("Администратор с ID " + admin.getId() + " уже существует.");
			return false;
		}
		allAdmins.add(admin);
		logger.info("Администратор " + admin.getId() + " добавлен в базу данных.");
		return true;
	}

	public boolean removeAdmin(String adminId) {
		Admin admin = findAdminById(adminId);
		if (admin != null) {
			allAdmins.remove(admin);
			logger.info("Администратор " + adminId + " удален из базы данных.");
			return true;
		}
		logger.warning("Администратор " + adminId + " не найден.");
		return false;
	}

	public Admin findAdminById(String adminId) {
		for (Admin a : allAdmins) {
			if (a.getId().equalsIgnoreCase(adminId)) {
				return a;
			}
		}
		return null;
	}

	// Методы для работы с декораторами исследователей

	public List<ResearcherDecorator> getAllResearchers() {
		return Collections.unmodifiableList(allResearchers);
	}




	// Методы для работы с курсами

	public List<Course> getAllCourses() {
		return Collections.unmodifiableList(allCourses);
	}

	public boolean addCourse(Course course) {
		if (course == null) {
			logger.warning("Попытка добавить null курса.");
			return false;
		}
		if (allCourses.contains(course)) {
			logger.warning("Курс с кодом " + course.getCourseCode() + " уже существует.");
			return false;
		}
		allCourses.add(course);
		logger.info("Курс " + course.getCourseName() + " добавлен в базу данных.");
		return true;
	}

	public boolean removeCourse(String courseCode) {
		Course course = findCourseByCode(courseCode);
		if (course != null) {
			allCourses.remove(course);
			logger.info("Курс " + courseCode + " удален из базы данных.");
			return true;
		}
		logger.warning("Курс " + courseCode + " не найден.");
		return false;
	}

	public Course findCourseByCode(String courseCode) {
		for (Course c : allCourses) {
			if (c.getCourseCode().equalsIgnoreCase(courseCode)) {
				return c;
			}
		}
		return null;
	}

	// Методы для работы с деканами

//    public List<Dean> getAllDeans() {
//        return Collections.unmodifiableList(allDeans);
//    }

//    public boolean addDean(Dean dean) {
//        if (dean == null) {
//            logger.warning("Попытка добавить null декана.");
//            return false;
//        }
//        if (allDeans.contains(dean)) {
//            logger.warning("Декан с ID " + dean.getId() + " уже существует.");
//            return false;
//        }
//        allDeans.add(dean);
//        logger.info("Декан " + dean.getId() + " добавлен в базу данных.");
//        return true;
//    }

//    public boolean removeDean(String deanId) {
//        Dean dean = findDeanById(deanId);
//        if (dean != null) {
//            allDeans.remove(dean);
//            logger.info("Декан " + deanId + " удален из базы данных.");
//            return true;
//        }
//        logger.warning("Декан " + deanId + " не найден.");
//        return false;
//    }
//
//    public Dean findDeanById(String deanId) {
//        for (Dean d : allDeans) {
//            if (d.getId().equalsIgnoreCase(deanId)) {
//                return d;
//            }
//        }
//        return null;
//    }

	// Методы для работы с организациями

	public List<Organization> getAllOrganizations() {
		return Collections.unmodifiableList(allOrganizations);
	}
	
	
	
	public boolean addNews(News news) {
	    if (news == null) {
	        logger.warning("Попытка добавить null новость.");
	        return false;
	    }
	    allNews.add(news);
	    logger.info("Новость добавлена: " + news.getTitle());
	    return true;
	}
	
	
	public boolean deleteNew(News n) {
	    if (n == null) {
	        logger.warning("Попытка удалить null новость.");
	        return false;
	    }
	    if (allNews.contains(n)) {
	        allNews.remove(n);
	        logger.info("Новость удалена: " + n.getTitle());
	        return true;
	    } else {
	        logger.warning("Новость не найдена: " + n.getTitle());
	        return false;
	    }
	}
	
	public Vector<News> getAllNews(){
		return allNews;
	}

	public boolean addOrganization(Organization organization) {
		if (organization == null) {
			logger.warning("Попытка добавить null организации.");
			return false;
		}
		if (allOrganizations.contains(organization)) {
			logger.warning("Организация с именем " + organization.getOrgName() + " уже существует.");
			return false;
		}
		allOrganizations.add(organization);
		logger.info("Организация " + organization.getOrgName() + " добавлена в базу данных.");
		return true;
	}

	public boolean removeOrganization(String organizationName) {
		Organization organization = findOrganizationByName(organizationName);
		if (organization != null) {
			allOrganizations.remove(organization);
			logger.info("Организация " + organizationName + " удалена из базы данных.");
			return true;
		}
		logger.warning("Организация " + organizationName + " не найдена.");
		return false;
	}

	public Organization findOrganizationByName(String organizationName) {
		for (Organization o : allOrganizations) {
			if (o.getOrgName().equalsIgnoreCase(organizationName)) {
				return o;
			}
		}
		return null;
	}

//	// Пример сохранения данных в JSON// не использовать
//	public void saveDataToJson(String filePath) {
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		try (FileWriter writer = new FileWriter(filePath)) {
//			gson.toJson(this, writer);
//			logger.info("Данные успешно сохранены в JSON файл: " + filePath);
//		} catch (IOException e) {
//			logger.severe("Ошибка при сохранении данных в JSON файл: " + e.getMessage());
//		}
//	}

	private <T> void saveListToFile(String fileName, List<T> dataList) {
		try (FileWriter writer = new FileWriter(fileName)) {
			for (T item : dataList) {
				writer.write(item.toString() + System.lineSeparator());
			}
			logger.info("Данные успешно сохранены в файл: " + fileName);
		} catch (IOException e) {
			logger.severe("Ошибка при записи в файл " + fileName + ": " + e.getMessage());
		}
	}

	/**
	 * Сохраняет все списки в отдельные файлы.
	 */
	public void saveAllListsToFiles() {
		saveListToFile("students.txt", allStudents);
		saveListToFile("teachers.txt", allTeachers);
		saveListToFile("courses.txt", allCourses);
		saveListToFile("organizations.txt", allOrganizations);
		saveListToFile("admins.txt", allAdmins);
		saveListToFile("researchers.txt", allResearchers);
		saveListToFile("registration_requests.txt", registrationRequests);
		saveListToFile("transcripts.txt", allTranscripts);
	}

}

