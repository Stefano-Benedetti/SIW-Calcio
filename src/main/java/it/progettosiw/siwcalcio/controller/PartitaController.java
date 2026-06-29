package it.progettosiw.siwcalcio.controller;

import it.progettosiw.siwcalcio.service.PartitaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PartitaController {

    private PartitaService partitaService;

    public PartitaController(PartitaService partitaService) {
        this.partitaService = partitaService;
    }

    @GetMapping("/partite/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        model.addAttribute("partita", this.partitaService.getPartitaById(id));
        return "partite/partita_singola.html";
    }

}
