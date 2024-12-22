package common;

import users.User;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {

	private User sender;
	private User recipient;
	private String message;
	private LocalDateTime date;

	public Message(User sender, User recipient, String message) {
		this.sender = sender;
		this.recipient = recipient;
		this.message = message;
		this.date = LocalDateTime.now();
	}

	// Геттеры и сеттеры

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// equals, hashCode и toString

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Message)) return false;
		Message message1 = (Message) o;
		return Objects.equals(getSender(), message1.getSender()) &&
				Objects.equals(getRecipient(), message1.getRecipient()) &&
				Objects.equals(getMessage(), message1.getMessage()) &&
				Objects.equals(getDate(), message1.getDate());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getSender(), getRecipient(), getMessage(), getDate());
	}

	@Override
	public String toString() {
		return "Message{" +
				"от='" + sender.getId() + '\'' +
				", сообщение='" + message + '\'' +
				", дата=" + date +
				", получатель='" + recipient.getId() + '\'' +
				'}';
	}
}

