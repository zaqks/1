package com.zaqksdev.el_meyloud_RE.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaqksdev.el_meyloud_RE.models.Agent;
import com.zaqksdev.el_meyloud_RE.models.Visit;
import com.zaqksdev.el_meyloud_RE.repos.AgentRepo;

@Service
public class AgentService {
    static AgentRepo agntRepo;

    @Autowired
    public void setCtrtRepo(AgentRepo repo) {
        AgentService.agntRepo = repo;

    }

    public List<Agent> getAll() {
        return agntRepo.findAll();
    }

    public List<Agent> getAllNonAdmin() {
        return agntRepo.findByAdmin(false);
    }

    public List<Agent> filterActive(List<Agent> inpt) {
        List<Agent> rslt = new ArrayList<Agent>();

        for (int i = 0; i < inpt.size(); i++) {
            if (inpt.get(i).isActive())
                rslt.add(inpt.get(i));
        }

        return rslt;
    }

    public List<Agent> getAllNonAdminActive() {
        return filterActive(agntRepo.findByAdmin(false));
    }

    public Agent get(int id) {
        return agntRepo.findById(id);
    }

    public void delete(int id) {
        agntRepo.delete(get(id));
    }

    public void deleteNonAdmin(int id) {
        Agent rslt = get(id);
        if (rslt != null && !rslt.isAdmin()) {
            rslt.setActive(false);
            agntRepo.save(rslt);
            // agntRepo.delete(rslt);
        }

    }

    public Visit getNextVisit(Agent agent, int gap, int duration) {
        int startW = agent.getStartH();
        int endW = agent.getEndW();
        int startH = agent.getStartH();
        int endH = agent.getEndH();

        // doka a7km today+GAP
        Calendar today = Calendar.getInstance();
        System.out.print(today);

        // a93d tzid day la ta7t f wahed ma ysl7ch

        // doka chouf la derniere visite f hadak e nhar 3la d9ah

        // la h == endH
        // dir day+1
        // a93d tzid day la ta7t f wahed ma ysl7ch

        // doka rah 3ndek le parfait timing
        // tu cree la visite ou cbn

        return new Visit();
    }

}
