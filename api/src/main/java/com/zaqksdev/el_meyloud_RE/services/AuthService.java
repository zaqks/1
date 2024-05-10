package com.zaqksdev.el_meyloud_RE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.zaqksdev.el_meyloud_RE.models.Agent;
import com.zaqksdev.el_meyloud_RE.models.Client;
import com.zaqksdev.el_meyloud_RE.repos.AgentRepo;
import com.zaqksdev.el_meyloud_RE.repos.ClientRepo;

@Service
public class AuthService {
    private String email, password;
    private ClientRepo clientRepo;
    private AgentRepo agentRepo;

    @Autowired
    public void setClientRepo(ClientRepo clientRepo, AgentRepo agentRepo) {
        this.clientRepo = clientRepo;
        this.agentRepo = agentRepo;
    }

    public class ClientAuth {

        public ClientAuth() {
        }

        public ClientAuth(String clientEmail, String clientPassword) {
            email = clientEmail;
            password = clientPassword;

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

    public class AgencyAuth {

        public AgencyAuth() {
        }

        public AgencyAuth(String agentEmail, String agentPassword) {
            agentEmail = email;
            agentPassword = password;
        }

        public Boolean checkAuth() {
            Agent rslt = agentRepo.findByEmail(email);
            if (rslt != null) {
                return rslt.getPassword().equals(password) && rslt.isAdmin();
            }

            return false;
        }

        public String kick(String src) {
            if (!checkAuth()) {
                return "redirect:/agency/signin";
            }
            return src;
        }

        public Agent get() {
            return agentRepo.findByEmail(email);
        }

        public class Form {
            private BindingResult result;

            public Form(BindingResult result) {
                this.result = result;
            }

            public boolean checkSGIN() {

                Agent agent = get();

                if (agent == null) {
                    result.rejectValue("email", null, "unexisting email");
                    return false;
                }

                if (!checkAuth()) {
                    result.rejectValue("password", null, "incorrect password");
                    return false;
                }
                return true;
            }

        }

    }

}
