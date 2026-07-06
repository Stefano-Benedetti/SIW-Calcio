package it.progettosiw.siwcalcio.controller;

import it.progettosiw.siwcalcio.model.Commento;
import it.progettosiw.siwcalcio.service.CommentoService;
import it.progettosiw.siwcalcio.service.PartitaService;
import it.progettosiw.siwcalcio.service.UtenteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentoController {

    private CommentoService commentoService;

    private PartitaService partitaService;

    private UtenteService utenteService;

    public CommentoController(CommentoService commentoService, PartitaService partitaService, UtenteService utenteService) {
        this.commentoService = commentoService;
        this.partitaService = partitaService;
        this.utenteService = utenteService;
    }

    @PostMapping("/partite/{partitaId}/commenti/nuovo")
    public String postaCommento(@Valid @ModelAttribute("nuovoCommento") Commento c, BindingResult b,
                                @PathVariable Long partitaId, Model model){
        if(b.hasErrors()){
            model.addAttribute("partita", this.partitaService.getPartitaById(partitaId));
            if (!model.containsAttribute("commento")) model.addAttribute("commento", new Commento());
            UserDetails userDetails = utenteService.getCurrentUserDetails();
            if (userDetails!=null) model.addAttribute("username", userDetails.getUsername());
            return "partite/partita_singola";
        }
        this.commentoService.aggiungiCommentoAPartita(c.getTesto(), partitaId);
        return "redirect:/partite/"+partitaId;
    }

    @PostMapping("/partite/{partitaId}/commenti/{commentoId}/modifica")
    public String modificaCommento(@Valid @ModelAttribute("commento") Commento c, BindingResult b,
                                   @PathVariable Long commentoId, @PathVariable Long partitaId, Model model){
        if(b.hasErrors()){
            model.addAttribute("partita", this.partitaService.getPartitaById(partitaId));
            if (!model.containsAttribute("nuovoCommento")) model.addAttribute("nuovoCommento", new Commento());
            UserDetails userDetails = utenteService.getCurrentUserDetails();
            if (userDetails!=null) model.addAttribute("username", userDetails.getUsername());
            return "partite/partita_singola";
        }
        this.commentoService.modificaCommento(c.getTesto(), commentoId);
        return "redirect:/partite/"+partitaId;
    }
}
