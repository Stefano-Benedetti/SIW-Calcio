package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.model.Commento;
import it.progettosiw.siwcalcio.model.Partita;
import it.progettosiw.siwcalcio.model.Utente;
import it.progettosiw.siwcalcio.repository.CommentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CommentoService {

    private CommentoRepository commentoRepository;

    private PartitaService partitaService;

    private UtenteService utenteService;

    public CommentoService(CommentoRepository commentoRepository, PartitaService partitaService, UtenteService utenteService) {
        this.commentoRepository = commentoRepository;
        this.partitaService = partitaService;
        this.utenteService = utenteService;
    }

    @Transactional
    public void aggiungiCommentoAPartita(String testo, Long partitaId){
        Partita p = this.partitaService.getPartitaById(partitaId);
        Utente u = this.utenteService.getCurrentUser();
        Commento c = new Commento();
        c.setTesto(testo);
        c.setUtente(u);
        p.getCommenti().add(c);
        this.commentoRepository.save(c);
    }

    @Transactional
    public void modificaCommento(String testo, Long commentoId){
        Commento c = this.getCommentoById(commentoId);
        String username = this.utenteService.getCurrentUserDetails().getUsername();
        if(!c.getUtente().getUsername().equals(username))
            throw new RuntimeException("non puoi modificare i commenti di un altro");
        c.setTesto(testo);
        this.commentoRepository.save(c);
    }

    private Commento getCommentoById(Long id){
        Optional<Commento> commentoOpt = this.commentoRepository.findById(id);
        if(commentoOpt.isEmpty()){
            throw new RuntimeException("commento non trovato");
        }
        return commentoOpt.get();
    }
}
