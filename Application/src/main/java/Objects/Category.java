
package Objects;
import java.io.Serializable;

public class Category implements Serializable {
    
    private String ID;
    private String title;

    public Category(String ID, String title){

        this.ID = ID;
        this.title = title;

    }

    public Category(){
        this("","");
    }
   
    // getters

 public String getID(){
    return ID;
 }
 public String getTitle(){
    return title;
 }

    // setters
    public void setID(String ID){
        this.ID = ID;
    }
    public void setTitle(String title){
        this.title = title;
    }
  
    public void deleteCategory(){
        ID = "";
        title = "";
    }

    @Override
    public String toString(){
        return ID + "/" + title;
    }
}
