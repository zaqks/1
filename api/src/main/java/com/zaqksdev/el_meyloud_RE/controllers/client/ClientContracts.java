package com.zaqksdev.el_meyloud_RE.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zaqksdev.el_meyloud_RE.services.SecurityService;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("client/contract")
public class ClientContracts {
    @Autowired
    private SecurityService authSrvc;

    @GetMapping("")
    public String showAllOffer(Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        // model.addAttribute("offers", offrSrvc.getOf(email));

        return authSrvc.new ClientAuth(email, password).kickNonSeller("offer/offer/showAll");

    }

}
