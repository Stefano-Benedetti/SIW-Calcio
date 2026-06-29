package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.model.Squadra;
import it.progettosiw.siwcalcio.repository.SquadraRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SquadraService {

    private SquadraRepository squadraRepository;

    public SquadraService(SquadraRepository squadraRepository) {
        this.squadraRepository = squadraRepository;
    }

    public Squadra getSquadraById(Long id){
        Optional<Squadra> squadraOpt = this.squadraRepository.findById(id);
        if(!squadraOpt.isPresent()){
            throw new RuntimeException("squadra non trovata");
        }
        return squadraOpt.get();
    }
}
