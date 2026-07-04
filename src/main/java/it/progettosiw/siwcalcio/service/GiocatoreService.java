package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.dto.GiocatoreForm;
import it.progettosiw.siwcalcio.model.Giocatore;
import it.progettosiw.siwcalcio.model.Squadra;
import it.progettosiw.siwcalcio.repository.GiocatoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class GiocatoreService {

    private GiocatoreRepository giocatoreRepository;

    private SquadraService squadraService;

    public GiocatoreService(GiocatoreRepository giocatoreRepository, SquadraService squadraService) {
        this.giocatoreRepository = giocatoreRepository;
        this.squadraService = squadraService;
    }

    @Transactional(readOnly = true)
    public Giocatore getGiocatoreById(Long id) {
        Optional<Giocatore> giocatoreOpt = this.giocatoreRepository.findById(id);
        if (giocatoreOpt.isEmpty()) {
            throw new RuntimeException("giocatore non trovato");
        }
        return giocatoreOpt.get();
    }

    @Transactional
    public void save(Giocatore g){
        this.giocatoreRepository.save(g);
    }

    @Transactional
    public void modify(GiocatoreForm gf, Long giocatoreId){

        Giocatore g = this.getGiocatoreById(giocatoreId);

        Squadra s = this.squadraService.getSquadraById((gf.getSquadraId()));

        g.setNome(gf.getNome());
        g.setCognome(gf.getCognome());
        g.setNascita(gf.getNascita());
        g.setAltezza(gf.getAltezza());
        g.setRuolo(gf.getRuolo());
        g.setSquadra(s);
        this.giocatoreRepository.save(g);
    }
}
