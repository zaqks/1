package com.zaqksdev.el_meyloud_RE.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.zaqksdev.el_meyloud_RE.models.dtos.auth.ClientKeyDTO;
import com.zaqksdev.el_meyloud_RE.models.entities.Client;
import com.zaqksdev.el_meyloud_RE.services.repos.ClientRepo;

@Controller
@RequestMapping("client/")
public class Auth {
    @Autowired
    private ClientRepo repo;

    @GetMapping("signin")
    public String showSignIn(Model model) {
        model.addAttribute("client", new ClientKeyDTO());
        return "auth/client/signin";
    }

    @PostMapping("signin")
    public String signIn(@Valid @ModelAttribute("client") ClientKeyDTO client, BindingResult result, Model model) {
        model.addAttribute("client", client);

        if (result.hasErrors()) {
            return "auth/client/signin";
        }
        // check existance
        Client check = repo.findByEmail(client.getEmail());

        if (check == null) {
            result.rejectValue("email", null, "unexisting email");
            return "auth/client/signin";
        }

        if (check.getPassword() != client.getPassword()) {
            result.rejectValue("email", null, "incorrect password");
            return "auth/client/signin";
        }

        return "redirect:/client";
    }

    //
    @GetMapping("signup")
    public String showSignUp(Model model) {
        model.addAttribute("client", new Client());
        return "auth/client/signup";
    }

    @PostMapping("signup")
    public String signUp(@Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(result);
            return "auth/client/signup";
        }

        return "redirect:/client";
    }

}
