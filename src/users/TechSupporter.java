package users;
import common.Message;
import common.Order;
import enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class TechSupporter extends Employee {

	public TechSupporter(String id, List<Message> messages, boolean isResearcher, boolean status, int age, String lastName, String firstName, String password, LocalDate hireDate, List<Order> orders, Vector<Order> acceptdedOrders, Vector<Order> newOrders, Vector<Order> completedOrders, Vector<Order> rejectedOrders) {
		super(id, messages, isResearcher, status, age, lastName, firstName, password, hireDate, orders);
		this.acceptdedOrders = acceptdedOrders;
		this.newOrders = newOrders;
		this.completedOrders = completedOrders;
		this.rejectedOrders = rejectedOrders;
	}

	public Vector<Order> getAcceptdedOrders() {

		return acceptdedOrders;
	}

	public void setAcceptdedOrders(Vector<Order> acceptdedOrders) {
		this.acceptdedOrders = acceptdedOrders;
	}

	public Vector<Order> getRejectedOrders() {
		return rejectedOrders;
	}

	public void setRejectedOrders(Vector<Order> rejectedOrders) {
		this.rejectedOrders = rejectedOrders;
	}

	public Vector<Order> getCompletedOrders() {
		return completedOrders;
	}

	public void setCompletedOrders(Vector<Order> completedOrders) {
		this.completedOrders = completedOrders;
	}

	public Vector<Order> getNewOrders() {
		return newOrders;
	}

	public void setNewOrders(Vector<Order> newOrders) {
		this.newOrders = newOrders;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TechSupporter that)) return false;
		if (!super.equals(o)) return false;
        return Objects.equals(getAcceptdedOrders(), that.getAcceptdedOrders()) && Objects.equals(getNewOrders(), that.getNewOrders()) && Objects.equals(getCompletedOrders(), that.getCompletedOrders()) && Objects.equals(getRejectedOrders(), that.getRejectedOrders());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getAcceptdedOrders(), getNewOrders(), getCompletedOrders(), getRejectedOrders());
	}

	@Override
	public String toString() {
		return "TechSupporter{" +
				"acceptdedOrders=" + acceptdedOrders +
				", newOrders=" + newOrders +
				", completedOrders=" + completedOrders +
				", rejectedOrders=" + rejectedOrders +
				'}';
	}

	private Vector<Order> acceptdedOrders;

	private Vector<Order> newOrders;

	private Vector<Order> completedOrders;

	private Vector<Order> rejectedOrders;

	public void viewNewOrder() {

	}

	public void viewAcceptedOrder() {

	}

	public void updateOrder(Order o, OrderStatus s) {

	}

	public void viewRejectedOrder() {

	}

	public void viewCompletedOrders() {

	}

}
