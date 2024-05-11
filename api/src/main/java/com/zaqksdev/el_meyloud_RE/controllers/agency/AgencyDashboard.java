package com.zaqksdev.el_meyloud_RE.controllers.agency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zaqksdev.el_meyloud_RE.services.AuthService;

@Controller
@RequestMapping("agency")
public class AgencyDashboard {
    @Autowired
    private AuthService authSrvc;

    @GetMapping("")
    public String showDashboard(
            Model model,
            @CookieValue(name = "admin_email", defaultValue = "") String email,
            @CookieValue(name = "admin_password", defaultValue = "") String password) {

        return authSrvc.new AgentAuth(email, password).kickNonAdmin("agency/dashboard");

    }

}
