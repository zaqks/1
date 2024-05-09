package com.zaqksdev.el_meyloud_RE.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaqksdev.el_meyloud_RE.models.Client;
import com.zaqksdev.el_meyloud_RE.models.Contract;
import com.zaqksdev.el_meyloud_RE.repos.ClientRepo;
import com.zaqksdev.el_meyloud_RE.repos.ContractRepo;

@Service
public class ContactService {
    private ContractRepo ctrtRepo;
    private ClientRepo clientRepo;

    @Autowired
    public void setCtrtRepo(ContractRepo ctrtRepo, ClientRepo clientRepo) {
        this.ctrtRepo = ctrtRepo;
        this.clientRepo = clientRepo;
    }

    public List<Contract> getOf(String email) {
        Client client = clientRepo.findByEmail(email);
        
        List<Contract> rslt = ctrtRepo.findBySrc(client);
        rslt.addAll(ctrtRepo.findByDst(client));

        return rslt;

    }

}
