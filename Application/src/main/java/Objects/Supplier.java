package Objects;
import java.io.Serializable;

public class Supplier implements Serializable {
    
    private String ID;
    private String name;
    private String address;
    private String email;

    //constructor
    public Supplier(String ID, String name, String address, String email){
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public Supplier(){
        this("","","","");
    }
    

    //accessor (getter)
    public String getID(){
        return ID;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public String getEmail(){
        return email;
    }


    //mutator (setter)
    public void setID(String ID){
        this.ID = ID;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setEmail(String email){
        this.email = email;
    }
    @Override
    public String toString(){
        return ID + "/" + name + "/" + address + "/" + email;
    }

    public void deleteSupplier(){
        ID = "";
        name = "";
        address = "";
        email = "";
    }
}