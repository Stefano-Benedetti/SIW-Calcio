package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.dto.GiocatoreForm;
import it.progettosiw.siwcalcio.model.Giocatore;
import it.progettosiw.siwcalcio.model.Squadra;
import it.progettosiw.siwcalcio.repository.GiocatoreRepository;
import it.progettosiw.siwcalcio.repository.SquadraRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GiocatoreService {

    private GiocatoreRepository giocatoreRepository;

    private SquadraRepository squadraRepository;

    private SquadraService squadraService;

    public GiocatoreService(GiocatoreRepository giocatoreRepository, SquadraRepository squadraRepository, SquadraService squadraService) {
        this.giocatoreRepository = giocatoreRepository;
        this.squadraRepository = squadraRepository;
        this.squadraService = squadraService;
    }

    public Giocatore getGiocatoreById(Long id) {
        Optional<Giocatore> giocatoreOpt = this.giocatoreRepository.findById(id);
        if (!giocatoreOpt.isPresent()) {
            throw new RuntimeException("giocatore non trovato");
        }
        return giocatoreOpt.get();
    }

    public void save(Giocatore g){
        this.giocatoreRepository.save(g);
    }

    public void modify(GiocatoreForm gf, Long giocatoreId){

        Giocatore g = getGiocatoreById(giocatoreId);

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
