package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.dto.GiocatoreForm;
import it.progettosiw.siwcalcio.exceptions.GiocatoreNonTrovatoException;
import it.progettosiw.siwcalcio.exceptions.SquadraNonEsisteException;
import it.progettosiw.siwcalcio.model.Giocatore;
import it.progettosiw.siwcalcio.model.Squadra;
import it.progettosiw.siwcalcio.repository.GiocatoreRepository;
import it.progettosiw.siwcalcio.repository.SquadraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class GiocatoreService {

    private final GiocatoreRepository giocatoreRepository;

    private final SquadraRepository squadraRepository;

    public GiocatoreService(GiocatoreRepository giocatoreRepository, SquadraRepository squadraRepository) {
        this.giocatoreRepository = giocatoreRepository;
        this.squadraRepository = squadraRepository;
    }

    @Transactional(readOnly = true)
    public Giocatore getGiocatoreById(Long id) {
        Optional<Giocatore> giocatoreOpt = this.giocatoreRepository.findById(id);
        if (giocatoreOpt.isEmpty()) {
            throw new GiocatoreNonTrovatoException("giocatore non trovato");
        }
        return giocatoreOpt.get();
    }

    @Transactional
    public Long creaESalva(GiocatoreForm gf){
        Squadra s = this.getSquadraById(gf.getSquadraId());
        Giocatore g = new Giocatore(gf,s);
        this.giocatoreRepository.save(g);
        return g.getId();
    }

    @Transactional
    public void modify(GiocatoreForm gf, Long giocatoreId){
        Giocatore g = this.getGiocatoreById(giocatoreId);
        Squadra s = this.getSquadraById(gf.getSquadraId());

        g.setNome(gf.getNome());
        g.setCognome(gf.getCognome());
        g.setNascita(gf.getNascita());
        g.setAltezza(gf.getAltezza());
        g.setRuolo(gf.getRuolo());
        g.setSquadra(s);
        this.giocatoreRepository.save(g);
    }

    private Squadra getSquadraById(Long id){
        Optional<Squadra> optSquadra = this.squadraRepository.findById(id);
        if(optSquadra.isEmpty()) throw new SquadraNonEsisteException("la squadra selezionata non esiste");
        return optSquadra.get();
    }
}
