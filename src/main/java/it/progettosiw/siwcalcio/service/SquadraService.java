package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.exceptions.SquadraNonTrovataException;
import it.progettosiw.siwcalcio.model.Squadra;
import it.progettosiw.siwcalcio.model.SquadraIscritta;
import it.progettosiw.siwcalcio.repository.SquadraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SquadraService {

    private final SquadraRepository squadraRepository;
    private final SquadraIscrittaService squadraIscrittaService;

    public SquadraService(SquadraRepository squadraRepository, SquadraIscrittaService squadraIscrittaService) {
        this.squadraRepository = squadraRepository;
        this.squadraIscrittaService = squadraIscrittaService;
    }

    @Transactional(readOnly = true)
    public Squadra getSquadraById(Long id){
        Optional<Squadra> squadraOpt = this.squadraRepository.findById(id);
        if(squadraOpt.isEmpty()){
            throw new SquadraNonTrovataException("squadra non trovata");
        }
        return squadraOpt.get();
    }

    @Transactional(readOnly = true)
    public List<Squadra> getAllSquadre(){
        return (List<Squadra>)this.squadraRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Squadra> getAllSquadreOrdinatePerNome(){
        return (List<Squadra>)this.squadraRepository.findAllByOrderByNomeAsc();
    }

    @Transactional(readOnly = true)
    public List<Squadra> getSquadreNonIscritteAlTorneo(Long torneoId){
        return this.squadraRepository.findSquadreNonIscritteAlTorneo(torneoId);
    }

    @Transactional
    public void save(Squadra s){
        this.squadraRepository.save(s);
    }

    @Transactional
    public void modify(Squadra temp, Long id){
        Squadra squadra = this.getSquadraById(id);
        squadra.setNome(temp.getNome());
        squadra.setFondazione(temp.getFondazione());
        squadra.setCitta(temp.getCitta());
        this.squadraRepository.save(squadra);
    }

    @Transactional
    public void deleteSquadra(Long id){
        Squadra s = this.getSquadraById(id);
        for(SquadraIscritta si : s.getIscrizioni()){
            this.squadraIscrittaService.removeIscrizioneAlTorneo(si.getTorneo().getId(), si.getSquadra().getId());
        }
        this.squadraRepository.delete(s);
    }
}
