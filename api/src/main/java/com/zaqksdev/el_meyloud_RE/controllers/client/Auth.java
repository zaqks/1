package com.zaqksdev.el_meyloud_RE.controllers.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zaqksdev.el_meyloud_RE.models.dtos.auth.ClientKeyDTO;
import com.zaqksdev.el_meyloud_RE.models.entities.Client;
import com.zaqksdev.el_meyloud_RE.services.repos.ClientRepo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

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
    public String signIn(@Valid @ModelAttribute("client") ClientKeyDTO client, BindingResult result, Model model,
            HttpServletResponse response) throws UnsupportedEncodingException {
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

        if (!(check.getPassword().equals(client.getPassword()))) {
            result.rejectValue("password", null, "incorrect password");
            return "auth/client/signin";
        }

        Cookie emailCookie = new Cookie("email", null);
        Cookie passwordCookie = new Cookie("password", null);
        emailCookie.setMaxAge(0);
        passwordCookie.setMaxAge(0);

        response.addCookie(emailCookie);
        response.addCookie(passwordCookie);

        emailCookie.setValue(URLEncoder.encode(client.getEmail(), "UTF-8"));
        passwordCookie.setValue(URLEncoder.encode(client.getPassword(), "UTF-8"));
        emailCookie.setMaxAge(86400);
        passwordCookie.setMaxAge(86400);

        response.addCookie(emailCookie);
        response.addCookie(passwordCookie);

        return "redirect:/client";
    }

    //
    @GetMapping("signup")
    public String showSignUp(Model model) {
        model.addAttribute("client", new Client());
        return "auth/client/signup";
    }

    @PostMapping("signup")
    public String signUp(@Valid Client client, BindingResult result, Model model, HttpServletResponse response)
            throws UnsupportedEncodingException {
        if (result.hasErrors()) {
            return "auth/client/signup";
        }

        // nin, phonenum, email, ccp, rip
        // name=[val, func]
        if (repo.findByNin(client.getNin()) != null) {
            result.rejectValue("nin", null, "already in use");
            return "auth/client/signup";
        }
        if (repo.findByNin(client.getPhonenum()) != null) {
            result.rejectValue("phonenum", null, "already in use");
            return "auth/client/signup";
        }
        if (repo.findByNin(client.getEmail()) != null) {
            result.rejectValue("email", null, "already in use");
            return "auth/client/signup";
        }
        if (repo.findByNin(client.getCcp()) != null) {
            result.rejectValue("ccp", null, "already in use");
            return "auth/client/signup";
        }
        if (repo.findByNin(client.getRip()) != null) {
            result.rejectValue("rip", null, "already in use");
            return "auth/client/signup";
        }

        repo.save(client);

        Cookie emailCookie = new Cookie("email", null);
        Cookie passwordCookie = new Cookie("password", null);
        emailCookie.setMaxAge(0);
        passwordCookie.setMaxAge(0);

        response.addCookie(emailCookie);
        response.addCookie(passwordCookie);

        emailCookie.setValue(URLEncoder.encode(client.getEmail(), "UTF-8"));
        passwordCookie.setValue(URLEncoder.encode(client.getPassword(), "UTF-8"));
        emailCookie.setMaxAge(86400);
        passwordCookie.setMaxAge(86400);

        response.addCookie(emailCookie);
        response.addCookie(passwordCookie);

        return "redirect:/client";
    }

}
