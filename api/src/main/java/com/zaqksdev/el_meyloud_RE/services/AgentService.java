package com.zaqksdev.el_meyloud_RE.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaqksdev.el_meyloud_RE.models.Agent;

import com.zaqksdev.el_meyloud_RE.repos.AgentRepo;

@Service
public class AgentService {
    private AgentRepo AgentRepo;

    @Autowired
    public void setCtrtRepo(AgentRepo AgentRepo) {
        this.AgentRepo = AgentRepo;

    }

    public List<Agent> getAll() {

        return AgentRepo.findAll();

    }

}
