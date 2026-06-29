package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.model.Torneo;
import it.progettosiw.siwcalcio.repository.TorneoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TorneoService {

    private TorneoRepository torneoRepository;

    public TorneoService(TorneoRepository torneoRepository){
        this.torneoRepository = torneoRepository;
    }

    public List<Torneo> getAllTornei(){
        return (List<Torneo>) this.torneoRepository.findAll();
    }

    public Torneo getTorneoById(Long id){
        Optional<Torneo> torneoOpt = this.torneoRepository.findById(id);
        if(!torneoOpt.isPresent()){
            throw new RuntimeException("torneo non trovato");
        }
        return torneoOpt.get();
    }
}
