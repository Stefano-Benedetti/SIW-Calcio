package it.progettosiw.siwcalcio.controller.rest;

import it.progettosiw.siwcalcio.dto.SquadraRestDto;
import it.progettosiw.siwcalcio.dto.TorneoRestDto;
import it.progettosiw.siwcalcio.model.Squadra;
import it.progettosiw.siwcalcio.model.Torneo;
import it.progettosiw.siwcalcio.service.SquadraService;
import it.progettosiw.siwcalcio.service.TorneoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/squadre")
public class SquadraRestController {

    private final SquadraService squadraService;

    public SquadraRestController(SquadraService squadraService) {
        this.squadraService = squadraService;
    }

    @GetMapping
    public List<SquadraRestDto> getTornei() {

        List<SquadraRestDto> risposta = new ArrayList<SquadraRestDto>();

        for(Squadra s : this.squadraService.getAllSquadreOrdinatePerNome()){
            risposta.add(new SquadraRestDto(s.getId(), s.getNome()));
        }

        return risposta;
    }
}
