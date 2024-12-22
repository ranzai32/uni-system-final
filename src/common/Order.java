package common;

import enums.OrderStatus;

import java.util.Objects;

public class Order {

	private static int nextOrderID = 1;

	private int orderID;
	private OrderStatus status;
	private String description;

	public Order(OrderStatus status, String description) {
		this.orderID = nextOrderID++;
		this.status = status;
		this.description = description;
	}

	// Геттеры и сеттеры

	public int getOrderID() {
		return orderID;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// Метод обновления статуса заказа

	public void updateStatus(OrderStatus newStatus) {
		this.status = newStatus;
	}

	// equals, hashCode и toString

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Order)) return false;
		Order order = (Order) o;
		return getOrderID() == order.getOrderID() &&
				getStatus() == order.getStatus() &&
				Objects.equals(getDescription(), order.getDescription());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getOrderID(), getStatus(), getDescription());
	}

	@Override
	public String toString() {
		return "Order{" +
				"orderID=" + orderID +
				", status=" + status +
				", description='" + description + '\'' +
				'}';
	}
}
