package com.zaqksdev.el_meyloud_RE.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaqksdev.el_meyloud_RE.models.Agent;

import com.zaqksdev.el_meyloud_RE.repos.AgentRepo;

@Service
public class AgentService {
    private AgentRepo agntRepo;

    @Autowired
    public void setCtrtRepo(AgentRepo repo) {
        this.agntRepo = repo;

    }

    public List<Agent> getAll() {
        return agntRepo.findAll();
    }

    public Agent get(int id) {
        return agntRepo.findById(id);
    }

}
