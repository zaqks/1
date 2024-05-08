package com.zaqksdev.el_meyloud_RE.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaqksdev.el_meyloud_RE.models.Offer;
import com.zaqksdev.el_meyloud_RE.models.Property;
import com.zaqksdev.el_meyloud_RE.repos.OfferRepo;

@Service
public class OfferService {
    private OfferRepo offerRepo;

    @Autowired
    public void setOfferRepo(OfferRepo offerRepo) {
        this.offerRepo = offerRepo;
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

    public Offer getOf(String email, int id) {
        Offer rslt = offerRepo.findById(id);

        if (rslt != null && rslt.getProperty().getOwner().getEmail().equals(email))
            return rslt;

        return null;

    }

    public void register(Offer offr) {
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

}
