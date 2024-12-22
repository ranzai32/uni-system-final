package services;

import common.Comment;
import common.News;
import database.DataBase;
import entities.*;
import enums.*;
import decorators.ResearcherDecorator;
import interfaces.ManageNews;

import java.time.LocalDate;
import java.util.Date;
import java.util.Vector;

public class ResearchTest implements ManageNews {

    // Реализация методов интерфейса ManageNews
    @Override
    public void makeNew(String title, String topic, String content, Languages language) {
        System.out.println("Новость создана:");
        System.out.println("Заголовок: " + title);
        System.out.println("Тема: " + topic);
        System.out.println("Контент: " + content);
        System.out.println("Язык: " + language);
    }

    @Override
    public void deleteNew(News n) {
        if (n != null) {
            System.out.println("Новость удалена: " + n.getTitle());
        } else {
            System.out.println("Новость не найдена.");
        }
    }

    @Override
    public void deleteComment(Comment c) {
        if (c != null) {
            System.out.println("Комментарий удалён: " + c.getContent() + " от " + c.getUser().getFullName());
        } else {
            System.out.println("Комментарий не найден.");
        }
    }

    public static void main(String[] args) {
        // Создание преподавателей

        DataBase db = DataBase.getInstance();
        Teacher teacher1 = new Teacher(
                "T001",
                new Vector<>(),
                false,
                true,
                45,
                "Иван",
                "Иванов",
                "teach123", LocalDate.of(2015, 1, 15),
                new Vector<>(),
                TypeTeacher.Tutor,
                4.5,
                null,
                Faculty.FIT
        );

        Teacher teacher2 = new Teacher(
                "T002",
                new Vector<>(),
                false,
                true,
                40,
                "Анна",
                "Петрова",
                "teach456",
                LocalDate.of(2015, 1, 15),
                new Vector<>(),
                TypeTeacher.Tutor,
                4.8,
                null,
                Faculty.FIT
        );

        // Создание декораторов для преподавателей
        ResearcherDecorator researcher1 = new ResearcherDecorator(teacher1, Faculty.FIT);
        ResearcherDecorator researcher2 = new ResearcherDecorator(teacher2, Faculty.FIT);

        // Создание научной работы
        ResearchPaper paper1 = new ResearchPaper(
                "Исследование искусственного интеллекта",
                15,
                new Date(),
                25,
                "Контент научной работы",
                "Artificial Intelligence"
        );

        // Добавление авторов в научную работу
        paper1.addMember(researcher1);
        paper1.addMember(researcher2);

        // Печать информации о научной работе
        System.out.println("Научная работа:");
        System.out.println(paper1);

        // Создание научного проекта
        Vector<ResearcherDecorator> projectMembers = new Vector<>();
        projectMembers.add(researcher1);
        projectMembers.add(researcher2);

        Vector<ResearchPaper> papers = new Vector<>();
        papers.add(paper1);

        ResearchProject project = new ResearchProject("Artificial Intelligence", projectMembers, papers);

        // Печать информации о проекте
        System.out.println("\nНаучный проект:");
        System.out.println(project);

        // Тестирование интерфейса ManageNews
        ResearchTest test = new ResearchTest();
        test.makeNew("Прогресс в ИИ", "AI Research", "Новые достижения в области ИИ", Languages.EN);

        // Создание и удаление новостей и комментариев
        News news = new News("Прогресс в ИИ", "AI Research", "Новые достижения в области ИИ", Languages.EN, teacher1);
        Comment comment = new Comment(teacher2, "Отличная работа!", news);

        test.deleteNew(news);
        test.deleteComment(comment);

        db.saveAllListsToFiles();
    }
}
