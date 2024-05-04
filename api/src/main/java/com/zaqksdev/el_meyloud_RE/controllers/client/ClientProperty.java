package com.zaqksdev.el_meyloud_RE.controllers.client;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("client")
public class ClientProperty {
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private PropertyRepo propertyRepo;

    @GetMapping("/property")
    public String showProperty(

            Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {
        return "property/client/show";
    }

    @GetMapping("/property/add")
    public String showAddProperty(
            Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {

        model.addAttribute("property", new PropertyCreateDTO());

        return new Security(clientRepo, email, password).kickNonSeller("property/add");
    }

    @PostMapping("/property/client/add")
    public String addProperty(@Valid @ModelAttribute("property") PropertyCreateDTO property, BindingResult result,
            Model model,
            @CookieValue(name = "email", defaultValue = "") String email,
            @CookieValue(name = "password", defaultValue = "") String password) {
        String finger = new Security(clientRepo, email, password).kickNonSeller("");
        if (!finger.equals(""))
            return finger;

        if (result.hasErrors()) {
            return "property/add";
        }

        if (property.getImg1().isEmpty()) {
            result.rejectValue("img1", null, "1 image at least is required");
            return "property/add";

        }

        // existance
        if (existingProperty(result, property))
            return "property/add";

        Property newProp = property.convertToEntity();
        newProp.setOwner(clientRepo.findByEmail(email));

        ArrayList<MultipartFile> imgs = new ArrayList<MultipartFile>();
        imgs.add(property.getImg1());
        imgs.add(property.getImg2());
        imgs.add(property.getImg3());
        imgs.add(property.getImg4());
        imgs.add(property.getImg5());

        newProp.setImgs(new Storage().saveAllImages(imgs));
        propertyRepo.save(newProp);

        return "redirect:/client/property";
    }

    Boolean existingProperty(BindingResult result, PropertyCreateDTO property) {
        // check the addr
        if (propertyRepo.findByAddr(property.getAddr()) != null) {
            result.rejectValue("addr", null, "already existing");
            return true;
        }
        // check the coords
        float x = property.getX();
        float y = property.getY();
        int i;

        List<Property> xlst = propertyRepo.findByX(x);
        List<Property> ylst = propertyRepo.findByX(y);

        for (i = 0; i < xlst.size(); i++) {
            if (xlst.get(i).getY() == y) {
                result.rejectValue("x", null, "already existing");
                result.rejectValue("y", null, "already existing");
                return true;
            }
        }

        for (i = 0; i < ylst.size(); i++) {
            if (ylst.get(i).getX() == x) {
                result.rejectValue("x", null, "already existing");
                result.rejectValue("y", null, "already existing");
                return true;
            }
        }

        return false;
    }

}
