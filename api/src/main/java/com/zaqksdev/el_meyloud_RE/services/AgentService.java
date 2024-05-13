package com.zaqksdev.el_meyloud_RE.services;

import java.sql.Date;
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

    static VisitService visitSrvc;

    @Autowired
    public void setCtrtRepo(AgentRepo repo, VisitService visitSrvc) {
        AgentService.agntRepo = repo;
        AgentService.visitSrvc = visitSrvc;

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

    public boolean isWorkDay(Agent agent, int day_num) {
        // day 0 => 6
        int startW = agent.getStartW();
        int endW = agent.getEndW();

        if (startW < endW) {
            return startW <= day_num && day_num <= endW;
        } else
            return startW >= day_num && day_num >= endW;

    }

    public Calendar getNextVisitDate(Agent agent, int gap, int duration) {
        /*
         * 
         * System.out.println("-------------------------");
         * System.out.println(c1.get(Calendar.HOUR_OF_DAY));// hour
         * System.out.println(c1.get(Calendar.MINUTE));// minute
         * System.out.println(c1.get(Calendar.SECOND));// seconds
         * 
         * System.out.println(c1.get(Calendar.YEAR));// year
         * System.out.println(c1.get(Calendar.MONTH) + 1);// month
         * System.out.println(c1.get(Calendar.DAY_OF_MONTH));// day num
         * System.out.println(c1.get(Calendar.DAY_OF_WEEK) - 1);// day name
         * System.out.println("-------------------------");
         * 
         */

        // doka a7km today+GAP
        Calendar ftrVstDate = Calendar.getInstance(); // today's datetime
        ftrVstDate.add(Calendar.DAY_OF_MONTH, gap);

        ftrVstDate.set(Calendar.MINUTE, 0);
        ftrVstDate.set(Calendar.SECOND, 0);
        ftrVstDate.set(Calendar.MILLISECOND, 0);

        // a93d tzid day la ta7t f wahed ma ysl7ch

        while (!isWorkDay(agent, ftrVstDate.get(Calendar.DAY_OF_WEEK) - 1)) {
            ftrVstDate.add(Calendar.DAY_OF_MONTH, 1);
            System.out.println("pass");
        }

        // doka chouf la derniere visite f hadak e nhar 3la d9ah
        Visit lastVst = visitSrvc.getLastOn(agent, ftrVstDate);

        // la mknch
        if (lastVst == null) {
            ftrVstDate.set(Calendar.HOUR_OF_DAY, agent.getEndH() - duration);
        } else {
            ftrVstDate.set(Calendar.HOUR_OF_DAY, lastVst.getDatetime().get(Calendar.HOUR_OF_DAY));
            // la h == endH
            if (ftrVstDate.get(Calendar.HOUR_OF_DAY) + duration >= agent.getEndH()) {
                // dir day+1
                ftrVstDate.add(Calendar.DAY_OF_MONTH, 1);

                // a93d tzid day la ta7t f wahed ma ysl7ch
                while (!isWorkDay(agent, ftrVstDate.get(Calendar.DAY_OF_WEEK))) {
                    ftrVstDate.add(Calendar.DAY_OF_MONTH, 1);
                }
            }
        }

        // doka rah 3ndek le parfait timing
        // tu cree la visite ou cbn

        return ftrVstDate;
    }

}
