package com.zaqksdev.el_meyloud_RE.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zaqksdev.el_meyloud_RE.models.Client;
import com.zaqksdev.el_meyloud_RE.models.Offer;
import com.zaqksdev.el_meyloud_RE.models.Visit;
import java.util.List;

@Repository
public interface VisitRepo extends JpaRepository<Visit, Integer> {
    Visit findById(int id);

    List<Visit> findByClient(Client client);
    List<Visit> findByOffer(Offer offer);
    
}
