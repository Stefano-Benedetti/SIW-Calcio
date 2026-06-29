package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.model.Giocatore;
import it.progettosiw.siwcalcio.repository.GiocatoreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GiocatoreService {

    private GiocatoreRepository giocatoreRepository;

    public GiocatoreService(GiocatoreRepository giocatoreRepository) {
        this.giocatoreRepository = giocatoreRepository;
    }

    public Giocatore getGiocatoreById(Long id){
        Optional<Giocatore> giocatoreOpt = this.giocatoreRepository.findById(id);
        if(!giocatoreOpt.isPresent()){
            throw new RuntimeException("giocatore non trovato");
        }
        return giocatoreOpt.get();
    }
}
