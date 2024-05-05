package com.zaqksdev.el_meyloud_RE.controllers.client;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.zaqksdev.el_meyloud_RE.controllers.client.utils.security.Security;
import com.zaqksdev.el_meyloud_RE.controllers.client.utils.storage.Storage;
import com.zaqksdev.el_meyloud_RE.models.dtos.property.PropertyCreateDTO;

import com.zaqksdev.el_meyloud_RE.models.entities.Property;
import com.zaqksdev.el_meyloud_RE.services.repos.ClientRepo;
import com.zaqksdev.el_meyloud_RE.services.repos.PropertyRepo;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("client")
public class ClientProperty {
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private PropertyRepo propertyRepo;

    @GetMapping("/property")
    public String showAllProperty(

            Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        model.addAttribute("properties", propertyRepo.findByOwner(clientRepo.findByEmail(email)));

        return new Security(clientRepo, email, password).kickNonSeller("property/client/showAll");
    }

    @GetMapping("/property/{id}")
    public String showProperty(
            @PathVariable(name = "id", required = true) int id,
            Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        Property rslt = propertyRepo.findById(id);
        if (rslt != null) {
            model.addAttribute("property", rslt);
            return new Security(clientRepo, email, password).kickNonSeller("property/client/show");
        } else {
            return "redirect:/client/property";
        }

    }

    @GetMapping("/property/add")
    public String showAddProperty(
            Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        model.addAttribute("property", new PropertyCreateDTO());

        return new Security(clientRepo, email, password).kickNonSeller("property/client/add");
    }

    @PostMapping("/property/add")
    public String addProperty(
            @Valid @ModelAttribute("property") PropertyCreateDTO property, BindingResult result,
            Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {
        String finger = new Security(clientRepo, email, password).kickNonSeller("");
        if (!finger.equals(""))
            return finger;

        // form validation
        if (result.hasErrors()) {
            return "property/client/add";
        }
        if (property.getImg1().isEmpty()) {
            result.rejectValue("img1", null, "1 image at least is required");
            return "property/client/add";

        }

        // existance
        if (new Storage().existingProperty(propertyRepo, result, property))
            return "property/client/add";

        Property newProp = property.convertToEntity();
        newProp.setOwner(clientRepo.findByEmail(email));

        ArrayList<MultipartFile> imgs = new ArrayList<MultipartFile>();
        imgs.add(property.getImg1());
        imgs.add(property.getImg2());
        imgs.add(property.getImg3());
        imgs.add(property.getImg4());
        imgs.add(property.getImg5());

        new Storage().saveAllImages(newProp, imgs);
        propertyRepo.save(newProp);

        return "redirect:/client/property";
    }

}
