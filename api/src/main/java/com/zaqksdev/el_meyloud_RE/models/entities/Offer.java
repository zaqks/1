package com.zaqksdev.el_meyloud_RE.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int price, rent;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "src_client_id", referencedColumnName = "id")
    private Client src;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dst_client_id", referencedColumnName = "id")
    private Client dst;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private Property property;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public Client getSrc() {
        return src;
    }

    public void setSrc(Client src) {
        this.src = src;
    }

    public Client getDst() {
        return dst;
    }

    public void setDst(Client dst) {
        this.dst = dst;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }



    

}