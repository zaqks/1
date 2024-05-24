package com.zaqksdev.el_meyloud_RE.controllers.agent;

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

import com.zaqksdev.el_meyloud_RE.dtos.auth.ClientKeyDTO;
import com.zaqksdev.el_meyloud_RE.services.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("agent/")
public class AgentAuth {
    @Autowired
    private AuthService authSrvc;

    @GetMapping("signin")
    public String showAgencySignIn(Model model) {
        model.addAttribute("agent", new ClientKeyDTO());
        return "auth/agent/signin";
    }

    @PostMapping("signin")
    public String signIn(@Valid @ModelAttribute("agent") ClientKeyDTO agent,
            BindingResult result, Model model,
            HttpServletResponse response) throws UnsupportedEncodingException {
        model.addAttribute("agent", agent);

        if (result.hasErrors()) {
            return "auth/agent/signin";
        }

        String email = agent.getEmail();
        String password = agent.getPassword();

        // check existance
        if (!authSrvc.new AgentAuth(email, password).new Form(result).checkSGIN())
            return "auth/agent/signin";

        // set the cookies
        Cookie emailCookie = new Cookie("agent_email", null);
        Cookie passwordCookie = new Cookie("agent_password", null);
        emailCookie.setMaxAge(0);
        passwordCookie.setMaxAge(0);

        response.addCookie(emailCookie);
        response.addCookie(passwordCookie);

        emailCookie.setValue(URLEncoder.encode(agent.getEmail(), "UTF-8"));
        passwordCookie.setValue(URLEncoder.encode(agent.getPassword(), "UTF-8"));
        emailCookie.setMaxAge(86400);
        passwordCookie.setMaxAge(86400);

        response.addCookie(emailCookie);
        response.addCookie(passwordCookie);

        return "redirect:/agent";
    }

    //
    /*
     * @GetMapping("signup")
     * public String showSignUp(Model model) {
     * model.addAttribute("client", new Client());
     * return "auth/client/signup";
     * }
     * 
     * @PostMapping("signup")
     * public String signUp(@Valid Client client, BindingResult result, Model model,
     * HttpServletResponse response)
     * throws UnsupportedEncodingException {
     * if (result.hasErrors()) {
     * return "auth/client/signup";
     * }
     * 
     * String email = client.getEmail();
     * String password = client.getPassword();
     * 
     * if (!authSrvc.new ClientAuth().new Form(result).checkSGUP(client))
     * return "auth/client/signup";
     * 
     * authSrvc.new ClientAuth(email, password).save(client);
     * 
     * // set the cookies
     * Cookie emailCookie = new Cookie("email", null);
     * Cookie passwordCookie = new Cookie("password", null);
     * emailCookie.setMaxAge(0);
     * passwordCookie.setMaxAge(0);
     * 
     * response.addCookie(emailCookie);
     * response.addCookie(passwordCookie);
     * 
     * emailCookie.setValue(URLEncoder.encode(client.getEmail(), "UTF-8"));
     * passwordCookie.setValue(URLEncoder.encode(client.getPassword(), "UTF-8"));
     * emailCookie.setMaxAge(86400);
     * passwordCookie.setMaxAge(86400);
     * 
     * response.addCookie(emailCookie);
     * response.addCookie(passwordCookie);
     * 
     * return "redirect:/client";
     * }
     */
}
