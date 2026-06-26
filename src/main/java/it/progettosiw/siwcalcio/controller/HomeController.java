package it.progettosiw.siwcalcio.controller;

import it.progettosiw.siwcalcio.service.UtenteService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private UtenteService utenteService;

    public HomeController(UtenteService utenteService){
        this.utenteService = utenteService;
    }

    @GetMapping("/")
    public String getHome(Model model){
        UserDetails userDetails = utenteService.getCurrentUserDetails();
        if (userDetails!=null)
            model.addAttribute("username", userDetails.getUsername());
        return "index.html";
    }

}