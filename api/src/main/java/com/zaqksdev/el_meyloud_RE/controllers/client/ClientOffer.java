package com.zaqksdev.el_meyloud_RE.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zaqksdev.el_meyloud_RE.dtos.offer.OfferCreateDTO;
import com.zaqksdev.el_meyloud_RE.models.Offer;
import com.zaqksdev.el_meyloud_RE.repos.ClientRepo;
import com.zaqksdev.el_meyloud_RE.repos.OfferRepo;
import com.zaqksdev.el_meyloud_RE.repos.PropertyRepo;
import com.zaqksdev.el_meyloud_RE.services.SecurityService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("client/offer")

public class ClientOffer {
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private PropertyRepo propertyRepo;
    @Autowired
    private OfferRepo offerRepo;

    @Autowired
    private SecurityService authSevice;

    @GetMapping("")
    public String showAllOffer(Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        model.addAttribute("offers", offerRepo.findByOwner(clientRepo.findByEmail(email)));

        return new SecurityService(email, password).new ClientAuth().kickNonSeller("offer/client/showAll");

    }

    @GetMapping("/{id}")
    public String showOffer(Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        model.addAttribute("offers", offerRepo.findByOwner(clientRepo.findByEmail(email)));

        return new SecurityService(email, password).new ClientAuth().kickNonSeller("offer/client/show");
    }

    @GetMapping("/add/{id}")
    public String showAddOffer(Model model,
            @PathVariable(name = "id") int id,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        model.addAttribute("id", id);
        model.addAttribute("offer", new OfferCreateDTO());

        return new SecurityService(email, password).new ClientAuth().kickNonSeller("offer/client/add");
    }

    @PostMapping("/add/{id}")
    public String addOffer(
            @PathVariable(name = "id") int id,
            Model model,
            @Valid @ModelAttribute("offer") OfferCreateDTO offer,
            BindingResult result,

            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {
        String finger = new SecurityService(email, password).new ClientAuth().kickNonSeller("");
        if (!finger.equals(""))
            return finger;

        // check errors
        if (result.hasErrors()) {
            return "offer/client/add";
        }

        // save
        Offer offr = offer.convertToEntity(clientRepo.findByEmail(email), propertyRepo.findById(id));
        offerRepo.save(offr);

        return "redirect:/client/offer";

    }

}
