package com.zaqksdev.el_meyloud_RE.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zaqksdev.el_meyloud_RE.controllers.client.utils.security.Security;
import com.zaqksdev.el_meyloud_RE.services.repos.ClientRepo;

@Controller
@RequestMapping("client")
public class Market {
    @Autowired
    ClientRepo clientRepo;

    @GetMapping("")
    public String showHome(Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        return new Security(clientRepo, email, password).kickNonLogged("market/home");

    }
}
