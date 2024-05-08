package com.zaqksdev.el_meyloud_RE.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zaqksdev.el_meyloud_RE.models.Offer;
import com.zaqksdev.el_meyloud_RE.models.Property;

import java.util.List;

@Repository
public interface OfferRepo extends JpaRepository<Offer, Integer> {
    Offer findById(int id);

    List<Offer> findByProperty(Property property);

}
