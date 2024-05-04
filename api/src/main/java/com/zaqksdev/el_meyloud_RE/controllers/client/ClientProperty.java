package com.zaqksdev.el_meyloud_RE.controllers.client;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.zaqksdev.el_meyloud_RE.models.dtos.auth.ClientKeyDTO;
import com.zaqksdev.el_meyloud_RE.models.dtos.property.PropertyCreateDTO;
import com.zaqksdev.el_meyloud_RE.models.entities.Property;
import com.zaqksdev.el_meyloud_RE.services.repos.ClientRepo;
import com.zaqksdev.el_meyloud_RE.services.repos.PropertyRepo;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("client")
public class ClientProperty {
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private PropertyRepo propertyRepo;

    @GetMapping("")
    public String showHome(Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        if (!new ClientKeyDTO(clientRepo, email, password).checkAuth()) {
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
        if (!new ClientKeyDTO(clientRepo, email, password).checkAuth()) {
            return "redirect:/client/signin";
        }

        // check if the client can add
        if (!clientRepo.findByEmail(email).isSells()) {
            return "redirect:/client";
        }

        model.addAttribute("property", new PropertyCreateDTO());

        return "property/add";
    }

    @PostMapping("/property/add")
    public String addProperty(@Valid @ModelAttribute("property") PropertyCreateDTO property, BindingResult result,
            Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        // check auth
        if (!new ClientKeyDTO(clientRepo, email, password).checkAuth()) {
            return "redirect:/client/signin";
        }

        // check if the client can add
        if (!clientRepo.findByEmail(email).isSells()) {
            return "redirect:/client";
        }

        if (result.hasErrors()) {
            return "property/add";
        }

        if (property.getImg1().isEmpty()) {
            result.rejectValue("img1", null, "1 image at least is required");
            return "property/add";
        }

        // existance
        // check the addr
        // check the coords

        Property newProp = property.convertToEntity();
        newProp.setOwner(clientRepo.findByEmail(email));

        propertyRepo.save(newProp);

        return "property/add";
        // return "redirect:/client";
    }

    void saveImage(MultipartFile image) {
        // Date createdAt = new Date();
        String dst = "public/images/properties/" + new Date() + "_" + image.getOriginalFilename();

        try {
            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(dst), StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (Exception e) {
        }
    }

    class Auth {
        private String email;
        private String pwd;

        public Auth(String email, String pwd) {
            this.email = email;
            this.pwd = pwd;
        }

        Boolean loggedIn(String email, String password) {
            return new ClientKeyDTO(clientRepo, email, password).checkAuth();

        }

        Boolean canSell(
                String email, String password) {
            return loggedIn(email, password) && clientRepo.findByEmail(email).isSells();
        }

        String kickNonLogged(String src) {
            if (!loggedIn(email, pwd)) {
                return "redirect:/client/signin";
            }
            return src;
        }

        String kickNonSeller(String src) {
            if (!canSell(email, pwd)) {
                return "redirect:/client";
            }

            return src;

        }

    }

}
