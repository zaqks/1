package com.zaqksdev.el_meyloud_RE.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaqksdev.el_meyloud_RE.models.Agent;
import com.zaqksdev.el_meyloud_RE.models.Visit;
import com.zaqksdev.el_meyloud_RE.repos.AgentRepo;
import com.zaqksdev.el_meyloud_RE.repos.ClientRepo;
import com.zaqksdev.el_meyloud_RE.repos.VisitRepo;

@Service
public class VisitService {
    static VisitRepo visitRepo;
    static ClientRepo clientRepo;
    static AgentRepo agentRepo;

    @Autowired
    public void setVisitRepo(VisitRepo visitRepo, ClientRepo clientRepo, AgentRepo agentRepo) {
        VisitService.visitRepo = visitRepo;
        VisitService.clientRepo = clientRepo;
        VisitService.agentRepo = agentRepo;
    }

    public List<Visit> getOf(String client_email) {

        return visitRepo.findByClient(clientRepo.findByEmail(client_email));

    }

    public Visit getOf(String client_email, int visitID) {
        Visit rslt = visitRepo.findById(visitID);

        if (rslt != null && rslt.getClient().getEmail().equals(client_email))
            return rslt;

        return null;

    }

    public List<Visit> getPresentedBy(Agent agent) {
        return visitRepo.findByAgent(agent);
    }

}
