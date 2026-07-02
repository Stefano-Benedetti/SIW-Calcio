package it.progettosiw.siwcalcio.controller;

import it.progettosiw.siwcalcio.dto.PartitaForm;
import it.progettosiw.siwcalcio.model.Partita;
import it.progettosiw.siwcalcio.model.Torneo;
import it.progettosiw.siwcalcio.service.ArbitroService;
import it.progettosiw.siwcalcio.service.PartitaService;
import it.progettosiw.siwcalcio.service.TorneoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PartitaController {

    private PartitaService partitaService;

    private TorneoService torneoService;

    private ArbitroService arbitroService;

    public PartitaController(PartitaService partitaService, TorneoService torneoService, ArbitroService arbitroService) {
        this.partitaService = partitaService;
        this.torneoService = torneoService;
        this.arbitroService = arbitroService;
    }

    @GetMapping("/partite/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        model.addAttribute("partita", this.partitaService.getPartitaById(id));
        return "partite/partita_singola.html";
    }

    @GetMapping("/admin/tornei/{id}/crea-partita")
    public String getFormCreazionePartita(@PathVariable("id") Long id, Model model){
        Torneo torneo = this.torneoService.getTorneoById(id);
        model.addAttribute("form", new PartitaForm());
        model.addAttribute("squadreIscritte", torneo.getIscrizioni());
        model.addAttribute("arbitri", this.arbitroService.getAllArbitri());
        return "admin/partite/crea_partita.html";
    }

    @PostMapping("/admin/tornei/{id}/crea-partita")
    public String makeNewPartita(@ModelAttribute("form") PartitaForm form, @PathVariable("id") Long torneoId, Model model){
        this.partitaService.creaPartita(form, torneoId);
        return "redirect:/tornei/"+torneoId+"/calendario";
    }

    @GetMapping("/admin/partite/{id}/modifica")
    public String getFormModificaPartita(@PathVariable("id") Long id, Model model){
        model.addAttribute("partita", this.partitaService.getPartitaById(id));
        return "admin/partite/modifica_partita.html";
    }

    @PostMapping("/admin/partite/{id}/inserisci-risultato")
    public String inserisciRisultatoPartita(@ModelAttribute("partita") Partita partita, @PathVariable("id") Long partitaId, Model model){
        this.partitaService.inserisciRisultato(partita.getGoalsHome(), partita.getGoalsAway(), partitaId);
        return "redirect:/partite/"+partitaId;
    }

    @PostMapping("/admin/partite/{id}/elimina")
    public String deletePartita(@ModelAttribute Partita partita, @PathVariable("id") Long partitaId, Model model){
        this.partitaService.delete(partitaId);
        return "redirect:/tornei/"+partita.getTorneo().getId();
    }

}
