package com.zaqksdev.el_meyloud_RE.controllers.agent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("agent/visit")
public class AgentVisit {

    @GetMapping("")
    public String showAll() {
        //

        return "visit/agent/showAll";
    }

}
