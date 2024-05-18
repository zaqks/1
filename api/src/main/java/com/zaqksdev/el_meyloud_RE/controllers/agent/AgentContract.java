package com.zaqksdev.el_meyloud_RE.controllers.agent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zaqksdev.el_meyloud_RE.services.ContractService;
import com.zaqksdev.el_meyloud_RE.models.Contract.Contract;
import com.zaqksdev.el_meyloud_RE.services.AuthService;

@Controller
@RequestMapping("agent/contract")
public class AgentContract {
    @Autowired
    private AuthService authSrvc;
    @Autowired
    private ContractService cntrtSrvc;

    @GetMapping("")
    public String showAllContact(Model model,
            @CookieValue(name = "agent_email", defaultValue = "") String email,
            @CookieValue(name = "agent_password", defaultValue = "") String password) {

        model.addAttribute("contracts", cntrtSrvc.getBy(email));

        return authSrvc.new AgentAuth(email, password).kick("contract/agent/showAll");

    }

    @GetMapping("/{id}")
    public String showContract(
            @PathVariable(name = "id") int id,
            Model model,
            @CookieValue(name = "agent_email", defaultValue = "") String email,
            @CookieValue(name = "agent_password", defaultValue = "") String password) {

        Contract rslt = cntrtSrvc.getBy(email, id);

        if (rslt == null)
            return "redirect:/agent/contract";

        model.addAttribute("contract", rslt);

        return authSrvc.new AgentAuth(email, password).kick("contract/show");
    }

}
