package com.zaqksdev.el_meyloud_RE.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zaqksdev.el_meyloud_RE.models.Client;
import com.zaqksdev.el_meyloud_RE.models.Contract.Contract;

import java.util.List;
import com.zaqksdev.el_meyloud_RE.models.Agent;

@Repository
public interface ContractRepo extends JpaRepository<Contract, Integer> {
    Contract findById(int id);

    List<Contract> findBySrc(Client src);

    List<Contract> findByDst(Client dst);

    List<Contract> findByAgent(Agent agent);
}
