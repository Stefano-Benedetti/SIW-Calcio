package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.model.SquadraIscritta;
import it.progettosiw.siwcalcio.model.Torneo;
import it.progettosiw.siwcalcio.repository.TorneoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TorneoService {

    private TorneoRepository torneoRepository;

    public TorneoService(TorneoRepository torneoRepository){
        this.torneoRepository = torneoRepository;
    }

    @Transactional(readOnly = true)
    public List<Torneo> getAllTornei(){
        return (List<Torneo>) this.torneoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Torneo getTorneoById(Long id){
        Optional<Torneo> torneoOpt = this.torneoRepository.findById(id);
        if(torneoOpt.isEmpty()){
            throw new RuntimeException("torneo non trovato");
        }
        return torneoOpt.get();
    }

    @Transactional(readOnly = true)
    public Torneo getTorneoByIdWithIscrizioni(Long id){
        Optional<Torneo> torneoOpt = this.torneoRepository.findWithIscrizioniById(id);
        if(torneoOpt.isEmpty()){
            throw new RuntimeException("torneo non trovato");
        }
        return torneoOpt.get();
    }

    @Transactional
    public void save(Torneo torneo){
        if(this.torneoRepository.existsByNomeAndAnno(torneo.getNome(), torneo.getAnno()))
            throw new RuntimeException("questo torneo già esiste");
        this.torneoRepository.save(torneo);
    }

    @Transactional
    public void modify(Torneo temp, Long torneoId){
        Torneo torneo = getTorneoById(torneoId);

        torneo.setNome(temp.getNome());
        torneo.setAnno(temp.getAnno());
        torneo.setDescrizione(temp.getDescrizione());

        this.torneoRepository.save(torneo);
    }
}
