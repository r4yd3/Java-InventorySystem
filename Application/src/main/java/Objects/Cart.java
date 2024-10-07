package Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Cart implements Serializable {

    private String ID;
    private List<Item> items;

    public Cart(String ID, List<Item> items){
        this.ID = ID;
        this.items = items;
    }

    public Cart(){
        this.ID = "";
        this.items = new ArrayList<>();
    }
    public Cart(Cart original) {
        this.ID = original.ID;
        this.items = new ArrayList<>();
        for (Item item : original.items) {
            this.items.add(new Item(item));  
        }
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public void setItems(List<Item> items){
        this.items = items;
    }

    public String getID(){
        return ID;
    }

    public List<Item> getItems(){
        return items;
    }

    public void addItem(Item item){
        if (item == null) {
            throw new IllegalArgumentException("Cannot add a null item.");
        }
        items.add(item);
    }

    public void removeItem(Item item){
        if (item == null) {
            throw new IllegalArgumentException("Cannot remove a null item.");
        }
        items.remove(item);
    }

    // Clears all items from the cart but keeps the cart ID
    public void clearCart() {
        items.clear();
    }

    // Clears the cart ID and all items
    public void deleteCart() {
        ID = "";
        items.clear();
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    @Override
    public String toString(){
        return ID + "/" + items;
    }
}
