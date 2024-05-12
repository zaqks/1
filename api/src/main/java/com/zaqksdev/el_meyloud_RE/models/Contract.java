package com.zaqksdev.el_meyloud_RE.models;

import java.sql.Time;
import java.sql.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;
    private Time time;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    private Offer offer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "src_client_id", referencedColumnName = "id")
    private Client src;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dst_client_id", referencedColumnName = "id")
    private Client dst;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agent_id", referencedColumnName = "id")
    private Agent agent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }


    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
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

    
    

}