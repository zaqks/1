package main.java.com.zaqksdev.el_meyloud_RE.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "clients")

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;
    private String nin;

    private String phonenum;
    private String email;

    private String ccp;
    private String key;
    private String rip;

    private double x;
    private double y;
    private boolean sells;

    // getters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNin() {
        return nin;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public String getEmail() {
        return email;
    }

    public String getCcp() {
        return ccp;
    }

    public String getKey() {
        return key;
    }

    public String getRip() {
        return rip;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean getSells() {
        return sells;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCcp(String ccp) {
        this.ccp = ccp;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setRip(String rip) {
        this.rip = rip;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setSells(boolean sells) {
        this.sells = sells;
    }
}
