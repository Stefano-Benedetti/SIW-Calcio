package it.progettosiw.siwcalcio.controller;

import it.progettosiw.siwcalcio.model.Arbitro;
import it.progettosiw.siwcalcio.service.ArbitroService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArbitroController {

    private final ArbitroService arbitroService;

    public ArbitroController(ArbitroService arbitroService) {
        this.arbitroService = arbitroService;
    }

    @GetMapping("/admin/arbitri/crea")
    public String getFormCreazioneArbitro(Model model){
        model.addAttribute("arbitro", new Arbitro());
        return "/admin/arbitri/crea_arbitro";
    }

    @PostMapping("/admin/arbitri/crea")
    public String creaArbitro(@Valid @ModelAttribute("arbitro") Arbitro a, BindingResult b, Model model){
        if (b.hasErrors()) {
            return "/admin/arbitri/crea_arbitro";
        }
        this.arbitroService.save(a);
        return "redirect:/";
    }
}
