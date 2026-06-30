package it.progettosiw.siwcalcio.controller;

import it.progettosiw.siwcalcio.model.Squadra;
import it.progettosiw.siwcalcio.service.SquadraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/admin/squadre/crea")
    public String getFormCreazioneSquadra(Model model){
        model.addAttribute("squadra", new Squadra());
        return "admin/squadre/crea_squadra.html";
    }

    @PostMapping("/admin/squadre/crea")
    public String makeNewSquadra(@ModelAttribute("squadra") Squadra squadra, Model model){
        this.squadraService.save(squadra);
        return "redirect:/squadre/"+squadra.getId();
    }

    @GetMapping("/admin/squadre/{id}/modifica")
    public String getFormModificaSquadra(@PathVariable("id") Long id, Model model){
        Squadra s = this.squadraService.getSquadraById(id);
        model.addAttribute("squadra", s);
        model.addAttribute(id);
        return "admin/squadre/modifica_squadra.html";
    }

    @PostMapping("/admin/squadre/{id}/modifica")
    public String modifySquadra(@PathVariable("id") Long id, @ModelAttribute("squadra") Squadra squadra, Model model){
        this.squadraService.modify(squadra,id);
        return "redirect:/squadre/"+id.toString();
    }
}
