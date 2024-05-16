package com.zaqksdev.el_meyloud_RE.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaqksdev.el_meyloud_RE.models.Agent;
import com.zaqksdev.el_meyloud_RE.models.Client;
import com.zaqksdev.el_meyloud_RE.models.Offer;
import com.zaqksdev.el_meyloud_RE.models.Property;
import com.zaqksdev.el_meyloud_RE.models.Visit;
import com.zaqksdev.el_meyloud_RE.repos.OfferRepo;
import com.zaqksdev.el_meyloud_RE.repos.VisitRepo;

@Service
public class OfferService {
    static OfferRepo offerRepo;
    // static VisitRepo visitRepo;

    static PropertyService prprtSrvc;
    static AgentService agntSrvc;
    static ClientService clntSrvc;
    static VisitService vztSrvc;

    @Autowired
    public void setOfferRepo(OfferRepo offerRepo, VisitRepo visitRepo, PropertyService prprtSrvc,
            AgentService agntSrvc, ClientService clntSrvc, VisitService vztSrvc) {
        OfferService.offerRepo = offerRepo;
        // OfferService.visitRepo = visitRepo;
        OfferService.prprtSrvc = prprtSrvc;
        OfferService.agntSrvc = agntSrvc;
        OfferService.clntSrvc = clntSrvc;
        OfferService.vztSrvc = vztSrvc;
    }

    public Offer get(int id) {
        return offerRepo.findById(id);
    }

    public List<Offer> getOf(String email) {
        List<Offer> rslt = new ArrayList<Offer>();

        List<Offer> offers = offerRepo.findAll();
        Offer current;
        // find offers with those props

        for (int i = 0; i < offers.size(); i++) {
            current = offers.get(i);
            if (current.getProperty().getOwner().getEmail().equals(email))
                rslt.add(current);
        }

        return rslt;
    }

    public Offer getOf(String email, int offrID) {
        Offer rslt = offerRepo.findById(offrID);

        if (rslt != null && rslt.getProperty().getOwner().getEmail().equals(email))
            return rslt;

        return null;

    }

    public void save(Offer offr) {
        // hna u schedule the check
        offerRepo.save(offr);
    }

    public List<Offer> getNoOf(String email) {
        List<Offer> rslt = offerRepo.findAll();
        List<Offer> toDel = new ArrayList<Offer>();
        int i;
        Offer currentOfr;
        Property currentPrp;

        // select the ones to delete
        for (i = 0; i < rslt.size(); i++) {
            currentOfr = rslt.get(i);
            currentPrp = currentOfr.getProperty();
            // li tkhliha lzm tkoun not owned by the client + avlb
            if (!(!currentPrp.getOwner().getEmail().equals(email) && currentOfr.isAvlbl()))
                toDel.add(currentOfr);
        }

        // delete
        for (i = 0; i < toDel.size(); i++) {
            rslt.remove(toDel.get(i));
        }

        return rslt;
    }

    public boolean bookedOffr(String email_client, int id_offer) {
        // a7km ga3 l visit ta3 l client
        // la tl9a asmou tmak a3ih b ban
        List<Visit> visits = vztSrvc.getOf(email_client);
        for (int i = 0; i < visits.size(); i++) {
            if (visits.get(i).getOffer().getId() == id_offer)
                return false;
        }

        return true;
    }

    public List<Offer> getNoOfNoBooked(String email) {
        List<Offer> rslt = offerRepo.findAll();
        List<Offer> toDel = new ArrayList<Offer>();
        int i;
        Offer currentOfr;
        Property currentPrp;

        // select the ones to delete
        for (i = 0; i < rslt.size(); i++) {
            currentOfr = rslt.get(i);
            currentPrp = currentOfr.getProperty();
            // li tkhliha lzm tkoun not owned by the client + avlb + non booked
            if (!(!currentPrp.getOwner().getEmail().equals(email) && currentOfr.isAvlbl()
                    && !bookedOffr(email, currentOfr.getId())))
                toDel.add(currentOfr);
        }

        // delete
        for (i = 0; i < toDel.size(); i++) {
            rslt.remove(toDel.get(i));
        }

        return rslt;
    }

    public List<Visit> getCheckVisits(int offer_id, String owner_email) {
        List<Visit> visits = vztSrvc.getVisits(offer_id);
        // client visits his offer

        Visit current;
        List<Visit> result = new ArrayList<Visit>();

        for (int i = 0; i < visits.size(); i++) {
            current = visits.get(i);
            if (current.getOffer().getProperty().getOwner().getEmail().equals(owner_email))
                result.add(current);

        }
        return result;
    }

    public void createVisit(Offer offer, Client client) {
        final int GAP = 2;// days
        final int DURATION = 1;// hours

        // sooo lzmlna dabord n3rfou l'agent le plus proche de la propriete
        Agent closestAgnt = prprtSrvc.getClosestAgent(offer.getProperty());

        Calendar nextVisitDate = agntSrvc.getNextVisitDate(closestAgnt, GAP, DURATION);

        Visit visit = new Visit();
        visit.setDatetime(nextVisitDate);
        visit.setOffer(offer);
        visit.setClient(client);
        visit.setAgent(closestAgnt);

        vztSrvc.save(visit);

    }

    public void createVisit(int id_offer, String email_client) {
        final int GAP = 2;// days
        final int DURATION = 1;// hours

        Offer offer = get(id_offer);
        Client client = clntSrvc.get(email_client);

        // sooo lzmlna dabord n3rfou l'agent le plus proche de la propriete
        Agent closestAgnt = prprtSrvc.getClosestAgent(offer.getProperty());

        Calendar nextVisitDate = agntSrvc.getNextVisitDate(closestAgnt, GAP, DURATION);

        Visit visit = new Visit();
        visit.setDatetime(nextVisitDate);
        visit.setOffer(offer);
        visit.setClient(client);
        visit.setAgent(closestAgnt);

        vztSrvc.save(visit);
    }

    public void activateOffer(Offer offr) {
        offr.setChecked(true);
        offr.setAvlbl(true);
        offerRepo.save(offr);
    }

    public void deactivateOffer(Offer offr) {
        // offr.setChecked(true);
        offr.setAvlbl(false);
        offerRepo.save(offr);
    }

}
