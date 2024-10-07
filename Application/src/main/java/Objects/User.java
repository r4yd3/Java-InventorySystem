package Objects;
import java.io.Serializable;

public abstract class User implements Serializable{
    protected String name;
    protected String ID;
    protected String email;
    private String password;
    protected int accessLevel;
/*
 
    Access Level:

    1: Members -> buy stuff part only
    2: Low Level Staff -> edit quantity only
    3: Manager -> edit staff, member & inventory, report
    4: Admin -> everything


 */
    public User(String name, String ID, String email, String password){
        this.name = name;
        this.ID = ID;
        this.email = email;
        this.password = password;
        accessLevel = 1;
    }

    public User(){
        this("", "", "", "");
    }

    // getters

    public String getName(){
        return name;
    }

    public String getID(){
        return ID;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public int getAccessLevel(){
        return accessLevel;
    }

    // setters

    public void setName(String name){
        this.name = name;
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setAccessLevel(int accessLevel){
        this.accessLevel = accessLevel;
    }

    // delete

    public abstract void deleteUser();

    // current user

    public abstract void currentUserInfo();
}