package com.zaqksdev.el_meyloud_RE.models.Contract;

import java.util.Calendar;

import com.zaqksdev.el_meyloud_RE.models.Agent;
import com.zaqksdev.el_meyloud_RE.models.Client;
import com.zaqksdev.el_meyloud_RE.models.Offer;

import jakarta.persistence.*;

@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Calendar datetime = Calendar.getInstance();

    @ManyToOne(cascade = CascadeType.ALL) // hya nrmlmnt one to one but dont forget when a cleint unsubscribes ou yji
                                          // wahed f plastou
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    private Offer offer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "src_client_id", referencedColumnName = "id")
    private Client src;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dst_client_id", referencedColumnName = "id")
    private Client dst;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agent_id", referencedColumnName = "id")
    private Agent agent;

    private ContractType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Calendar getDatetime() {

        return datetime;
    }

    public String getDatetime2() {

        Calendar clndr = datetime;

        int hour = clndr.get(Calendar.HOUR_OF_DAY);
        int minute = clndr.get(Calendar.MINUTE);

        int day = clndr.get(Calendar.DAY_OF_MONTH);
        int month = clndr.get(Calendar.MONTH) + 1; // months start from 0 !!!!
        int year = clndr.get(Calendar.YEAR);

        String time = String.format("%dh" + (minute < 10 ? "0" : "") + "%d", hour, minute);
        String date = String.format("%d/%d/%d", month, day, year);

        return String.format("%s %s", date, time);

    }

    public void setDatetime(Calendar datetime) {
        this.datetime = datetime;
    }

    public ContractType getType() {
        return type;
    }

    public void setType(ContractType type) {
        this.type = type;
    }

}
