package Objects;
import java.io.Serializable;

public class Item implements Serializable {
    private int quantity;
    private Product product;

    public Item(int quantity, Product product){
        this.quantity = quantity;
        this.product = product;
    }

    public Item(){
        this(0,new Product());
    }
    public Item(Item original){
        this.quantity = original.quantity;
        this.product = original.product;
    }
    public void setQuantity(int quantity){
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Item quantity cannot be negative. Setting quantity to 0.");
            this.quantity = 0;
        }
    }

    public void setProduct(Product product){
        this.product = product;
    }

    public int getQuantity(){
        return quantity;
    }

    public Product getProduct(){
        return product;
    }

    public void deleteItem(){
        quantity = 0;
        product = new Product();
    }

    @Override
    public String toString(){
        return product.toString() + "\n\n Quantity: " + quantity;
    }
}