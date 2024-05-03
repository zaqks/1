package com.zaqksdev.el_meyloud_RE.services.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zaqksdev.el_meyloud_RE.models.entities.Client;


@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
    Client findByEmail(String email);
    

    //nin, phonenum, email, ccp, rip
    Client findByNin(String val);
    Client findByPhonenum(String val);
    Client findByCcp(String val);
    Client findByRip(String val);
    
}


