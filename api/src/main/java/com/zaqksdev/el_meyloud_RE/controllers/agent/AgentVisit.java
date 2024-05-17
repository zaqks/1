package com.zaqksdev.el_meyloud_RE.controllers.agent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zaqksdev.el_meyloud_RE.dtos.offer.OfferCheckDTO;
import com.zaqksdev.el_meyloud_RE.dtos.visit.VisitShowDTO;
import com.zaqksdev.el_meyloud_RE.models.Visit;
import com.zaqksdev.el_meyloud_RE.services.AuthService;
import com.zaqksdev.el_meyloud_RE.services.VisitService;

@Controller
@RequestMapping("agent/visit")
public class AgentVisit {
    @Autowired
    AuthService authSrvc;
    @Autowired
    VisitService vztSrvc;

    @GetMapping("")
    public String showAll(Model model,
            @CookieValue(name = "agent_email", defaultValue = "") String email,
            @CookieValue(name = "agent_password", defaultValue = "") String password) {

        model.addAttribute("buy", new VisitShowDTO().VisitShowDTOs(vztSrvc.getAllBuy(email)));
        model.addAttribute("rent", new VisitShowDTO().VisitShowDTOs(vztSrvc.getAllRent(email)));
        model.addAttribute("check", new VisitShowDTO().VisitShowDTOs(vztSrvc.getAllCheck(email)));

        return authSrvc.new AgentAuth(email, password).kick("visit/agent/showAll");
    }

    @GetMapping("/{id}")
    public String showVisit(
            @PathVariable(name = "id") int id,
            Model model,
            @CookieValue(name = "agent_email", defaultValue = "") String email,
            @CookieValue(name = "agent_password", defaultValue = "") String password)
             {

        Visit rslt = vztSrvc.getPresentedBy(email, id);

        if (rslt == null)
            return "redirect:/agent/visit";

        model.addAttribute("visit", rslt);
        model.addAttribute("checkDTO", new OfferCheckDTO());

        return authSrvc.new AgentAuth(email, password).kick(
                "visit/agent/show");
    }

    @PostMapping("/{id}")
    public String successVisit(
            @PathVariable(name = "id") int id, //
            Model model,
            @CookieValue(name = "agent_email", defaultValue = "") String email,
            @CookieValue(name = "agent_password", defaultValue = "") String password) {

        String finger = authSrvc.new AgentAuth(email, password).kick("");
        if (finger.equals("")) // do the thingy after auth
        {
            vztSrvc.successVst(id);
        }

        return "redirect:/agent/visit";

    }

}
