package com.zaqksdev.el_meyloud_RE.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zaqksdev.el_meyloud_RE.models.Agent;
import java.util.List;





@Repository
public interface AgentRepo extends JpaRepository<Agent, Integer> {
    Agent findById(int id);
    Agent findByEmail(String email);
    List<Agent> findByAdmin(boolean admin);    

    //nin, phonenum, email, ccp, rip
    Agent findByNin(String val);
    Agent findByPhonenum(String val);
    Agent findByCcp(String val);
    Agent findByRip(String val);


}


