package com.zaqksdev.el_meyloud_RE.controllers.client;

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
@RequestMapping("client/contract")
public class ClientContract {
    @Autowired
    private AuthService authSrvc;
    @Autowired
    private ContractService cntrtSrvc;

    @GetMapping("")
    public String showAllContact(Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        model.addAttribute("contracts", cntrtSrvc.getOf(email));

        return authSrvc.new ClientAuth(email, password).kickNonSeller("contract/client/showAll");

    }

    @GetMapping("/{id}")
    public String showContract(
            @PathVariable(name = "id") int id,
            Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        Contract rslt = cntrtSrvc.getOf(email, id);

        if (rslt == null)
            return "redirect:/client/contract";

        model.addAttribute("contract", rslt);

        return authSrvc.new ClientAuth(email, password).kickNonSeller("contract/show");
    }

}
