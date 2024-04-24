package com.zaqksdev.el_meyloud_RE.models;

import jakarta.persistence.*;

@Entity
@Table(name = "agents")

public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name, surname, nin;
    private String phonenum, email;
    private String ccp, ccp_key, rip;

    private double x, y;
    

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

    public String getCcp_key() {
        return ccp_key;
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

    public void setCcp_key(String ccp_key) {
        this.ccp_key = ccp_key;
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

  
}
