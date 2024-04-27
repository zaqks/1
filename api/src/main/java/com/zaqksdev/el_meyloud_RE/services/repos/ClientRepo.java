package com.zaqksdev.el_meyloud_RE.services.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zaqksdev.el_meyloud_RE.models.entities.Client;


@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
    //Boolean exists(Client client);
    Client findByEmail(String email);
    
}


