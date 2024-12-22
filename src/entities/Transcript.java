package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Transcript {

	private List<TranscriptEntry> entries; // Список записей транскрипта
	private double totalGPA;
	private int totalCredits;

	public Transcript() {
		this.entries = new ArrayList<>();
		this.totalGPA = 0.0;
		this.totalCredits = 0;
	}

	public void addGrade(String studentId, Course course, double numericGrade) {
		String letterGrade = GradeConverter.calculateLetterGrade(numericGrade);
		TranscriptEntry entry = new TranscriptEntry(studentId, course, numericGrade, letterGrade);
		entries.add(entry); // Добавляем запись в список

		// Проверка содержимого списка
		System.out.println("Список записей в транскрипте:");
		for (TranscriptEntry e : entries) {
			System.out.println(e);
		}

		// Обновляем GPA
		totalCredits += course.getCredits();
		totalGPA = calculateTotalGPA();
	}




	private double calculateTotalGPA() {
		double gradeSum = 0.0;
		for (TranscriptEntry entry : entries) {
			// Умножаем числовую оценку на количество кредитов курса
			gradeSum += entry.getNumericGrade() * entry.getCourseCredits();
		}
		// Возвращаем средний балл (учитывая кредиты)
		return totalCredits == 0 ? 0.0 : gradeSum / totalCredits;
	}


	@Override
	public String toString() {
		return "Transcript{" +
				"entries=" + entries +
				", totalGPA=" + totalGPA +
				", totalCredits=" + totalCredits +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Transcript that)) return false;
        return Double.compare(totalGPA, that.totalGPA) == 0 && totalCredits == that.totalCredits && Objects.equals(entries, that.entries);
	}

	@Override
	public int hashCode() {
		return Objects.hash(entries, totalGPA, totalCredits);
	}

	public void printTranscript() {
		System.out.println("Transcript:");
		if (entries.isEmpty()) {
			System.out.println("No grades available.");
			return;
		}

		for (TranscriptEntry entry : entries) {
			System.out.println("Student ID: " + entry.getStudentId() +
					", Course ID: " + entry.getCourseId() +
					", Course Name: " + entry.getCourseName() +
					", Numeric Grade: " + entry.getNumericGrade() +
					", Letter Grade: " + entry.getLetterGrade());
		}
		System.out.println("Total GPA: " + totalGPA);


	}

}
