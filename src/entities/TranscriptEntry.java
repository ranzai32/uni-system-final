package entities;

public class TranscriptEntry {

    private String studentId;
    private String courseId;
    private String courseName;
    private double firstAttestation;  // Первая аттестация (30 баллов)
    private double secondAttestation; // Вторая аттестация (30 баллов)
    private double finalExam;         // Финальный экзамен (40 баллов)
    private String letterGrade;
    private int courseCredits;

    public TranscriptEntry(String studentId, String courseId, String courseName, double firstAttestation, double secondAttestation, double finalExam, String letterGrade, int courseCredits) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.firstAttestation = firstAttestation;
        this.secondAttestation = secondAttestation;
        this.finalExam = finalExam;
        this.letterGrade = letterGrade;
        this.courseCredits = courseCredits;
    }

    // Метод для вычисления общей оценки (total score)
    public double getTotalScore() {
        return firstAttestation + secondAttestation + finalExam;
    }

    // Геттеры и сеттеры
    public String getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getFirstAttestation() {
        return firstAttestation;
    }

    public void setFirstAttestation(double firstAttestation) {
        if (firstAttestation < 0 || firstAttestation > 30) {
            throw new IllegalArgumentException("Баллы первой аттестации должны быть в пределах от 0 до 30.");
        }
        this.firstAttestation = firstAttestation;
    }

    public double getSecondAttestation() {
        return secondAttestation;
    }

    public void setSecondAttestation(double secondAttestation) {
        if (secondAttestation < 0 || secondAttestation > 30) {
            throw new IllegalArgumentException("Баллы второй аттестации должны быть в пределах от 0 до 30.");
        }
        this.secondAttestation = secondAttestation;
    }

    public double getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(double finalExam) {
        if (finalExam < 0 || finalExam > 40) {
            throw new IllegalArgumentException("Баллы финального экзамена должны быть в пределах от 0 до 40.");
        }
        this.finalExam = finalExam;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
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
                ", firstAttestation=" + firstAttestation +
                ", secondAttestation=" + secondAttestation +
                ", finalExam=" + finalExam +
                ", totalScore=" + getTotalScore() +
                ", letterGrade='" + letterGrade + '\'' +
                ", courseCredits=" + courseCredits +
                '}';
    }
}
