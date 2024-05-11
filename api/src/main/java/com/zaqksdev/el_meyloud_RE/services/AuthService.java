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

    public class AgentAuth {

        public AgentAuth() {
        }

        public AgentAuth(String agentEmail, String agentPassword) {
            email = agentEmail;
            password = agentPassword;
        }

        public Boolean checkAuth() {
            Agent rslt = agentRepo.findByEmail(email);
            if (rslt != null) {
                return rslt.getPassword().equals(password);
            }

            return false;
        }

        public Boolean isAdmin() {
            return get().isAdmin();
        }

        public String kickNonAdmin(String src) {
            if (!checkAuth() || !isAdmin()) {
                return "redirect:/agency/signin";
            }
            return src;
        }

        public Agent get() {
            return agentRepo.findByEmail(email);
        }

        public void save(Agent agent){            

            agentRepo.save(agent);
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

            public boolean checkSGUP(Agent agent) {

                if (clientRepo.findByNin(agent.getNin()) != null) {
                    result.rejectValue("nin", null, "already in use");
                    return false;
                }
                if (clientRepo.findByNin(agent.getPhonenum()) != null) {
                    result.rejectValue("phonenum", null, "already in use");
                    return false;
                }
                if (clientRepo.findByNin(agent.getEmail()) != null) {
                    result.rejectValue("email", null, "already in use");
                    return false;
                }
                if (clientRepo.findByNin(agent.getCcp()) != null) {
                    result.rejectValue("ccp", null, "already in use");
                    return false;
                }
                if (clientRepo.findByNin(agent.getRip()) != null) {
                    result.rejectValue("rip", null, "already in use");
                    return false;
                }

                //hna zid le check ta3 les horraires

                return true;
            }



            public boolean checkSGINAdmin() {

                Agent agent = get();

                if (agent == null) {
                    result.rejectValue("email", null, "unexisting email");
                    return false;
                }

                if (!checkAuth()) {
                    result.rejectValue("password", null, "incorrect password");
                    return false;
                }

                if (!isAdmin()) {
                    result.rejectValue("email", null, "not allowed");
                    return false;
                }

                return true;
            }

        }

    }

}
