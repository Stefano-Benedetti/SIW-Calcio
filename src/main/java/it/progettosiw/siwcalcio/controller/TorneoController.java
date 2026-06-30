package it.progettosiw.siwcalcio.controller;

import it.progettosiw.siwcalcio.service.PartitaService;
import it.progettosiw.siwcalcio.service.TorneoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TorneoController {

    private TorneoService torneoService;
    private PartitaService partitaService;

    public TorneoController(TorneoService torneoService, PartitaService partitaService){
        this.torneoService = torneoService;
        this.partitaService = partitaService;
    }

    @GetMapping("/tornei")
    public String getTornei(Model model){
        model.addAttribute("tornei", this.torneoService.getAllTornei());
        return "tornei/elenco_tornei.html";
    }

    @GetMapping("/tornei/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        model.addAttribute("torneo", this.torneoService.getTorneoById(id));
        model.addAttribute("partiteOggi", this.partitaService.getPartiteDiOggiPerTorneo(id));
        return "tornei/torneo_singolo.html";
    }

    @GetMapping("/tornei/{id}/calendario")
    public String getCalendarioTorneo(@PathVariable("id") Long id, Model model){
        model.addAttribute("torneo", this.torneoService.getTorneoById(id));
        model.addAttribute("partiteScheduled", this.partitaService.getCalendarioPerTorneo(id));
        return "tornei/calendario_torneo";
    }

    @GetMapping("/tornei/{id}/partiteterminate")
    public String getPartiteTerminate(@PathVariable("id") Long id, Model model){
        model.addAttribute("torneo", this.torneoService.getTorneoById(id));
        model.addAttribute("partitePlayed", this.partitaService.getPartiteTerminatePerTorneo(id));
        return "tornei/partite_terminate";
    }

}
