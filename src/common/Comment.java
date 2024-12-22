package common;

import users.User;
import java.util.Objects;
import java.time.LocalDateTime;

public class Comment {

	private User user; // Пользователь, оставивший комментарий
	private LocalDateTime date; // Дата комментария
	private News news; // Новость, к которой относится комментарий
	private String content; // Содержимое комментария

	// Конструктор
	public Comment(User user, String content, News news) {
		this.user = user;
		this.content = content;
		this.news = news;
		this.date = LocalDateTime.now(); // Устанавливаем текущую дату
	}

	// Геттеры и сеттеры
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public LocalDateTime getDate() {
		return date;
	}

	// Переопределение методов equals и hashCode
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Comment)) return false;
		Comment comment = (Comment) o;
		return Objects.equals(getUser(), comment.getUser()) &&
				Objects.equals(getDate(), comment.getDate()) &&
				Objects.equals(getNews(), comment.getNews()) &&
				Objects.equals(getContent(), comment.getContent());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUser(), getDate(), getNews(), getContent());
	}

	// Переопределение метода toString
	@Override
	public String toString() {
		return "Comment{" +
				"user=" + user +
				", date=" + date +
				", news=" + news +
				", content='" + content + '\'' +
				'}';
	}
}
