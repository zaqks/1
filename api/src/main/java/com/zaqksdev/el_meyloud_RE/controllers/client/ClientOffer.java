package com.zaqksdev.el_meyloud_RE.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zaqksdev.el_meyloud_RE.controllers.client.utils.security.Security;
import com.zaqksdev.el_meyloud_RE.models.dtos.offer.OfferCreateDTO;
import com.zaqksdev.el_meyloud_RE.models.entities.Offer;
import com.zaqksdev.el_meyloud_RE.services.repos.ClientRepo;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("client/offer")

public class ClientOffer {
    @Autowired
    private ClientRepo clientRepo;

    @GetMapping("")
    public String showAllOffer(Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        return new Security(clientRepo, email, password).kickNonSeller("offer/client/showAll");
    }

    @GetMapping("/add")
    public String showAddOffer(Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        model.addAttribute("offer", new Offer());

        return new Security(clientRepo, email, password).kickNonSeller("offer/client/add");
    }

    @PostMapping("/add")
    public String addOffer(Model model,
            @Valid @ModelAttribute("offer") OfferCreateDTO offer, BindingResult result,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {
        String finger = new Security(clientRepo, email, password).kickNonSeller("");
        if (!finger.equals(""))
            return finger;

        model.addAttribute("offer", offer);

        return new Security(clientRepo, email, password).kickNonSeller("offer/client/add");
    }

}
