package com.zaqksdev.el_meyloud_RE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaqksdev.el_meyloud_RE.models.Client;
import com.zaqksdev.el_meyloud_RE.repos.ClientRepo;

@Service
public class ClientService {
    private ClientRepo clientRepo;

    @Autowired
    public void setClient_Repo(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    Client get(String email) {
        return clientRepo.findByEmail(email);
    }

}
