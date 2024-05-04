package com.zaqksdev.el_meyloud_RE.controllers.client.utils.security;

import com.zaqksdev.el_meyloud_RE.models.dtos.auth.ClientKeyDTO;
import com.zaqksdev.el_meyloud_RE.services.repos.ClientRepo;

public class Security {
    private String email;
    private String pwd;
    private ClientRepo clientRepo;

    public Security(ClientRepo repo,  String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
        this.clientRepo = repo;
    }

    public Boolean loggedIn(String email, String password) {
        return new ClientKeyDTO(clientRepo, email, password).checkAuth();

    }

    public Boolean canSell(
            String email, String password) {
        return loggedIn(email, password) && clientRepo.findByEmail(email).isSells();
    }

    public String kickNonLogged(String src) {
        if (!loggedIn(email, pwd)) {
            return "redirect:/client/signin";
        }
        return src;
    }

    public String kickNonSeller(String src) {
        if (!canSell(email, pwd)) {
            return "redirect:/client";
        }

        return src;

    }

}
