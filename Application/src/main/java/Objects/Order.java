package Objects;

import java.time.LocalDateTime;
import java.io.Serializable;

public class Order implements Serializable {
    private String ID;
    private LocalDateTime orderDate;
    private String status;
    private Payment payment;
    private Cart cart;

    // Constructors
    public Order(String ID, LocalDateTime orderDate, String status, Payment payment, Cart cart) {
        this.ID = ID;
        this.orderDate = orderDate;
        this.status = status;
        this.payment = payment;
        this.cart = cart;
    }

    public Order() {
        this("", LocalDateTime.MIN, "", new Payment(), new Cart());
    }
    public Order(Order original) { //for deep copy
        this.ID = original.ID;
        this.orderDate = original.orderDate;
        this.status = original.status;
        this.payment = new Payment(original.payment);  
        this.cart = new Cart(original.cart);  
    }
    // Getters
    public String getID() {
        return ID;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public Payment getPayment() {
        return payment;
    }

    public Cart getCart() {
        return cart;
    }

    // Setters
    public void setID(String ID) {
        this.ID = ID;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    // Methods
    public void deleteOrder() {
        this.ID = "";
        this.orderDate = LocalDateTime.MIN;
        this.status = "";
        this.payment = new Payment();
        this.cart = new Cart();
    }

    @Override
    public String toString() {
        return String.format("Order ID: " + ID + "\n\nOrder Date: " + orderDate + "\n\nStatus: " + status +"\n\nPayment: " + payment + "\n\nCart: " + cart);

    }
}
