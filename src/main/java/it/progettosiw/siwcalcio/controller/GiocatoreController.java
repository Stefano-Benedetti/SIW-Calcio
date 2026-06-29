package it.progettosiw.siwcalcio.controller;

import it.progettosiw.siwcalcio.service.GiocatoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GiocatoreController {

    private GiocatoreService giocatoreService;

    public GiocatoreController(GiocatoreService giocatoreService) {
        this.giocatoreService = giocatoreService;
    }

    @GetMapping("/giocatori/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        model.addAttribute("giocatore", this.giocatoreService.getGiocatoreById(id));
        return "giocatori/giocatore_singolo.html";
    }
}
