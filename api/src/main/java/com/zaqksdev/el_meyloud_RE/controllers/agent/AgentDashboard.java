package com.zaqksdev.el_meyloud_RE.controllers.agent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zaqksdev.el_meyloud_RE.services.AuthService;

@Controller
@RequestMapping("agent")
public class AgentDashboard {
    @Autowired
    private AuthService authSrvc;

    @GetMapping("")
    public String showDashboard(
            Model model,
            @CookieValue(name = "agent_email", defaultValue = "") String email,
            @CookieValue(name = "agent_password", defaultValue = "") String password) {

        return authSrvc.new AgentAuth(email, password).kick("agent/dashboard");

    }

}
