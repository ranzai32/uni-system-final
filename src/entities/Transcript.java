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

	// Добавление новой записи в транскрипт
	public void addGrade(String studentId, Course course, double firstAttestation, double secondAttestation, double finalExam) {
		// Найти запись для данного курса и студента, если она уже существует
		for (TranscriptEntry entry : entries) {
			if (entry.getStudentId().equals(studentId) && entry.getCourseId().equals(course.getCourseCode())) {
				// Суммируем баллы
				double newFirst = Math.min(30, entry.getFirstAttestation() + firstAttestation); // Макс. 30
				double newSecond = Math.min(30, entry.getSecondAttestation() + secondAttestation); // Макс. 30
				double newFinal = Math.min(40, entry.getFinalExam() + finalExam); // Макс. 40

				entry.setFirstAttestation(newFirst);
				entry.setSecondAttestation(newSecond);
				entry.setFinalExam(newFinal);

				// Обновляем итоговую буквенную оценку
				double totalScore = entry.getTotalScore();
				entry.setLetterGrade(utils.GradeConverter.calculateLetterGrade(totalScore));

				// Пересчитываем GPA
				totalGPA = calculateTotalGPA();

				System.out.println("Оценка обновлена в транскрипте: " + studentId + ", Курс: " + course.getCourseName() + ", Общая оценка: " + totalScore);
				return;
			}
		}

		// Если запись не найдена, создаем новую
		double totalScore = firstAttestation + secondAttestation + finalExam;
		String letterGrade = utils.GradeConverter.calculateLetterGrade(totalScore);

		TranscriptEntry entry = new TranscriptEntry(
				studentId,
				course.getCourseCode(),
				course.getCourseName(),
				firstAttestation,
				secondAttestation,
				finalExam,
				letterGrade,
				course.getCredits()
		);
		entries.add(entry);

		// Обновляем GPA
		totalCredits += course.getCredits();
		totalGPA = calculateTotalGPA();

		System.out.println("Оценка добавлена в транскрипт: " + studentId + ", Курс: " + course.getCourseName() + ", Общая оценка: " + totalScore);
	}

	// Расчет общего GPA
	private double calculateTotalGPA() {
		double gpaSum = 0.0;
		for (TranscriptEntry entry : entries) {
			double normalizedScore = entry.getTotalScore() / 100.0 * 4.0; // Нормализация на 4.0
			gpaSum += normalizedScore * entry.getCourseCredits(); // Учитываем кредиты курса
		}
		return totalCredits == 0 ? 0.0 : gpaSum / totalCredits; // Средний GPA с учетом кредитов
	}


	// Печать транскрипт
	public void printTranscript() {
		System.out.println("Transcript:");
		if (entries.isEmpty()) {
			System.out.println("No grades available.");
			return;
		}

		for (TranscriptEntry entry : entries) {
			System.out.println(entry);
		}
		System.out.println("Total GPA: " + totalGPA);
	}
}
