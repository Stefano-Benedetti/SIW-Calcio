package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.model.Giocatore;
import it.progettosiw.siwcalcio.model.Squadra;
import it.progettosiw.siwcalcio.repository.SquadraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public List<Squadra> getAllSquadre(){
        return (List<Squadra>)this.squadraRepository.findAll();
    }

    public void save(Squadra s){
        this.squadraRepository.save(s);
    }

    public void modify(Squadra temp, Long id){
        Squadra squadra = getSquadraById(id);
        squadra.setNome(temp.getNome());
        squadra.setFondazione(temp.getFondazione());
        squadra.setCitta(temp.getCitta());
        this.squadraRepository.save(squadra);
    }

    @Transactional
    public void delete(Long id){
        this.squadraRepository.deleteById(id);
    }

    public List<Squadra> getSquadreNonIscritteAlTorneo(Long torneoId){
        return this.squadraRepository.findSquadreNonIscritteAlTorneo(torneoId);
    }
}
