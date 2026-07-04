package it.progettosiw.siwcalcio.controller;

import it.progettosiw.siwcalcio.dto.IscrizioneForm;
import it.progettosiw.siwcalcio.model.SquadraIscritta;
import it.progettosiw.siwcalcio.model.Torneo;
import it.progettosiw.siwcalcio.service.PartitaService;
import it.progettosiw.siwcalcio.service.SquadraIscrittaService;
import it.progettosiw.siwcalcio.service.SquadraService;
import it.progettosiw.siwcalcio.service.TorneoService;
import it.progettosiw.siwcalcio.validation.AnnoTorneoValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TorneoController {

    private TorneoService torneoService;
    private PartitaService partitaService;
    private SquadraService squadraService;
    private SquadraIscrittaService squadraIscrittaService;
    private AnnoTorneoValidator annoTorneoValidator;

    public TorneoController(TorneoService torneoService, PartitaService partitaService, SquadraService squadraService,
                            SquadraIscrittaService squadraIscrittaService, AnnoTorneoValidator annoTorneoValidator){
        this.torneoService = torneoService;
        this.partitaService = partitaService;
        this.squadraService = squadraService;
        this.squadraIscrittaService = squadraIscrittaService;
        this.annoTorneoValidator = annoTorneoValidator;
    }

    @GetMapping("/tornei")
    public String getTornei(Model model){
        model.addAttribute("tornei", this.torneoService.getAllTornei());
        return "/tornei/elenco_tornei";
    }

    @GetMapping("/tornei/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        //torneo con quell'id non trovato -> 404
        model.addAttribute("torneo", this.torneoService.getTorneoById(id));
        model.addAttribute("partiteOggi", this.partitaService.getPartiteDiOggiPerTorneo(id));
        return "/tornei/torneo_singolo";
    }

    @GetMapping("/tornei/{id}/calendario")
    public String getCalendarioTorneo(@PathVariable("id") Long id, Model model){
        //torneo con quell'id non trovato -> 404
        model.addAttribute("torneo", this.torneoService.getTorneoById(id));
        model.addAttribute("partiteScheduled", this.partitaService.getCalendarioPerTorneo(id));
        return "/tornei/calendario_torneo";
    }

    @GetMapping("/tornei/{id}/partite-terminate")
    public String getPartiteTerminate(@PathVariable("id") Long id, Model model){
        //torneo con quell'id non trovato -> 404
        model.addAttribute("torneo", this.torneoService.getTorneoById(id));
        model.addAttribute("partitePlayed", this.partitaService.getPartiteTerminatePerTorneo(id));
        return "/tornei/partite_terminate";
    }

    @GetMapping("/tornei/{id}/classifica")
    public String getClassifica(@PathVariable("id") Long id, Model model){
        //torneo con quell'id non trovato -> 404

        model.addAttribute("torneo", this.torneoService.getTorneoById(id));

        List<SquadraIscritta> classifica = this.squadraIscrittaService.getClassificaPerTorneo(id);
        model.addAttribute("classifica", classifica);

        model.addAttribute("posizioni", this.squadraIscrittaService.getPosizioniClassifica(classifica));

        return "/tornei/classifica_torneo";
    }

    @GetMapping("/admin/tornei/crea")
    public String getFormCreazioneTorneo(Model model){
        model.addAttribute("torneo", new Torneo());
        return "/admin/tornei/crea_torneo";
    }

    @PostMapping("/admin/tornei/crea")
    public String makeNewTorneo(@Valid @ModelAttribute("torneo") Torneo torneo, BindingResult b, Model model){
        this.annoTorneoValidator.validate(torneo,b);
        if (b.hasErrors()) {
            return "/admin/tornei/crea_torneo";
        }
        this.torneoService.save(torneo);
        return "redirect:/tornei/"+torneo.getId();
    }

    @GetMapping("/admin/tornei/{id}/modifica")
    public String getFormModificaTorneo(@PathVariable("id") Long id, Model model){
        //torneo con quell'id non trovato -> 404
        Torneo torneo = this.torneoService.getTorneoById(id);
        model.addAttribute("torneo", torneo);
        model.addAttribute("squadreIscritte", torneo.getIscrizioni());
        model.addAttribute("squadreNonIscritte", this.squadraService.getSquadreNonIscritteAlTorneo(id));
        model.addAttribute("squadra", new IscrizioneForm());
        model.addAttribute("nuovaSquadra", new IscrizioneForm());
        return "/admin/tornei/modifica_torneo";
    }

    @PostMapping("/admin/tornei/{id}/modifica")
    public String modificaTorneo(@PathVariable("id") Long torneoId, @Valid @ModelAttribute("torneo") Torneo torneo, BindingResult b, Model model){
        //torneo con quell'id non esiste per modifica -> 404
        this.annoTorneoValidator.validate(torneo,b);
        if (b.hasErrors()) {
            model.addAttribute("squadreIscritte", torneo.getIscrizioni());
            model.addAttribute("squadreNonIscritte", this.squadraService.getSquadreNonIscritteAlTorneo(torneoId));
            model.addAttribute("squadra", new IscrizioneForm());
            model.addAttribute("nuovaSquadra", new IscrizioneForm());
            return "/admin/tornei/modifica_torneo";
        }
        this.torneoService.modify(torneo, torneoId);
        return "redirect:/tornei/"+torneoId;
    }

    @PostMapping("/admin/tornei/{id}/iscrivi")
    public String aggiungiIscrizioneAlTorneo(@PathVariable("id") Long torneoId, @Valid @ModelAttribute("nuovaSquadra") IscrizioneForm iscForm,
                                             BindingResult b, Model model){
        //torneo o try catch squadra con quell'id non esistono, torneo->404, squadra->reject inserire squadra valida
        if (b.hasErrors()) {
            Torneo torneo = this.torneoService.getTorneoById(torneoId);
            model.addAttribute("torneo", torneo);
            model.addAttribute("squadreIscritte", torneo.getIscrizioni());
            model.addAttribute("squadreNonIscritte", this.squadraService.getSquadreNonIscritteAlTorneo(torneoId));
            model.addAttribute("squadra", new IscrizioneForm());
            model.addAttribute("nuovaSquadra", new IscrizioneForm());
            return "/admin/tornei/modifica_torneo";
        }
        this.squadraIscrittaService.addIscrizioneAlTorneo(torneoId, iscForm.getSquadraId());
        return "redirect:/tornei/"+torneoId;
    }

    @PostMapping("/admin/tornei/{id}/disiscrivi")
    public String rimuoviIscrizioneDalTorneo(@PathVariable("id") Long torneoId, @Valid @ModelAttribute("squadra") IscrizioneForm iscForm,
                                             BindingResult b, Model model){
        //torneo o try catch squadra con quell'id non esistono, torneo->404, squadra->reject inserire squadra valida
        if (b.hasErrors()) {
            Torneo torneo = this.torneoService.getTorneoById(torneoId);
            model.addAttribute("torneo", torneo);
            model.addAttribute("squadreIscritte", torneo.getIscrizioni());
            model.addAttribute("squadreNonIscritte", this.squadraService.getSquadreNonIscritteAlTorneo(torneoId));
            model.addAttribute("squadra", new IscrizioneForm());
            model.addAttribute("nuovaSquadra", new IscrizioneForm());
            return "/admin/tornei/modifica_torneo";
        }
        this.squadraIscrittaService.removeIscrizioneAlTorneo(torneoId, iscForm.getSquadraId());
        return "redirect:/tornei/"+torneoId;
    }

}
