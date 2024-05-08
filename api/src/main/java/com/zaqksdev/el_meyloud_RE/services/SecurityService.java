package com.zaqksdev.el_meyloud_RE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

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

        public ClientAuth() {
        }

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

        public void save(Client client) {
            clientRepo.save(client);
        }

        public class Form {
            private BindingResult result;

            public Form(BindingResult result) {
                this.result = result;
            }

            public boolean checkSGIN() {

                Client client = get();

                if (client == null) {
                    result.rejectValue("email", null, "unexisting email");
                    return false;
                }

                if (!checkAuth()) {
                    result.rejectValue("password", null, "incorrect password");
                    return false;
                }
                return true;
            }

            public boolean checkSGUP(Client client) {

                if (clientRepo.findByNin(client.getNin()) != null) {
                    result.rejectValue("nin", null, "already in use");
                    return false;
                }
                if (clientRepo.findByNin(client.getPhonenum()) != null) {
                    result.rejectValue("phonenum", null, "already in use");
                    return false;
                }
                if (clientRepo.findByNin(client.getEmail()) != null) {
                    result.rejectValue("email", null, "already in use");
                    return false;
                }
                if (clientRepo.findByNin(client.getCcp()) != null) {
                    result.rejectValue("ccp", null, "already in use");
                    return false;
                }
                if (clientRepo.findByNin(client.getRip()) != null) {
                    result.rejectValue("rip", null, "already in use");
                    return false;
                }

                return true;
            }

        }

    }

}
