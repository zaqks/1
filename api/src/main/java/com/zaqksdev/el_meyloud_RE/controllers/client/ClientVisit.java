package com.zaqksdev.el_meyloud_RE.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zaqksdev.el_meyloud_RE.dtos.visit.VisitShowDTO;
import com.zaqksdev.el_meyloud_RE.models.Visit;
import com.zaqksdev.el_meyloud_RE.services.VisitService;
import com.zaqksdev.el_meyloud_RE.services.AuthService;

@Controller
@RequestMapping("client/visit")
public class ClientVisit {
    @Autowired
    private AuthService authSrvc;
    @Autowired
    private VisitService vztSrvc;

    @GetMapping("")
    public String showAllVisit(Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        model.addAttribute("visits",
                new VisitShowDTO().VisitShowDTOs(vztSrvc.getOf(email))
        );

        return authSrvc.new ClientAuth(email, password).kickNonSeller("visit/client/showAll");

    }

    @GetMapping("/{id}")
    public String showVisit(
            @PathVariable(name = "id") int id,
            Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        Visit rslt = vztSrvc.getOf(email, id);

        if (rslt == null)
            return "redirect:/client/visit";

        model.addAttribute("visit", rslt);

        return authSrvc.new ClientAuth(email, password).kickNonSeller("visit/client/show");
    }

}
