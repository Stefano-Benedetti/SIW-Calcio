package it.progettosiw.siwcalcio.controller.rest;

import it.progettosiw.siwcalcio.dto.TorneoRestDto;
import it.progettosiw.siwcalcio.model.Torneo;
import it.progettosiw.siwcalcio.service.TorneoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tornei")
public class TorneoRestController {

    private final TorneoService torneoService;

    public TorneoRestController(TorneoService torneoService) {
        this.torneoService = torneoService;
    }

    @GetMapping
    public List<TorneoRestDto> getTornei() {

        List<TorneoRestDto> risposta = new ArrayList<TorneoRestDto>();

        for(Torneo t : this.torneoService.getAllTorneiOrdinatiPerAnno()){
            risposta.add(new TorneoRestDto(t.getId(), t.getNome(), t.getAnno().getValue(), t.getDescrizione()));
        }

        return risposta;
    }
}