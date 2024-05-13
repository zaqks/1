package com.zaqksdev.el_meyloud_RE.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaqksdev.el_meyloud_RE.models.Visit;
import com.zaqksdev.el_meyloud_RE.repos.ClientRepo;
import com.zaqksdev.el_meyloud_RE.repos.VisitRepo;

@Service
public class VisitService {
    private VisitRepo visitRepo;
    private ClientRepo clientRepo;

    @Autowired
    public void setVisitRepo(VisitRepo visitRepo, ClientRepo clientRepo) {
        this.visitRepo = visitRepo;
        this.clientRepo = clientRepo;
    }

    public List<Visit> getOf(String email) {

        return visitRepo.findByClient(clientRepo.findByEmail(email));

    }

    public Visit getOf(String email, int visitID) {
        Visit rslt = visitRepo.findById(visitID);

        if (rslt != null && rslt.getClient().getEmail().equals(email))
            return rslt;

        return null;

    }

}
