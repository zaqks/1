package com.zaqksdev.el_meyloud_RE.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zaqksdev.el_meyloud_RE.models.Client;
import com.zaqksdev.el_meyloud_RE.models.Property;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Integer> {
    Property findByAddr(String addr);

    List<Property> findByX(float x);

    List<Property> findByY(float y);


    List<Property> findByOwner(Client client);

    Property findById(int id);
}
