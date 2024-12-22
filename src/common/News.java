package common;

import enums.Languages;
import database.DataBase;
import users.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Vector;

public class News {

	private String title;
	private String content;
	private Languages language = Languages.EN;
	private LocalDateTime date;
	private String topic;
	private User author;
	private Vector<Comment> comments;

	public News(String title, String topic, String content, Languages language, User autho) {
		this.title = title;
		this.topic = topic;
		this.author = author;
		this.content = content;
		this.language = language;
		this.date = LocalDateTime.now();
		this.comments = new Vector<>();
		boolean added = DataBase.getInstance().addNews(this);
	    if (!added) {
	        System.err.println("Ошибка при добавлении новости в базу данных: " + this.title);
	    }
	}

	// Геттеры и сеттеры

	public String getTitle() {
		return title;
	}
	
	public User getAuthor() {
		return author;
	}
	
	public void setTitle(String title) {
		if(title != null && !title.isEmpty()) {
			this.title = title;
		}
	}

	public Vector<Comment> getComments() {
		return comments;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		if(topic != null && !topic.isEmpty()) {
			this.topic = topic;
		}
	}

	public LocalDateTime getDate() {
		return date;
	}


	public Languages getLanguage() {
		return language;
	}

	public void setLanguage(Languages language) {
		if(language != null) {
			this.language = language;
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if(content != null && !content.isEmpty()) {
			this.content = content;
		}
	}

	// Методы для работы с комментариями

	public void addComment(Comment comment) {
		if(comment != null) {
			comments.add(comment);
		}
	}

	public void removeComment(Comment comment) {
		if(comment != null) {
			comments.remove(comment);
		}
	}

	// Дополнительные методы

	public void changeLanguage(Languages l) {
		setLanguage(l);
	}

	public void viewComments() {
		if(comments.isEmpty()) {
			System.out.println("Нет комментариев.");
		} else {
			for(Comment comment : comments) {
				System.out.println(comment);
			}
		}
	}

	// equals, hashCode и toString

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof News)) return false;
		News news = (News) o;
		return Objects.equals(getTitle(), news.getTitle()) &&
				Objects.equals(getContent(), news.getContent()) &&
				getLanguage() == news.getLanguage() &&
				Objects.equals(getDate(), news.getDate()) &&
				Objects.equals(getTopic(), news.getTopic()) &&
				Objects.equals(getComments(), news.getComments());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getTitle(), getContent(), getLanguage(), getDate(), getTopic(), getComments());
	}

	@Override
	public String toString() {
		return "News{" +
				"заголовок='" + title + '\'' +
				", содержание='" + content + '\'' +
				", язык=" + language +
				", дата=" + date +
				", тема='" + topic + '\'' +
				'}';
	}
}
