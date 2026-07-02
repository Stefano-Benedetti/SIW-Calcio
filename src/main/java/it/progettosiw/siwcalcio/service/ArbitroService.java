package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.model.Arbitro;
import it.progettosiw.siwcalcio.model.Giocatore;
import it.progettosiw.siwcalcio.repository.ArbitroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArbitroService {

    private ArbitroRepository arbitroRepository;

    public ArbitroService(ArbitroRepository arbitroRepository){
        this.arbitroRepository = arbitroRepository;
    }

    public Arbitro getArbitroById(Long id){
        Optional<Arbitro> arbitroOpt = this.arbitroRepository.findById(id);
        if (arbitroOpt.isEmpty()) {
            throw new RuntimeException("arbitro non trovato");
        }
        return arbitroOpt.get();
    }

    public List<Arbitro> getAllArbitri(){
        return (List<Arbitro>) this.arbitroRepository.findAll();
    }

}
