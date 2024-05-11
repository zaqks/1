package com.zaqksdev.el_meyloud_RE.controllers.agency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.zaqksdev.el_meyloud_RE.models.Agent;
import com.zaqksdev.el_meyloud_RE.services.AgentService;
import com.zaqksdev.el_meyloud_RE.services.AuthService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("agency/agent")
public class AgencyAgents {
    @Autowired
    private AuthService authSrvc;
    @Autowired
    private AgentService agntSrvc;

    @GetMapping("")
    public String showAllAgent(Model model,
            @CookieValue(name = "admin_email", defaultValue = "") String email,
            @CookieValue(name = "admin_password", defaultValue = "") String password) {

        model.addAttribute("agents", agntSrvc.getAll());

        return authSrvc.new AgentAuth(email, password).kickNonAdmin("agent/showAll");

    }

    @GetMapping("/{id}")
    public String showAgent(
            @PathVariable(name = "id") int id,
            Model model,
            @CookieValue(name = "admin_email", defaultValue = "") String email,
            @CookieValue(name = "admin_password", defaultValue = "") String password) {

        Agent rslt = agntSrvc.get(id);
        if (rslt == null)
            return "redirect:/agency/agent";

        // check if ure not checking your self
        if (rslt.getEmail().equals(email))
            return "redirect:/agency/agent";

        model.addAttribute("agent", rslt);

        return authSrvc.new AgentAuth(email,
                password).kickNonAdmin("agent/show");
    }

    @GetMapping("/add")
    public String addAgent(
            Model model) {

        model.addAttribute("agent", new Agent());
        return "agent/add";
    }
}
