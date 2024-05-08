package com.zaqksdev.el_meyloud_RE.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaqksdev.el_meyloud_RE.models.Offer;
import com.zaqksdev.el_meyloud_RE.repos.OfferRepo;

@Service
public class OfferService {
    private OfferRepo offerRepo;

    @Autowired
    public void setOfferRepo(OfferRepo offerRepo ) {
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

    public void save(Offer offr) {
        offerRepo.save(offr);
    }

}
