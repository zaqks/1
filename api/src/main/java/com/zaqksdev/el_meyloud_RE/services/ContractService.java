package com.zaqksdev.el_meyloud_RE.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaqksdev.el_meyloud_RE.models.Client;
import com.zaqksdev.el_meyloud_RE.models.Offer;
import com.zaqksdev.el_meyloud_RE.models.Visit;
import com.zaqksdev.el_meyloud_RE.models.Contract.Contract;
import com.zaqksdev.el_meyloud_RE.models.Contract.ContractType;
import com.zaqksdev.el_meyloud_RE.repos.AgentRepo;
import com.zaqksdev.el_meyloud_RE.repos.ClientRepo;
import com.zaqksdev.el_meyloud_RE.repos.ContractRepo;

@Service
public class ContractService {
    static ContractRepo cntrctRepo;
    static ClientRepo clientRepo;
    static AgentRepo agentRepo;

    @Autowired
    public void setCtrtRepo(ContractRepo cntrctRepo, ClientRepo clientRepo, AgentRepo agentRepo) {
        ContractService.cntrctRepo = cntrctRepo;
        ContractService.clientRepo = clientRepo;
        ContractService.agentRepo = agentRepo;

    }

    public List<Contract> getOf(String client_email) {
        Client client = clientRepo.findByEmail(client_email);

        List<Contract> rslt = cntrctRepo.findBySrc(client);

        List<Contract> rslt2 = cntrctRepo.findByDst(client);

        Contract current;
        for (int i = 0; i < rslt2.size(); i++) {
            current = rslt2.get(i);

            if (!rslt.contains(current))
                rslt.add(current);

        }

        return rslt;

    }

    public Contract getOf(String email, int ctrtID) {
        Contract rslt = cntrctRepo.findById(ctrtID);

        if (rslt != null && isPart(email, ctrtID))
            return rslt;

        return null;

    }

    public boolean isPart(String email, int ctrtID) {
        Contract contract = cntrctRepo.findById(ctrtID);

        String src = contract.getSrc().getEmail();
        String dst = contract.getDst().getEmail();

        return (src.equals(email) || dst.equals(email));
    }

    public List<Contract> getBy(String agent_email) {
        return cntrctRepo.findByAgent(agentRepo.findByEmail(agent_email));

    }

    public Contract getBy(String agent_email, int cntrct_id) {
        Contract rslt = cntrctRepo.findById(cntrct_id);

        if (!rslt.getAgent().getEmail().equals(agent_email))
            return null;

        return rslt;

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
                (ContractType.values())[((offr.isChecked() ? 0 : 2) + (offr.isRent() ? 0 : 1))]

        );

        cntrctRepo.save(contract);
    }

}
