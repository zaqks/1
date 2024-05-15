package com.zaqksdev.el_meyloud_RE.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zaqksdev.el_meyloud_RE.services.OfferService;
import com.zaqksdev.el_meyloud_RE.models.Offer;
import com.zaqksdev.el_meyloud_RE.services.AuthService;

@Controller
@RequestMapping("client/market")
public class Market {
    @Autowired
    private AuthService authSrvc;
    @Autowired
    private OfferService offrSrvc;

    @GetMapping("")
    public String showHome(Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        // show the offers not owned by the client
        model.addAttribute("offers", offrSrvc.getNoOf(email));

        return authSrvc.new ClientAuth(email, password).kickNonLogged("market/showAll");

    }

    @GetMapping("/{id}")
    public String showOffer(
            @PathVariable(name = "id") int id,
            Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        Offer rslt = offrSrvc.getOf(email, id);
        if (rslt != null)
            return "redirect:/client/market";

        model.addAttribute("offer", rslt);
        model.addAttribute("owns", false);

        return authSrvc.new ClientAuth(email, password).kickNonSeller("offer/client/show");
    }

}
