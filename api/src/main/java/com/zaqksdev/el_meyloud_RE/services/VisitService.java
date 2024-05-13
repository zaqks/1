package com.zaqksdev.el_meyloud_RE.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
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

    public boolean equalDates(Calendar c1, Calendar c2) {
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
                c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) &&
                c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);

    }

    public List<Visit> filterByDate(List<Visit> inpt, Calendar date) {
        List<Visit> rslt = new ArrayList<Visit>();

        for (int i = 0; i < inpt.size(); i++) {

            // day day month year
            if (equalDates(date, inpt.get(i).getDatetime()))
                rslt.add(inpt.get(i));
        }

        return rslt;
    }

    public Visit getLastOn(Agent agent, Calendar date) {
        List<Visit> todays = filterByDate(getPresentedBy(agent), date);

        HashMap<Integer, Integer> hours = new HashMap<Integer, Integer>();
        HashMap<Integer, Visit> invHours = new HashMap<Integer, Visit>();

        // each indx linkih m3a hour + 1
        int hour;
        for (int i = 0; i < todays.size(); i++) {
            hour = todays.get(i).getDatetime().get(Calendar.HOUR_OF_DAY);
            hours.put(i, hour);
            invHours.put(hour, todays.get(i));
        }

        List<Integer> vals = (List<Integer>) hours.values();
        Collections.sort(vals);

        // the highest val hya the endtime (last one on the sort)

        return invHours.get(vals.get(vals.size() - 1));

    }

}
