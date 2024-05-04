package com.zaqksdev.el_meyloud_RE.services.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zaqksdev.el_meyloud_RE.models.entities.Property;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Integer> {

}
