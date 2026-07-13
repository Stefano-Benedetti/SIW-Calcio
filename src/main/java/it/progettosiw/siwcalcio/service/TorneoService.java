package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.exceptions.TorneoGiaEsisteException;
import it.progettosiw.siwcalcio.exceptions.TorneoNonTrovatoException;
import it.progettosiw.siwcalcio.model.Torneo;
import it.progettosiw.siwcalcio.repository.TorneoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TorneoService {

    private final TorneoRepository torneoRepository;

    public TorneoService(TorneoRepository torneoRepository){
        this.torneoRepository = torneoRepository;
    }

    @Transactional(readOnly = true)
    public List<Torneo> getAllTornei(){
        return (List<Torneo>) this.torneoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Torneo> getAllTorneiOrdinatiPerAnno(){
        return this.torneoRepository.findAllByOrderByAnnoDesc();
    }

    @Transactional(readOnly = true)
    public Torneo getTorneoById(Long id){
        Optional<Torneo> torneoOpt = this.torneoRepository.findById(id);
        if(torneoOpt.isEmpty()){
            throw new TorneoNonTrovatoException("torneo non trovato");
        }
        return torneoOpt.get();
    }

    @Transactional(readOnly = true)
    public Torneo getTorneoByIdWithIscrizioni(Long id){
        Optional<Torneo> torneoOpt = this.torneoRepository.findWithIscrizioniById(id);
        if(torneoOpt.isEmpty()){
            throw new TorneoNonTrovatoException("torneo non trovato");
        }
        return torneoOpt.get();
    }

    @Transactional
    public void save(Torneo torneo){
        if(this.torneoRepository.existsByNomeAndAnno(torneo.getNome(), torneo.getAnno()))
            throw new TorneoGiaEsisteException("esiste già un torneo con questo nome in questo anno");
        this.torneoRepository.save(torneo);
    }

    @Transactional
    public void modify(Torneo temp, Long torneoId){
        Torneo torneo = this.getTorneoById(torneoId);

        torneo.setNome(temp.getNome());
        torneo.setAnno(temp.getAnno());
        torneo.setDescrizione(temp.getDescrizione());

        this.torneoRepository.save(torneo);
    }
}
