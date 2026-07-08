package it.progettosiw.siwcalcio.controller;

import it.progettosiw.siwcalcio.model.Squadra;
import it.progettosiw.siwcalcio.service.SquadraService;
import it.progettosiw.siwcalcio.validation.AnnoFondazioneValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SquadraController {

    private final SquadraService squadraService;

    private final AnnoFondazioneValidator annoFondazioneValidator;

    public SquadraController(SquadraService squadraService, AnnoFondazioneValidator annoFondazioneValidator) {
        this.squadraService = squadraService;
        this.annoFondazioneValidator = annoFondazioneValidator;
    }

    @GetMapping("/squadre/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        model.addAttribute("squadra", this.squadraService.getSquadraById(id));
        return "squadre/squadra_singola";
    }

    @GetMapping("/squadre")
    public String getSquadre(Model model){
        model.addAttribute("squadre", this.squadraService.getAllSquadre());
        return "squadre/elenco_squadre";
    }

    @GetMapping("/admin/squadre/crea")
    public String getFormCreazioneSquadra(Model model){
        model.addAttribute("squadra", new Squadra());
        return "admin/squadre/crea_squadra";
    }

    @PostMapping("/admin/squadre/crea")
    public String makeNewSquadra(@Valid @ModelAttribute("squadra") Squadra squadra, BindingResult b, Model model){
        this.annoFondazioneValidator.validate(squadra,b);
        if (b.hasErrors()) {
            return "admin/squadre/crea_squadra";
        }
        this.squadraService.save(squadra);
        return "redirect:/squadre/"+squadra.getId();
    }

    @GetMapping("/admin/squadre/{id}/modifica")
    public String getFormModificaSquadra(@PathVariable("id") Long id, Model model){
        Squadra s = this.squadraService.getSquadraById(id);
        model.addAttribute("squadra", s);
        return "admin/squadre/modifica_squadra";
    }

    @PostMapping("/admin/squadre/{id}/modifica")
    public String modifySquadra(@PathVariable("id") Long id, @Valid @ModelAttribute("squadra") Squadra squadra, BindingResult b, Model model){
        this.annoFondazioneValidator.validate(squadra,b);
        if (b.hasErrors()) {
            return "admin/squadre/modifica_squadra";
        }
        this.squadraService.modify(squadra,id);
        return "redirect:/squadre/"+id;
    }

    @PostMapping("/admin/squadre/{id}/elimina")
    public String deleteSquadra(@PathVariable("id") Long id, Model model){
        this.squadraService.deleteSquadra(id);
        return "redirect:/";
    }
}
