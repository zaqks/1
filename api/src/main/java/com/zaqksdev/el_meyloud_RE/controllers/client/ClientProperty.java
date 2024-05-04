package com.zaqksdev.el_meyloud_RE.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zaqksdev.el_meyloud_RE.models.dtos.auth.ClientKeyDTO;
import com.zaqksdev.el_meyloud_RE.models.dtos.property.PropertyCreateDTO;
import com.zaqksdev.el_meyloud_RE.services.repos.ClientRepo;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("client")
public class ClientProperty {
    @Autowired
    private ClientRepo repo;

    @GetMapping("")
    public String showHome(Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        if (!new ClientKeyDTO(repo, email, password).checkAuth()) {
            return "redirect:/client/signin";
        }

        return "property/home";

    }

    @GetMapping("/property/add")
    public String showAddProperty(
            Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        // check auth
        if (!new ClientKeyDTO(repo, email, password).checkAuth()) {
            return "redirect:/client/signin";
        }

        // check if the client can add
        if (!repo.findByEmail(email).isSells()) {
            return "redirect:/client";
        }

        model.addAttribute("property", new PropertyCreateDTO());

        return "property/add";
    }

    @PostMapping("/property/add")
    public String addProperty(@Valid PropertyCreateDTO property, BindingResult result, Model model) {

        System.out.println(result);

        model.addAttribute("property", property);

        if (result.hasErrors()) {
            return "property/add";
        }

        if (property.getImg1().isEmpty()) {
            result.rejectValue("img1", null, "1 image at least is required");
            return "property/add";
        }

        return "redirect:/client";
    }

}
