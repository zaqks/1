package com.zaqksdev.el_meyloud_RE.controllers.client;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.zaqksdev.el_meyloud_RE.dtos.property.PropertyCreateDTO;
import com.zaqksdev.el_meyloud_RE.models.Property;
import com.zaqksdev.el_meyloud_RE.services.PropertyService;
import com.zaqksdev.el_meyloud_RE.services.AuthService;
import com.zaqksdev.el_meyloud_RE.services.StorageService;

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
    private AuthService authSrvc;
    @Autowired
    private PropertyService propertySrvc;
    @Autowired
    private StorageService strgSrvc;

    @GetMapping("/property")
    public String showAllProperty(

            Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        model.addAttribute("properties", propertySrvc.getOf(email));

        return authSrvc.new ClientAuth(email, password).kickNonSeller("property/client/showAll");
    }

    @GetMapping("/property/{id}")
    public String showProperty(
            @PathVariable(name = "id", required = true) int id,
            Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        Property rslt = propertySrvc.getOf(email, id);
        
        // check if he's the owner
        if (rslt != null) {

            model.addAttribute("property", rslt);
            return authSrvc.new ClientAuth(email, password).kickNonSeller("property/client/show");

        }
        return "redirect:/client/property";

    }

    @GetMapping("/property/add")
    public String showAddProperty(
            Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        model.addAttribute("property", new PropertyCreateDTO());

        return authSrvc.new ClientAuth(email, password).kickNonSeller("property/client/add");
    }

    @PostMapping("/property/add")
    public String addProperty(
            @Valid @ModelAttribute("property") PropertyCreateDTO property, BindingResult result,
            Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {
        String finger = authSrvc.new ClientAuth(email, password).kickNonSeller("");
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

        if (propertySrvc.exists(property, result))
            return "property/client/add";

        Property newProp = property.convertToEntity();
        newProp.setOwner(authSrvc.new ClientAuth(email, password).get());

        ArrayList<MultipartFile> imgs = new ArrayList<MultipartFile>();
        imgs.add(property.getImg1());
        imgs.add(property.getImg2());
        imgs.add(property.getImg3());
        imgs.add(property.getImg4());
        imgs.add(property.getImg5());

        strgSrvc.saveAllImages(newProp, imgs);
        propertySrvc.save(newProp);

        return "redirect:/client/property";
    }

}
