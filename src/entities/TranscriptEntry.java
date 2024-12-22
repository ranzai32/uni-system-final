package entities;

public class TranscriptEntry {

    private String studentId;
    private String courseId;
    private String courseName;
    private double numericGrade;
    private String letterGrade;
    private int courseCredits; // Хранит кредиты курса

    public TranscriptEntry(String studentId, String courseId, String courseName, double numericGrade, String letterGrade, int courseCredits) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.numericGrade = numericGrade;
        this.letterGrade = letterGrade;
        this.courseCredits = courseCredits;
    }

    // Используем конструктор, принимающий объект Course
    public TranscriptEntry(String studentId, Course course, double numericGrade, String letterGrade) {
        this.studentId = studentId;
        this.courseId = course.getCourseCode();
        this.courseName = course.getCourseName();
        this.numericGrade = numericGrade;
        this.letterGrade = letterGrade;
        this.courseCredits = course.getCredits(); // Извлекаем кредиты из курса
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getNumericGrade() {
        return numericGrade;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public int getCourseCredits() {
        return courseCredits;
    }

    @Override
    public String toString() {
        return "TranscriptEntry{" +
                "studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", numericGrade=" + numericGrade +
                ", letterGrade='" + letterGrade + '\'' +
                ", courseCredits=" + courseCredits +
                '}';
    }
}
