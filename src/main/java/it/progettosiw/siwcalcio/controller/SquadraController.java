package it.progettosiw.siwcalcio.controller;

import it.progettosiw.siwcalcio.service.SquadraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SquadraController {

    private SquadraService squadraService;

    public SquadraController(SquadraService squadraService) {
        this.squadraService = squadraService;
    }

    @GetMapping("/squadre/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        model.addAttribute("squadra", this.squadraService.getSquadraById(id));
        return "squadre/squadra_singola.html";
    }
}
