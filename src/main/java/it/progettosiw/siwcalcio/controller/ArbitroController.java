package it.progettosiw.siwcalcio.controller;

import it.progettosiw.siwcalcio.model.Arbitro;
import it.progettosiw.siwcalcio.service.ArbitroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArbitroController {

    private ArbitroService arbitroService;

    public ArbitroController(ArbitroService arbitroService) {
        this.arbitroService = arbitroService;
    }

    @GetMapping("/admin/arbitri/crea")
    public String getFormCreazioneArbitro(Model model){
        model.addAttribute("arbitro", new Arbitro());
        return "/admin/arbitri/crea_arbitro.html";
    }

    @PostMapping("/admin/arbitri/crea")
    public String creaArbitro(@ModelAttribute Arbitro a, Model model){
        this.arbitroService.save(a);
        return "redirect:/";
    }
}
