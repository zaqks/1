package com.zaqksdev.el_meyloud_RE.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zaqksdev.el_meyloud_RE.models.dtos.auth.ClientKeyDTO;
import com.zaqksdev.el_meyloud_RE.services.repos.ClientRepo;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("client")
public class Home extends ClientKeyDTO {
    @Autowired
    private ClientRepo repo;

    @GetMapping("")
    public String showHome(Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        if (new ClientKeyDTO(repo, email, password).checkAuth()) {
            return "home/home";
        }

        return "redirect:/client/signin";

    }

}
