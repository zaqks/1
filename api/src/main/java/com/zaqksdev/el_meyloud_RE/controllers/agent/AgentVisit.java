package com.zaqksdev.el_meyloud_RE.controllers.agent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zaqksdev.el_meyloud_RE.dtos.visit.VisitShowDTO;
import com.zaqksdev.el_meyloud_RE.services.AuthService;
import com.zaqksdev.el_meyloud_RE.services.VisitService;

@Controller
@RequestMapping("agent/visit")
public class AgentVisit {
    @Autowired
    AuthService authSrvc;
    @Autowired
    VisitService vstSrvc;

    @GetMapping("")
    public String showAll(Model model,
            @CookieValue(name = "agent_email", defaultValue = "") String email,
            @CookieValue(name = "agent_password", defaultValue = "") String password) {

        model.addAttribute("rent", new VisitShowDTO().VisitShowDTOs(vstSrvc.getAllRent(email)));
        model.addAttribute("buy", new VisitShowDTO().VisitShowDTOs(vstSrvc.getAllBuy(email)));
        model.addAttribute("check", new VisitShowDTO().VisitShowDTOs(vstSrvc.getAllCheck(email)));

        return authSrvc.new AgentAuth(email, password).kick("visit/agent/showAll");
    }

}
