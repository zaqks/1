package com.zaqksdev.el_meyloud_RE.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaqksdev.el_meyloud_RE.models.Agent;
import com.zaqksdev.el_meyloud_RE.models.Offer;
import com.zaqksdev.el_meyloud_RE.models.Property;
import com.zaqksdev.el_meyloud_RE.models.Visit;
import com.zaqksdev.el_meyloud_RE.repos.OfferRepo;
import com.zaqksdev.el_meyloud_RE.repos.VisitRepo;
import com.zaqksdev.el_meyloud_RE.services.coords.Coords;
import com.zaqksdev.el_meyloud_RE.services.coords.Point;

@Service
public class OfferService {
    static OfferRepo offerRepo;
    static VisitRepo visitRepo;

    static PropertyService prprtSrvc;

    @Autowired
    public void setOfferRepo(OfferRepo offerRepo, VisitRepo visitRepo, PropertyService prprtSrvc) {
        OfferService.offerRepo = offerRepo;
        OfferService.visitRepo = visitRepo;
        OfferService.prprtSrvc = prprtSrvc;
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

    public List<Visit> getVisits(int offer_id) {
        return visitRepo.findByOffer(get(offer_id));
    }

    public List<Visit> getCheckVisits(int offer_id, String owner_email) {
        List<Visit> visits = getVisits(offer_id);
        // client visits his offer

        Visit current;
        List<Visit> result = new ArrayList<Visit>();

        for (int i = 0; i < visits.size(); i++) {
            current = visits.get(i);
            if (current.getOffer().getProperty().getOwner().getEmail().equals(owner_email)) {
                result.add(current);
            }
        }
        return result;
    }

    public void createVisit(Offer offer, String client_email) {
        final int GAP = 2;//days
        final int DURATION = 1;//hours

        // sooo lzmlna dabord n3rfou l'agent le plus proche de la propriete
        Agent closestAgnt = prprtSrvc.getClosestAgent(offer.getProperty());



        // doka a7km today+GAP
        // a93d tzid day la ta7t f wahed ma ysl7ch
        
        // doka chouf la derniere visite f hadak e nhar 3la d9ah
        
        // la h == endH
        // dir day+1 
        // a93d tzid day la ta7t f wahed ma ysl7ch

        // doka rah 3ndek le parfait timing
        // tu cree la visite ou cbn
        

    }
}
