package it.progettosiw.siwcalcio.controller;

import it.progettosiw.siwcalcio.dto.RegistrationForm;
import it.progettosiw.siwcalcio.exceptions.NomeUtenteGiaInUsoException;
import it.progettosiw.siwcalcio.service.UtenteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UtenteController {

    private final UtenteService utenteService;

    public UtenteController(UtenteService utenteService){
        this.utenteService = utenteService;
    }

    @GetMapping("/login")
    public String getLogin(Model model){
        return "login";
    }

    @GetMapping("/register")
    public String getRegister(Model model){
        model.addAttribute("form", new RegistrationForm());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("form") RegistrationForm form, BindingResult b, Model model){
        if (b.hasErrors()) {
            return "register";
        }
        try {
            utenteService.register(form);
            return "redirect:/login";
        } catch(NomeUtenteGiaInUsoException e){
            b.reject("registrazione.NomeUtenteGiaInUso");
            return "register";
        }
    }
}
