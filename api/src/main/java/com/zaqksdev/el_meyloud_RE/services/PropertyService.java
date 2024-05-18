package com.zaqksdev.el_meyloud_RE.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.zaqksdev.el_meyloud_RE.models.Agent;
import com.zaqksdev.el_meyloud_RE.models.Property;
import com.zaqksdev.el_meyloud_RE.repos.ClientRepo;
import com.zaqksdev.el_meyloud_RE.repos.PropertyRepo;
import com.zaqksdev.el_meyloud_RE.services.Coords.Coords;
import com.zaqksdev.el_meyloud_RE.services.Coords.Point;

@Service
public class PropertyService {
    static PropertyRepo propertyRepo;
    static ClientRepo clientRepo;

    static AgentService agntSrvc;

    @Autowired
    public void setPropertyRepo(PropertyRepo propertyRepo, ClientRepo clientRepo,
            AgentService agntSrvc) {
        PropertyService.propertyRepo = propertyRepo;
        PropertyService.clientRepo = clientRepo;
        PropertyService.agntSrvc = agntSrvc;
    }

    public Boolean exists(Property property, BindingResult result) {
        // check the addr
        if (propertyRepo.findByAddr(property.getAddr()) != null) {
            result.rejectValue("addr", null, "already existing");
            return true;
        }
        // check the coords
        float x = property.getX();
        float y = property.getY();
        int i;

        List<Property> xlst = propertyRepo.findByX(x);
        List<Property> ylst = propertyRepo.findByX(y);

        for (i = 0; i < xlst.size(); i++) {
            if (xlst.get(i).getY() == y) {
                result.rejectValue("x", null, "already existing");
                result.rejectValue("y", null, "already existing");
                return true;
            }
        }

        for (i = 0; i < ylst.size(); i++) {
            if (ylst.get(i).getX() == x) {
                result.rejectValue("x", null, "already existing");
                result.rejectValue("y", null, "already existing");
                return true;
            }
        }

        return false;
    }

    public List<Property> getOf(String email) {

        return propertyRepo.findByOwner(clientRepo.findByEmail(email));
    }

    public Property getOf(String email, int prp_id) {
        Property rslt = propertyRepo.findById(prp_id);
        if (rslt == null)
            return null;

        if (!owns(email, prp_id))
            return null;

        return rslt;
    }

    public boolean owns(String email, int prp_id) {
        Property rslt = propertyRepo.findById(prp_id);
        return rslt.getOwner().getEmail().equals(email);
    }

    public void save(Property prp) {
        propertyRepo.save(prp);
    }

    public Agent getClosestAgent(Property property) {
        Point prpPnt = new Point().getPoint(property);

        List<Agent> agnts = agntSrvc.getAllNonAdminActive();
        Point agntPnt;
        Agent current;

        HashMap<Integer, Float> distances = new HashMap<Integer, Float>();
        HashMap<Float, Integer> distancesInv = new HashMap<Float, Integer>();

        float d;

        // link each agent with d
        for (int i = 0; i < agnts.size(); i++) {
            current = agnts.get(i);
            agntPnt = new Point().getPoint(current);
            d = new Coords(prpPnt, agntPnt).getDistance();

            distances.put(current.getId(), d);

            // inv
            distancesInv.put(d, current.getId());
        }

        // get the shortest distance to get the closest agent
        List<Float> vals = new ArrayList<Float>(distances.values());
        Collections.sort(vals);// ASCD tsma the closest c vraiment the first elem

        // doka chouf la distance a quel agent elle correspond

        return agntSrvc.get(distancesInv.get(vals.get(0)));

    }

}
