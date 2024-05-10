package com.zaqksdev.el_meyloud_RE.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaqksdev.el_meyloud_RE.models.Client;
import com.zaqksdev.el_meyloud_RE.models.Contract;
import com.zaqksdev.el_meyloud_RE.repos.ClientRepo;
import com.zaqksdev.el_meyloud_RE.repos.ContractRepo;

@Service
public class ContractService {
    private ContractRepo cntrctRepo;
    private ClientRepo clientRepo;

    @Autowired
    public void setCtrtRepo(ContractRepo cntrctRepo, ClientRepo clientRepo) {
        this.cntrctRepo = cntrctRepo;
        this.clientRepo = clientRepo;
    }

    public List<Contract> getOf(String email) {
        Client client = clientRepo.findByEmail(email);

        List<Contract> rslt = cntrctRepo.findBySrc(client);
        rslt.addAll(cntrctRepo.findByDst(client));

        return rslt;

    }

    public Contract getOf(String email, int ctrtID) {
        Contract rslt = cntrctRepo.findById(ctrtID);

        if (rslt != null && isMember(email, ctrtID))
            return rslt;

        return null;

    }

    public boolean isMember(String email, int ctrtID) {
        Contract contract = cntrctRepo.findById(ctrtID);

        String src = contract.getSrc().getEmail();
        String dst = contract.getDst().getName();

        return (src.equals(email) || dst.equals(email));
    }

}
