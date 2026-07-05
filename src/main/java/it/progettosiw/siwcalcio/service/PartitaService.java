package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.dto.PartitaForm;
import it.progettosiw.siwcalcio.exceptions.*;
import it.progettosiw.siwcalcio.model.*;
import it.progettosiw.siwcalcio.repository.ArbitroRepository;
import it.progettosiw.siwcalcio.repository.PartitaRepository;
import it.progettosiw.siwcalcio.repository.SquadraIscrittaRepository;
import it.progettosiw.siwcalcio.repository.SquadraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PartitaService {

    private PartitaRepository partitaRepository;

    private SquadraRepository squadraRepository;

    private TorneoService torneoService;

    private ArbitroRepository arbitroRepository;

    private SquadraIscrittaRepository squadraIscrittaRepository;

    public PartitaService(PartitaRepository partitaRepository, SquadraRepository squadraRepository,
                          TorneoService torneoService, ArbitroRepository arbitroRepository, SquadraIscrittaRepository squadraIscrittaRepository){
        this.partitaRepository = partitaRepository;
        this.squadraRepository = squadraRepository;
        this.torneoService = torneoService;
        this.arbitroRepository = arbitroRepository;
        this.squadraIscrittaRepository = squadraIscrittaRepository;
    }

    @Transactional(readOnly = true)
    public List<Partita> getPartiteDiOggiPerTorneo(Long torneoId){
        return this.partitaRepository.findPartiteByGiornoAndByTorneo(torneoId, LocalDateTime.now().toLocalDate().atStartOfDay(), LocalDate.now().plusDays(1).atStartOfDay());
    }

    @Transactional(readOnly = true)
    public Partita getPartitaById(Long id){
        Optional<Partita> partitaOpt = this.partitaRepository.findById(id);
        if(partitaOpt.isEmpty()) throw new PartitaNonTrovataException("partita non trovata");
        return partitaOpt.get();
    }

    @Transactional(readOnly = true)
    public Partita getPartitaByIdWithCommenti(Long id){
        Optional<Partita> partitaOpt = this.partitaRepository.findWithCommentiById(id);
        if(partitaOpt.isEmpty()) throw new PartitaNonTrovataException("partita non trovata");
        return partitaOpt.get();
    }

    @Transactional(readOnly = true)
    public List<Partita> getCalendarioPerTorneo(Long torneoId){
        return this.partitaRepository.findPartitasByTorneoIdAndStatoOrderByDataAsc(torneoId, StatoPartita.SCHEDULED);
    }

    @Transactional(readOnly = true)
    public List<Partita> getPartiteTerminatePerTorneo(Long torneoId){
        return this.partitaRepository.findPartitasByTorneoIdAndStatoOrderByDataDesc(torneoId, StatoPartita.PLAYED);
    }

    @Transactional
    public void creaPartita(PartitaForm pf, Long torneoId){
        if(pf.getSquadraHomeId().equals(pf.getSquadraAwayId()))
            throw new SquadraControSeStessaException("una squadra non può giocare contro se stessa");

        Long squadraHomeId = pf.getSquadraHomeId();
        Long squadraAwayId = pf.getSquadraAwayId();

        if(!this.squadraIscrittaRepository.existsSquadraIscrittaBySquadraIdAndTorneoId(squadraHomeId,torneoId)
            || !this.squadraIscrittaRepository.existsSquadraIscrittaBySquadraIdAndTorneoId(squadraAwayId,torneoId))
            throw new PartitaConSquadraNonIscrittaException("una delle due squadre non fa parte del torneo");

        Arbitro arbitro = this.getArbitroById(pf.getArbitroId());
        Squadra squadraHome = this.getSquadraById(squadraHomeId);
        Squadra squadraAway = this.getSquadraById(squadraAwayId);
        Torneo torneo = this.torneoService.getTorneoById(torneoId);

        Partita partita = new Partita(pf, arbitro, squadraHome, squadraAway, torneo);

        this.partitaRepository.save(partita);
    }

    private Arbitro getArbitroById(Long id){
        Optional<Arbitro> arbitroOpt = this.arbitroRepository.findById(id);
        if(arbitroOpt.isEmpty()) throw new ArbitroNonEsisteException("l'arbitro selezionato non esiste");
        return arbitroOpt.get();
    }

    private Squadra getSquadraById(Long id){
        Optional<Squadra> squadraOpt = this.squadraRepository.findById(id);
        if(squadraOpt.isEmpty()) throw new SquadraNonEsisteException("una delle due squadre selezionate non esiste");
        return squadraOpt.get();
    }

    @Transactional
    public void inserisciRisultato(int goalsHome, int goalsAway, Long partitaId) {
        Partita partita = this.getPartitaById(partitaId);
        Long torneoId = partita.getTorneo().getId();

        Long oldWinnerId = null;
        if (StatoPartita.PLAYED.equals(partita.getStato())) {
            oldWinnerId = getWinnerId(partita.getGoalsHome(), partita.getGoalsAway(), partita);
        }

        Long newWinnerId = this.getWinnerId(goalsHome, goalsAway, partita);

        if (!Objects.equals(oldWinnerId, newWinnerId)) {
            if (oldWinnerId != null) {
                this.removeVittoriaDallaSquadraIscrittaAlTorneo(oldWinnerId, torneoId);
            }
            if (newWinnerId != null) {
                this.addVittoriaAllaSquadraIscrittaAlTorneo(newWinnerId, torneoId);
            }
        }

        partita.setGoalsHome(goalsHome);
        partita.setGoalsAway(goalsAway);
        partita.setStato(StatoPartita.PLAYED);

        this.partitaRepository.save(partita);
    }

    private Long getWinnerId(int goalsHome, int goalsAway, Partita partita) {
        if (goalsHome > goalsAway) return partita.getSquadraHome().getId();
        if (goalsAway > goalsHome) return partita.getSquadraAway().getId();
        return null;
    }

    @Transactional
    public void deletePartita(Long partitaId){
        Partita p = this.getPartitaById(partitaId);
        Long torneoId = p.getTorneo().getId();
        togliPunteggioDiPartitaDalTorneo(p,torneoId);

        this.partitaRepository.delete(p);
    }

    private void togliPunteggioDiPartitaDalTorneo(Partita p, Long torneoId){
        if(p.getStato().equals(StatoPartita.PLAYED) && p.getGoalsHome() != p.getGoalsAway()) {
            Long squadraVincitriceId;
            if (p.getGoalsHome() > p.getGoalsAway())
                squadraVincitriceId = p.getSquadraHome().getId();
            else
                squadraVincitriceId = p.getSquadraAway().getId();

            this.removeVittoriaDallaSquadraIscrittaAlTorneo(squadraVincitriceId, torneoId);
        }
    }

    @Transactional
    public void rimuoviPartiteDiUnaSquadraETogliPunteggi(Long torneoId, Long squadraId){
        List<Partita> partiteDaEliminare =
                this.partitaRepository.findPartitasByTorneoIdAndSquadraHomeIdOrTorneoIdAndSquadraAwayId(torneoId, squadraId, torneoId, squadraId);

        for(Partita p : partiteDaEliminare){
            togliPunteggioDiPartitaDalTorneo(p,torneoId);
            this.partitaRepository.delete(p);
        }
    }

    private void addVittoriaAllaSquadraIscrittaAlTorneo(Long squadraId, Long torneoId){
        SquadraIscritta si;
        si = this.squadraIscrittaRepository.findSquadraIscrittaBySquadraIdAndTorneoId(squadraId, torneoId);
        si.addVittoria();
        this.squadraIscrittaRepository.save(si);
    }

    private void removeVittoriaDallaSquadraIscrittaAlTorneo(Long squadraId, Long torneoId){
        SquadraIscritta si;
        si = this.squadraIscrittaRepository.findSquadraIscrittaBySquadraIdAndTorneoId(squadraId, torneoId);
        si.removeVittoria();
        this.squadraIscrittaRepository.save(si);
    }

}
