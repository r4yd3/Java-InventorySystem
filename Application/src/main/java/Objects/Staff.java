package Objects;
public class Staff extends User {
    String department;
    String position;

    public Staff(String name, String ID, String email, String password, String department, String position){
        super(name, ID, email, password);
        this.position = position;
        this.department = department;
        if (position.toUpperCase().equals("ADMIN") && department.toUpperCase().equals("IT")){
            accessLevel = 4;
        } else if (position.toUpperCase().equals("MANAGER") && department.toUpperCase().equals("INVENTORY")){
            accessLevel = 3;
        } else {
            accessLevel = 2;
        }
    }
    public Staff(){}
    // getters

    public String getDepartment(){
        return department;
    }

    public String getPosition(){
        return position;
    }

    // setters

    public void setDepartment(String department){
        this.department = department;
    }

    public void setPosition(String position){
        this.position = position;
    }

    @Override
    public void deleteUser(){
        name = "";
        ID = "";
        email = "";
        setPassword("");
        accessLevel = 0;
        department = "";
        position = "";
    }

    @Override
    public void currentUserInfo() {
       System.out.printf("\n\n Name : %s\n ID: %s\n Email: %s\n Department: %s\n Position: %s\n Access Level: %d\n\n", name, ID, email, department, position, accessLevel);
    }
}