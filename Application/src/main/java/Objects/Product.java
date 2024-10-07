package Objects;
import java.io.Serializable;

public class Product implements Serializable {

    private String ID, name, description; // ID could be cat+ number
    private double price;
    private int stockQty;
    private static int numOfProd = 0;
    private Category category;
    private Inventory inventory;
    private Supplier supplier;

   
    public Product(String name, String description, double price, int stockQty, Category category,Inventory inventory, Supplier supplier ){
      numOfProd++;
      ID = category.getID() + numOfProd;
      this.name = name;
      this.description = description;
      this.price = price;
      this.stockQty = stockQty;
      this.category = category;
      this.inventory = inventory;
      this.supplier = supplier;
    }
    public Product() {
      this("", "", 0, 0, new Category(), new Inventory(), new Supplier());
    }

   
    // getters
 
    public String getID(){
      return ID;
    }
    
    public String getName(){
      return name;
    }

    public String getDescription(){
      return description;
    }

    public double getPrice(){
      return price;
    }

    public int getStockQty(){
      return stockQty;
    }

    public Category getCategory(){
      return category;
    }

    public Inventory getInventory(){
      return inventory;
    }

    public static int getNumOfProd(){
      return numOfProd;
    }

    public Supplier getSupplier(){
      return supplier;
    }
    // setters

    public void setID(String ID){
      this.ID = ID;
    }

    public void setName(String name){
      this.name = name;
    }

    public void setDescription(String description){
      this.description = description;
    }

    public void setPrice(double price){
      this.price = price;
    }

    public void setStockQty(int stockQty){
      this.stockQty = stockQty;
    }

    public static void setNumOfProd(int numOfProd){
      Product.numOfProd = numOfProd;
    }

    public void setSupplier(Supplier supplier){
      this.supplier = supplier;
    }

    public void setCategory(Category category){

      this.category = category;
  
    }

    public void setInventory(Inventory inventory){

      this.inventory = inventory;

    }

    public void deleteProduct(){
      ID = "";
      name = "";
      description = "";
      price = 0;
      stockQty = 0;
      category = new Category();
      inventory = new Inventory();
      supplier = new Supplier();
    }

    @Override
    public String toString(){
     return String.format("\nID: %s \nName: %s \nDescription: %s \nPrice: %.2f \nStock Quantity: %d \nCategory: %s \nInventory: %s \nSupplier: %s", ID, name, description, price, stockQty, category, inventory, supplier);
    }
    
 }