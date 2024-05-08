package com.zaqksdev.el_meyloud_RE.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.datatype.jsr310.ser.OffsetTimeSerializer;
import com.zaqksdev.el_meyloud_RE.dtos.offer.OfferCreateDTO;
import com.zaqksdev.el_meyloud_RE.models.Offer;
import com.zaqksdev.el_meyloud_RE.models.Property;
import com.zaqksdev.el_meyloud_RE.repos.ClientRepo;
import com.zaqksdev.el_meyloud_RE.repos.OfferRepo;
import com.zaqksdev.el_meyloud_RE.repos.PropertyRepo;
import com.zaqksdev.el_meyloud_RE.services.OfferService;
import com.zaqksdev.el_meyloud_RE.services.PropertyService;
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
    private SecurityService authSrvc;
    @Autowired
    private OfferService offrSrvc;
    @Autowired
    private PropertyService prprtSrvc;

    @GetMapping("")
    public String showAllOffer(Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        model.addAttribute("offers", offrSrvc.getOf(email));

        return authSrvc.new ClientAuth(email, password).kickNonSeller("offer/client/showAll");

    }

    @GetMapping("/{id}")
    public String showOffer(
            @PathVariable(name = "id") int id,
            Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        Offer rslt = offrSrvc.getOf(email, id);
        if (rslt == null)
            return "redirect:/client/offer";

        model.addAttribute("offer", rslt);

        return authSrvc.new ClientAuth(email, password).kickNonSeller("offer/client/show");
    }

    @GetMapping("/add/{id}")
    public String showAddOffer(Model model,
            @PathVariable(name = "id") int id,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        // check if prop id exists
        if (prprtSrvc.getOf(email, id) == null)
            return "redirect:/";

        model.addAttribute("id", id);
        model.addAttribute("offer", new OfferCreateDTO());

        return authSrvc.new ClientAuth(email, password).kickNonSeller("offer/client/add");
    }

    @PostMapping("/add/{id}")
    public String addOffer(
            @PathVariable(name = "id") int id,
            Model model,
            @Valid @ModelAttribute("offer") OfferCreateDTO offer,
            BindingResult result,

            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {
        String finger = authSrvc.new ClientAuth(email, password).kickNonSeller("");
        if (!finger.equals(""))
            return finger;

        Property prop = prprtSrvc.getOf(email, id);
        if (prop == null)
            return "redirect:/";

        // check errors
        if (result.hasErrors()) {
            return "offer/client/add";
        }

        // save
        Offer offr = offer.convertToEntity(prop.getOwner(), prop);
        offrSrvc.save(offr);

        return "redirect:/client/offer";

    }

}
