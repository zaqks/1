package com.zaqksdev.el_meyloud_RE.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zaqksdev.el_meyloud_RE.services.ContactService;
import com.zaqksdev.el_meyloud_RE.services.SecurityService;

@Controller
@RequestMapping("client/contract")
public class ClientContracts {
    @Autowired
    private SecurityService authSrvc;
    @Autowired
    private ContactService cntrtSrvc;

    @GetMapping("")
    public String showAllContact(Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        model.addAttribute("contracts", cntrtSrvc.getOf(email));

        return authSrvc.new ClientAuth(email, password).kickNonSeller("contract/client/showAll");

    }

}
