package com.zaqksdev.el_meyloud_RE.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "clients")

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 3, message = "invalid values")
    private String name, surname;

    @Size(min = 10, max = 10, message = "invalid value")
    private String nin;

    @Size(min = 10, max = 13, message = "invalid value")
    private String phonenum;

    @Size(min = 12, message = "invalid value")
    private String email;

    @Size(min = 8, max = 8, message = "invalid value")
    private String ccp;

    @Size(min = 2, max = 2, message = "invalid value")
    private String ccp_key;

    @Size(min = 15, max = 15, message = "invalid value")
    private String rip;

    @Size(min = 8, max = 64, message = "invalid value")
    private String password;

    private float x, y;
    private boolean sells;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getNin() {
        return nin;
    }
    public void setNin(String nin) {
        this.nin = nin;
    }
    public String getPhonenum() {
        return phonenum;
    }
    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCcp() {
        return ccp;
    }
    public void setCcp(String ccp) {
        this.ccp = ccp;
    }
    public String getCcp_key() {
        return ccp_key;
    }
    public void setCcp_key(String ccp_key) {
        this.ccp_key = ccp_key;
    }
    public String getRip() {
        return rip;
    }
    public void setRip(String rip) {
        this.rip = rip;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }
    public boolean isSells() {
        return sells;
    }
    public void setSells(boolean sells) {
        this.sells = sells;
    }


}
