package UniversitySystem.entities;

public class RegistrationRequest {
    private static int requestCounter = 0; // Генератор ID для заявок

    private final String requestId;
    private final Student student;
    private final Course course;

    public RegistrationRequest(Student student, Course course) {
        this.requestId = "REQ-" + (++requestCounter);
        this.student = student;
        this.course = course;
    }

    public String getRequestId() {
        return requestId;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "requestId='" + requestId + '\'' +
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}

