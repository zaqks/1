package com.zaqksdev.el_meyloud_RE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaqksdev.el_meyloud_RE.models.Client;
import com.zaqksdev.el_meyloud_RE.repos.ClientRepo;

@Service
public class SecurityService {
    private ClientRepo clientRepo;

    @Autowired
    public void setClientRepo(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public class ClientAuth {
        private String email, password;

        public ClientAuth(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public Boolean checkAuth() {
            Client rslt = clientRepo.findByEmail(email);
            if (rslt != null) {
                return rslt.getPassword().equals(password);
            }

            return false;
        }

        public Boolean loggedIn() {
            return checkAuth();

        }

        public Boolean canSell() {
            return loggedIn() && clientRepo.findByEmail(email).isSells();
        }

        public String kickNonLogged(String src) {
            if (!loggedIn()) {
                return "redirect:/client/signin";
            }
            return src;
        }

        public String kickNonSeller(String src) {
            if (!canSell()) {
                return "redirect:/client";
            }

            return src;

        }

        public Client get() {
            return clientRepo.findByEmail(email);
        }

    }

}
