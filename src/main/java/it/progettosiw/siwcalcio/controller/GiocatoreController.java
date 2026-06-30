package it.progettosiw.siwcalcio.controller;

import it.progettosiw.siwcalcio.dto.GiocatoreForm;
import it.progettosiw.siwcalcio.model.Giocatore;
import it.progettosiw.siwcalcio.model.Squadra;
import it.progettosiw.siwcalcio.service.GiocatoreService;
import it.progettosiw.siwcalcio.service.SquadraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GiocatoreController {

    private GiocatoreService giocatoreService;

    private SquadraService squadraService;

    public GiocatoreController(GiocatoreService giocatoreService, SquadraService squadraService) {
        this.giocatoreService = giocatoreService;
        this.squadraService = squadraService;
    }

    @GetMapping("/giocatori/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        model.addAttribute("giocatore", this.giocatoreService.getGiocatoreById(id));
        return "giocatori/giocatore_singolo.html";
    }

    @GetMapping("/admin/giocatori/crea")
    public String getFormCreazioneGiocatore(Model model){
        model.addAttribute("squadre", this.squadraService.getAllSquadre());
        model.addAttribute("form", new GiocatoreForm());
        return "/admin/giocatori/crea_giocatore.html";
    }

    @PostMapping("/admin/giocatori/crea")
    public String makeNewGiocatore(@ModelAttribute("form") GiocatoreForm gf, Model model){
        Squadra s = this.squadraService.getSquadraById(gf.getSquadraId());
        Giocatore g = new Giocatore(gf, s);
        this.giocatoreService.save(g);
        return "redirect:/giocatori/"+g.getId().toString();
    }

    @GetMapping("/admin/giocatori/{id}/modifica")
    public String getFormModificaGiocatore(@PathVariable("id") Long id, Model model){
        Giocatore g = this.giocatoreService.getGiocatoreById(id);
        GiocatoreForm gf = new GiocatoreForm(g);
        model.addAttribute("squadre", this.squadraService.getAllSquadre());
        model.addAttribute("form", gf);
        model.addAttribute(id);
        return "/admin/giocatori/modifica_giocatore.html";
    }

    @PostMapping("/admin/giocatori/{id}/modifica")
    public String modifyGiocatore(@PathVariable("id") Long id, @ModelAttribute("form") GiocatoreForm gf, Model model){
        this.giocatoreService.modify(gf,id);
        return "redirect:/giocatori/"+id.toString();
    }
}
