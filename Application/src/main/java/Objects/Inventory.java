package Objects;
import java.io.Serializable;

public class Inventory implements Serializable {
    
    private String ID;
    private String location;


    public Inventory(String ID, String location){
        this.ID = ID;
        this.location = location;

    }
    public Inventory(){
        this("", "");
    }

    // getters

   public String getID(){
    return ID;
   }
   
   public String getLocation(){
    return location;
   }

 
    // setters

    public void setID(String ID){
        this.ID = ID;
    }

    public void setLocation(String location){
        this.location = location;
    }

  

    public void deleteInventoryDetails(){
        ID = "";
        location = "";
    }

    @Override
    public String toString(){
        return ID + "/" + location;
    }
   
}
