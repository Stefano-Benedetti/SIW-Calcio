package it.progettosiw.siwcalcio.controller;

import it.progettosiw.siwcalcio.dto.PartitaForm;
import it.progettosiw.siwcalcio.exceptions.ArbitroNonEsisteException;
import it.progettosiw.siwcalcio.exceptions.SquadraControSeStessaException;
import it.progettosiw.siwcalcio.exceptions.SquadraNonEsisteException;
import it.progettosiw.siwcalcio.model.Commento;
import it.progettosiw.siwcalcio.model.Partita;
import it.progettosiw.siwcalcio.model.Torneo;
import it.progettosiw.siwcalcio.service.*;
import it.progettosiw.siwcalcio.validation.DataPartitaValidator;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PartitaController {

    private final PartitaService partitaService;

    private final TorneoService torneoService;

    private final ArbitroService arbitroService;

    private final UtenteService utenteService;

    private final DataPartitaValidator dataPartitaValidator;

    public PartitaController(PartitaService partitaService, TorneoService torneoService,
                             ArbitroService arbitroService, UtenteService utenteService,
                             DataPartitaValidator dataPartitaValidator) {
        this.partitaService = partitaService;
        this.torneoService = torneoService;
        this.arbitroService = arbitroService;
        this.utenteService = utenteService;
        this.dataPartitaValidator = dataPartitaValidator;
    }

    @GetMapping("/partite/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        model.addAttribute("commento", new Commento());
        model.addAttribute("nuovoCommento", new Commento());
        model.addAttribute("partita", this.partitaService.getPartitaById(id));
        UserDetails userDetails = utenteService.getCurrentUserDetails();
        if (userDetails!=null)
            model.addAttribute("username", userDetails.getUsername());
        return "partite/partita_singola";
    }

    @GetMapping("/admin/tornei/{id}/crea-partita")
    public String getFormCreazionePartita(@PathVariable("id") Long id, Model model){
        //torneo non trovato -> 404
        Torneo torneo = this.torneoService.getTorneoById(id);
        model.addAttribute("form", new PartitaForm());
        model.addAttribute("squadreIscritte", torneo.getIscrizioni());
        model.addAttribute("arbitri", this.arbitroService.getAllArbitri());
        return "admin/partite/crea_partita";
    }

    @PostMapping("/admin/tornei/{id}/crea-partita")
    public String makeNewPartita(@Valid @ModelAttribute("form") PartitaForm form, BindingResult b,
                                 @PathVariable("id") Long torneoId, Model model){
        //torneo non trovato -> 404
        this.dataPartitaValidator.validate(form,b);
        if (b.hasErrors()) {
            Torneo torneo = this.torneoService.getTorneoById(torneoId);
            model.addAttribute("squadreIscritte", torneo.getIscrizioni());
            model.addAttribute("arbitri", this.arbitroService.getAllArbitri());
            return "admin/partite/crea_partita";
        }

        try {
            this.partitaService.creaPartita(form, torneoId);
            return "redirect:/tornei/" + torneoId + "/calendario";
        } catch(SquadraNonEsisteException | ArbitroNonEsisteException | SquadraControSeStessaException e){
            if(e instanceof SquadraControSeStessaException)
                b.reject("partita.SquadraControSeStessa");
            Torneo torneo = this.torneoService.getTorneoById(torneoId);
            model.addAttribute("squadreIscritte", torneo.getIscrizioni());
            model.addAttribute("arbitri", this.arbitroService.getAllArbitri());
            return "admin/partite/crea_partita";
        }
    }

    @GetMapping("/admin/partite/{id}/modifica")
    public String getFormModificaPartita(@PathVariable("id") Long id, Model model){
        model.addAttribute("partita", this.partitaService.getPartitaById(id));
        return "admin/partite/modifica_partita";
    }

    @PostMapping("/admin/partite/{id}/inserisci-risultato")
    public String inserisciRisultatoPartita(@Valid @ModelAttribute("partita") Partita partita, BindingResult b,
                                            @PathVariable("id") Long partitaId, Model model){
        if (b.hasErrors()) {
            model.addAttribute("partita", this.partitaService.getPartitaById(partitaId));
            return "admin/partite/modifica_partita";
        }
        this.partitaService.inserisciRisultato(partita.getGoalsHome(), partita.getGoalsAway(), partitaId);
        return "redirect:/partite/"+partitaId;
    }

    @PostMapping("/admin/partite/{id}/elimina")
    public String deletePartita(@PathVariable("id") Long partitaId, Model model){
        this.partitaService.deletePartita(partitaId);
        return "redirect:/";
    }

}
