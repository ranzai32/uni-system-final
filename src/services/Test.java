package services;

import entities.Course;
import entities.Transcript;
import enums.Faculty;
import enums.TypeCourse;

public class Test {
    public static void main(String[] args) {
        Transcript transcript = new Transcript();
        Course course = new Course("CS101", "Программирование", 3, TypeCourse.Required, Faculty.FIT, 50);

        // Добавляем оценки
        transcript.addGrade("S001", course, 28, 25, 35);
        transcript.addGrade("S002", course, 30, 30, 40);
        transcript.addGrade("S001", course, 0, 0, 5);
        // Печатаем транскрипт
        transcript.printTranscript();
    }

}
