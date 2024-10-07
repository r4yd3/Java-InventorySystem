package Objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Payment implements Serializable {
    private String ID;
    private LocalDateTime paymentDate;
    private double amountPaid;
    private String paymentMethod;

    // Constructors
    public Payment(String ID, LocalDateTime paymentDate, double amountPaid, String paymentMethod) {
        this.ID = ID;
        this.paymentDate = paymentDate;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
    }

    public Payment() {
        this("", LocalDateTime.now(), 0.0, "");
    }

    public Payment(Payment original){
        this.ID = original.ID;
        this.paymentDate = original.paymentDate;
        this.amountPaid = original.amountPaid;
        this.paymentMethod = original.paymentMethod;
    }
    // Getters
    public String getID() {
        return ID;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    // Setters
    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // Methods
    public void deletePayment() {
        this.ID = "";
        this.paymentDate = LocalDateTime.MIN;
        this.amountPaid = 0.0;
        this.paymentMethod = "";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss");
        return String.format("Payment ID: " + ID + "\nPayment Date: " + paymentDate.format(formatter) + "\nAmount Paid: " + amountPaid + "\nPayment Method: " + paymentMethod);
    }
}
