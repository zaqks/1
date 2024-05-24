package com.zaqksdev.el_meyloud_RE.controllers.agency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zaqksdev.el_meyloud_RE.models.Agent;
import com.zaqksdev.el_meyloud_RE.services.AgentService;
import com.zaqksdev.el_meyloud_RE.services.AuthService;
import com.zaqksdev.el_meyloud_RE.services.ClientService;
import com.zaqksdev.el_meyloud_RE.services.ContractService;
import com.zaqksdev.el_meyloud_RE.services.OfferService;
import com.zaqksdev.el_meyloud_RE.services.PropertyService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("agency/agent")
public class AgencyAgents {
    @Autowired
    private AuthService authSrvc;
    @Autowired
    private AgentService agntSrvc;
    @Autowired
    private ClientService clntSrvc;
    @Autowired
    private OfferService offrSrvc;
    @Autowired
    private PropertyService prprtSrvc;
    @Autowired
    private ContractService cntrctSrvc;

    @GetMapping("")
    public String showAllAgent(Model model,
            @CookieValue(name = "admin_email", defaultValue = "") String email,
            @CookieValue(name = "admin_password", defaultValue = "") String password) {

        model.addAttribute("agents", agntSrvc.getAllNonAdminActive());
        model.addAttribute("clients", clntSrvc.getAll());
        model.addAttribute("offers", offrSrvc.getAll());
        model.addAttribute("properties", prprtSrvc.getAll());
        model.addAttribute("contracts", cntrctSrvc.getAll());

        


        return authSrvc.new AgentAuth(email, password).kickNonAdmin("agent/showAll");

    }

    @GetMapping("/{id}")
    public String showAgent(
            @PathVariable(name = "id") int id,
            Model model,
            @CookieValue(name = "admin_email", defaultValue = "") String email,
            @CookieValue(name = "admin_password", defaultValue = "") String password) {

        Agent rslt = agntSrvc.get(id);
        if (rslt == null)
            return "redirect:/agency/agent";

        // check if ure not checking your self
        if (rslt.getEmail().equals(email))
            return "redirect:/agency/agent";

        model.addAttribute("agent", rslt);

        return authSrvc.new AgentAuth(email,
                password).kickNonAdmin("agent/show");
    }

    @GetMapping("/add")
    public String showAddAgent(
            Model model,
            @CookieValue(name = "admin_email", defaultValue = "") String email,
            @CookieValue(name = "admin_password", defaultValue = "") String password) {

        model.addAttribute("agent", new Agent());

        return authSrvc.new AgentAuth(email,
                password).kickNonAdmin("agent/add");
    }

    @PostMapping("/add")
    public String addAgent(
            Model model,
            @Valid @ModelAttribute("agent") Agent agent,
            BindingResult result,
            @CookieValue(name = "admin_email", defaultValue = "") String email,
            @CookieValue(name = "admin_password", defaultValue = "") String password) {

        String finger = authSrvc.new AgentAuth(email, password).kickNonAdmin("");
        if (!finger.equals(""))
            return finger;

        if (result.hasErrors()) {
            return "agent/add";
        }

        AuthService.AgentAuth authCheck = authSrvc.new AgentAuth();

        // check existance blaabaaa...
        if (!authCheck.new Form(result).checkSGUP(agent))
            return "auth/client/signup";

        // create agent
        authCheck.save(agent);

        return "redirect:/agency/agent";

    }

    @GetMapping("/delete/{id}")
    public String deleteAgent(
            @PathVariable(name = "id") int id,
            Model model,
            @CookieValue(name = "admin_email", defaultValue = "") String email,
            @CookieValue(name = "admin_password", defaultValue = "") String password) {
        String finger = authSrvc.new AgentAuth(email, password).kickNonAdmin("");
        if (!finger.equals(""))
            return finger;

        Agent rslt = agntSrvc.get(id);
        if (rslt == null)
            return "redirect:/agency/agent";

        // check if ure not checking your self
        if (rslt.getEmail().equals(email))
            return "redirect:/agency/agent";

        agntSrvc.deleteNonAdmin(id);

        return "redirect:/agency/agent";

    }

}
