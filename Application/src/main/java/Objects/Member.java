package Objects;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class Member extends User{
    boolean memberStatus;
    LocalDateTime memberExpiry;
    private List<Order> orders;

    public Member(String name, String ID, String email, String password, boolean memberStatus, LocalDateTime memberExpiry, List<Order> orders){
        super(name, ID, email, password);
        this.memberStatus = memberStatus;
        this.memberExpiry = memberExpiry;
        this.orders = orders;
    }
    public Member(){}
    // getters

    public boolean getMemberStatus(){
        return memberStatus;
    }

    public LocalDateTime getMemberExpiry(){
        return memberExpiry;
    }

    public List<Order> getOrders(){
        return orders;
    }

    // setters

    public void setMemberStatus(boolean memberStatus){
        this.memberStatus = memberStatus;
    }

    public void setMemberExpiry(LocalDateTime memberExpiry){
        this.memberExpiry = memberExpiry;
    }

    public void setOrders(List<Order> orders){
        this.orders = orders;
    }

    @Override
    public void deleteUser(){
        name = "";
        ID = "";
        email = "";
        setPassword("");
        accessLevel = 0;
        memberStatus = false;
        memberExpiry = LocalDateTime.MIN;
        orders = new ArrayList<>();
    }

    @Override
    public void currentUserInfo(){
        System.out.printf("\n\n Name : %s\n ID: %s\n Email: %s\n Member Status: %s\n Member Expiry: %s\n Access Level: %d\n\n", name, ID, email, memberStatus, memberExpiry, accessLevel);
    }
}