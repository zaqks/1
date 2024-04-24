package com.zaqksdev.el_meyloud_RE.models;

import jakarta.persistence.*;

@Entity
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double x, y;
    private String addr;
    private int floors, surf, rooms, grgs, pools;
    
    private int owner_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public int getSurf() {
        return surf;
    }

    public void setSurf(int surf) {
        this.surf = surf;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getGrgs() {
        return grgs;
    }

    public void setGrgs(int grgs) {
        this.grgs = grgs;
    }

    public int getPools() {
        return pools;
    }

    public void setPools(int pools) {
        this.pools = pools;
    }

    
}
