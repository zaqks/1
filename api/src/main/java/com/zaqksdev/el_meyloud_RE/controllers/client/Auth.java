package com.zaqksdev.el_meyloud_RE.controllers.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.zaqksdev.el_meyloud_RE.models.Client;

@Controller
@RequestMapping("user/")
public class Auth {

    @GetMapping("signin")
    public String showSignIn(Model model) {
        // model.addAttribute("user", new User());
        return "auth/client/signin";
    }

    /*
     * @PostMapping("/add")
     * public String signIn(@Valid User user, BindingResult result, Model model) {
     * if (result.hasErrors()) {
     * System.out.println(result);
     * return "user/create";
     * }
     * repository.save(user);
     * 
     * return "products/index";
     * }
     */

    @GetMapping("signup")
    public String showSignUp(Model model) {
        model.addAttribute("client", new Client());
        return "auth/client/signup";
    }

    @PostMapping("signup")
    public String signIn(@Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(result);
            return "auth/client/signup";
        }

        return "auth/client/signup";
    }

}
