package it.progettosiw.siwcalcio.controller;

import it.progettosiw.siwcalcio.service.CommentoService;
import it.progettosiw.siwcalcio.service.PartitaService;
import it.progettosiw.siwcalcio.service.UtenteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentoController {

    private CommentoService commentoService;

    private UtenteService utenteService;

    private PartitaService partitaService;

    public CommentoController(CommentoService commentoService, UtenteService utenteService, PartitaService partitaService) {
        this.commentoService = commentoService;
        this.utenteService = utenteService;
        this.partitaService = partitaService;
    }

    @PostMapping("/partite/{partitaId}/commenti/nuovo")
    public String postaCommento(@PathVariable Long partitaId, @RequestParam("testo") String testo, Model model){
        this.commentoService.aggiungiCommentoAPartita(testo, partitaId);
        return "redirect:/partite/"+partitaId;
    }

    @PostMapping("/partite/{partitaId}/commenti/{commentoId}/modifica")
    public String modificaCommento(@PathVariable Long commentoId, @PathVariable Long partitaId, @RequestParam("testo") String testo, Model model){
        this.commentoService.modificaCommento(testo, commentoId);
        return "redirect:/partite/"+partitaId;
    }
}
