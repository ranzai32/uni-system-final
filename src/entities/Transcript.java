package entities;

import java.util.ArrayList;
import java.util.List;

public class Transcript {

	private List<TranscriptEntry> entries; // Список записей транскрипта
	private double totalGPA; // Общее значение GPA
	private int totalCredits; // Общее количество кредитов

	public Transcript() {
		this.entries = new ArrayList<>();
		this.totalGPA = 0.0;
		this.totalCredits = 0;
	}

	// Добавление новой оценки
	public void addGrade(String studentId, Course course, double numericGrade) {
		String letterGrade = GradeConverter.calculateLetterGrade(numericGrade);
		TranscriptEntry entry = new TranscriptEntry(studentId, course, numericGrade, letterGrade);
		entries.add(entry);

		// Обновляем общее количество кредитов
		totalCredits += course.getCredits();

		// Пересчитываем общий GPA
		totalGPA = calculateTotalGPA();

		System.out.println("Оценка добавлена в транскрипт: " + studentId + ", Курс: " + course.getCourseName() + ", Оценка: " + numericGrade);
	}

	// Расчет общего GPA
	private double calculateTotalGPA() {
		double gradeSum = 0.0;
		for (TranscriptEntry entry : entries) {
			gradeSum += entry.getNumericGrade() * entry.getCourseCredits();
		}
		return totalCredits == 0 ? 0.0 : gradeSum / totalCredits;
	}

	// Печать транскрипта
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
