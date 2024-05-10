package com.zaqksdev.el_meyloud_RE.controllers.agency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zaqksdev.el_meyloud_RE.services.AgentService;
import com.zaqksdev.el_meyloud_RE.services.AuthService;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("agency/agents")
public class AgencyAgents {
    @Autowired
    private AuthService authSrvc;
    @Autowired
    private AgentService agntSrvc;

    @GetMapping("")
    public String showAllAgent(Model model,
            @CookieValue(name = "admin_email", defaultValue = "") String email,
            @CookieValue(name = "admin_password", defaultValue = "") String password) {

        model.addAttribute("agents", agntSrvc.getAll());

        return authSrvc.new AgencyAuth(email, password).kick("agency/showAll");

    }
    /*
     * @GetMapping("/{id}")
     * public String showAgent(
     * 
     * @PathVariable(name = "id") int id,
     * Model model,
     * 
     * @CookieValue(name = "email", defaultValue = "") String email,
     * 
     * @CookieValue(name = "password", defaultValue = "") String password) {
     * 
     * Offer rslt = offrSrvc.getOf(email, id);
     * if (rslt == null)
     * return "redirect:/client/offer";
     * 
     * model.addAttribute("offer", rslt);
     * 
     * return authSrvc.new ClientAuth(email,
     * password).kickNonSeller("offer/client/show");
     * }
     * 
     * @GetMapping("/add/{id}")
     * public String showAddAgent(Model model,
     * 
     * @PathVariable(name = "id") int id,
     * 
     * @CookieValue(name = "email", defaultValue = "") String email,
     * 
     * @CookieValue(name = "password", defaultValue = "") String password) {
     * 
     * // check if prop id exists
     * if (prprtSrvc.getOf(email, id) == null)
     * return "redirect:/client/offer";
     * 
     * model.addAttribute("id", id);
     * model.addAttribute("offer", new OfferCreateDTO());
     * 
     * return authSrvc.new ClientAuth(email,
     * password).kickNonSeller("offer/client/add");
     * }
     * 
     * @PostMapping("/add/{id}")
     * public String addAgent(
     * 
     * @PathVariable(name = "id") int id,
     * Model model,
     * 
     * @Valid @ModelAttribute("offer") OfferCreateDTO offer,
     * BindingResult result,
     * 
     * @CookieValue(name = "email", defaultValue = "") String email,
     * 
     * @CookieValue(name = "password", defaultValue = "") String password) {
     * String finger = authSrvc.new ClientAuth(email, password).kickNonSeller("");
     * if (!finger.equals(""))
     * return finger;
     * 
     * Property prop = prprtSrvc.getOf(email, id);
     * if (prop == null)
     * return "redirect:/client/offer";
     * 
     * // check errors
     * if (result.hasErrors()) {
     * return "offer/client/add";
     * }
     * 
     * // save
     * Offer offr = offer.convertToEntity(prop.getOwner(), prop);
     * offrSrvc.register(offr);
     * 
     * return "redirect:/client/offer";
     * 
     * }
     */
}
