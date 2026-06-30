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

    public GiocatoreService(GiocatoreRepository giocatoreRepository, SquadraRepository squadraRepository) {
        this.giocatoreRepository = giocatoreRepository;
        this.squadraRepository = squadraRepository;
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
        Optional<Giocatore> optGiocatore = this.giocatoreRepository.findById(giocatoreId);
        if(!optGiocatore.isPresent()){
            throw new RuntimeException("giocatore per modifica non trovato");
        }
        Giocatore g = optGiocatore.get();

        Optional<Squadra> optSquadra = this.squadraRepository.findById(gf.getSquadraId());
        if(!optSquadra.isPresent()){
            throw new RuntimeException("squadra non trovata");
        }
        Squadra s = optSquadra.get();

        g.setNome(gf.getNome());
        g.setCognome(gf.getCognome());
        g.setNascita(gf.getNascita());
        g.setAltezza(gf.getAltezza());
        g.setRuolo(gf.getRuolo());
        g.setSquadra(s);
        this.giocatoreRepository.save(g);
    }
}
