package com.zaqksdev.el_meyloud_RE.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaqksdev.el_meyloud_RE.models.Client;
import com.zaqksdev.el_meyloud_RE.models.Offer;
import com.zaqksdev.el_meyloud_RE.models.Visit;
import com.zaqksdev.el_meyloud_RE.models.Contract.Contract;
import com.zaqksdev.el_meyloud_RE.models.Contract.ContractType;
import com.zaqksdev.el_meyloud_RE.repos.ClientRepo;
import com.zaqksdev.el_meyloud_RE.repos.ContractRepo;

@Service
public class ContractService {
    static ContractRepo cntrctRepo;
    static ClientRepo clientRepo;

    @Autowired
    public void setCtrtRepo(ContractRepo cntrctRepo, ClientRepo clientRepo) {
        ContractService.cntrctRepo = cntrctRepo;
        ContractService.clientRepo = clientRepo;
    }

    public List<Contract> getOf(String client_email) {
        Client client = clientRepo.findByEmail(client_email);

        List<Contract> rslt = cntrctRepo.findBySrc(client);
        List<Contract> rslt2 = cntrctRepo.findByDst(client);

        Contract current;
        for (int i = 0; i < rslt2.size(); i++) {
            current = rslt2.get(i);

            if (!rslt2.contains(current))
                rslt2.add(current);

        }

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

    public void createContract(
            Visit vst) {

        Offer offr = vst.getOffer();
        Contract contract = new Contract();

        contract.setOffer(offr);
        contract.setSrc(offr.getProperty().getOwner());
        contract.setDst(vst.getClient());
        contract.setAgent(vst.getAgent());

        // now lets set the contract type
        contract.setType(
                ContractType.values()[(offr.isChecked() ? 0 : 2) + (offr.isRent() ? 0 : 1)]);

        cntrctRepo.save(contract);
    }

}
