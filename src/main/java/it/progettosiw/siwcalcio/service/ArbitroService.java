package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.exceptions.ArbitroNonTrovatoException;
import it.progettosiw.siwcalcio.model.Arbitro;
import it.progettosiw.siwcalcio.repository.ArbitroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArbitroService {

    private ArbitroRepository arbitroRepository;

    public ArbitroService(ArbitroRepository arbitroRepository){
        this.arbitroRepository = arbitroRepository;
    }

    @Transactional(readOnly = true)
    public Arbitro getArbitroById(Long id){
        Optional<Arbitro> arbitroOpt = this.arbitroRepository.findById(id);
        if (arbitroOpt.isEmpty()) {
            throw new ArbitroNonTrovatoException("arbitro non trovato");
        }
        return arbitroOpt.get();
    }

    @Transactional(readOnly = true)
    public List<Arbitro> getAllArbitri(){
        return (List<Arbitro>) this.arbitroRepository.findAll();
    }

    @Transactional
    public void save(Arbitro a){
        this.arbitroRepository.save(a);
    }

}
