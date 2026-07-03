package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.dto.PartitaForm;
import it.progettosiw.siwcalcio.model.*;
import it.progettosiw.siwcalcio.repository.PartitaRepository;
import it.progettosiw.siwcalcio.repository.SquadraIscrittaRepository;
import it.progettosiw.siwcalcio.repository.SquadraRepository;
import org.springframework.data.domain.Sort;
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

    private ArbitroService arbitroService;

    private SquadraIscrittaRepository squadraIscrittaRepository;

    public PartitaService(PartitaRepository partitaRepository, SquadraRepository squadraRepository,
                          TorneoService torneoService, ArbitroService arbitroService, SquadraIscrittaRepository squadraIscrittaRepository){
        this.partitaRepository = partitaRepository;
        this.squadraRepository = squadraRepository;
        this.torneoService = torneoService;
        this.arbitroService = arbitroService;
        this.squadraIscrittaRepository = squadraIscrittaRepository;
    }

    public List<Partita> getPartiteDiOggiPerTorneo(Long torneoId){
        return this.partitaRepository.findPartiteByGiornoAndByTorneo(torneoId, LocalDateTime.now().toLocalDate().atStartOfDay(), LocalDate.now().plusDays(1).atStartOfDay());
    }

    public Partita getPartitaById(Long id){
        Optional<Partita> partitaOpt = this.partitaRepository.findById(id);
        if(partitaOpt.isEmpty()){
            throw new RuntimeException("partita non trovata");
        }
        return partitaOpt.get();
    }

    public Partita getPartitaByIdWithCommenti(Long id){
        Optional<Partita> partitaOpt = this.partitaRepository.findByIdWithCommenti(id);
        if(partitaOpt.isEmpty()){
            throw new RuntimeException("partita non trovata");
        }
        return partitaOpt.get();
    }

    public List<Partita> getCalendarioPerTorneo(Long torneoId){
        return this.partitaRepository.findPartitasByTorneoIdAndStatoOrderByDataAsc(torneoId, StatoPartita.SCHEDULED);
    }

    public List<Partita> getPartiteTerminatePerTorneo(Long torneoId){
        return this.partitaRepository.findPartitasByTorneoIdAndStatoOrderByDataDesc(torneoId, StatoPartita.PLAYED);
    }

    @Transactional
    public void removePartitaDiTorneoConSquadra(Long torneoId, Long squadraId){
        this.partitaRepository.deleteAllByTorneoIdAndSquadraHomeIdOrTorneoIdAndSquadraAwayId(torneoId, squadraId, torneoId, squadraId);
    }

    public void creaPartita(PartitaForm pf, Long torneoId){
        if(this.partitaRepository.existsBySquadraAwayIdAndSquadraHomeIdAndData(pf.getSquadraHomeId(), pf.getSquadraAwayId(), pf.getData())
                || this.partitaRepository.existsBySquadraAwayIdAndSquadraHomeIdAndData(pf.getSquadraAwayId(), pf.getSquadraHomeId(), pf.getData()))
            throw new RuntimeException("questa partita con queste squadre e in questa data già esiste");

        Arbitro arbitro = this.arbitroService.getArbitroById(pf.getArbitroId());
        Squadra squadraHome = this.getSquadraById(pf.getSquadraHomeId());
        Squadra squadraAway = this.getSquadraById(pf.getSquadraAwayId());
        Torneo torneo = this.torneoService.getTorneoById(torneoId);

        Partita partita = new Partita(pf, arbitro, squadraHome, squadraAway, torneo);

        this.partitaRepository.save(partita);
    }

    private Squadra getSquadraById(Long id){
        Optional<Squadra> squadraOpt = this.squadraRepository.findById(id);
        if(squadraOpt.isEmpty()){
            throw new RuntimeException("squadra non trovata");
        }
        return squadraOpt.get();
    }

    public void inserisciRisultato(int goalsHome, int goalsAway, Long partitaId) {
        Partita partita = this.getPartitaById(partitaId);
        Long torneoId = partita.getTorneo().getId();

        Long oldWinnerId = null;
        if (StatoPartita.PLAYED.equals(partita.getStato())) {
            oldWinnerId = getWinnerId(partita.getGoalsHome(), partita.getGoalsAway(), partita);
        }

        Long newWinnerId = getWinnerId(goalsHome, goalsAway, partita);

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
    public void delete(Long partitaId){
        Partita partita = this.getPartitaById(partitaId);

        if(partita.getStato().equals(StatoPartita.PLAYED)) {
            Long squadraId;
            Long torneoId = partita.getTorneo().getId();
            if (partita.getGoalsHome() > partita.getGoalsAway())
                squadraId = partita.getSquadraHome().getId();
            else
                squadraId = partita.getSquadraAway().getId();

            this.removeVittoriaDallaSquadraIscrittaAlTorneo(squadraId, torneoId);
        }

        this.partitaRepository.deleteById(partitaId);
    }

    public void rimuoviPartiteDiUnaSquadraETogliPunteggi(Long torneoId, Long squadraId){
        List<Partita> partiteDaEliminare =
                this.partitaRepository.findPartitasByTorneoIdAndSquadraHomeIdOrTorneoIdAndSquadraAwayId(torneoId, squadraId, torneoId, squadraId);

        for(Partita p : partiteDaEliminare){
            if(p.getStato().equals(StatoPartita.PLAYED)) {
                Long squadraVincitriceId;
                if (p.getGoalsHome() > p.getGoalsAway())
                    squadraVincitriceId = p.getSquadraHome().getId();
                else
                    squadraVincitriceId = p.getSquadraAway().getId();
                this.removeVittoriaDallaSquadraIscrittaAlTorneo(squadraVincitriceId, torneoId);
            }
            this.partitaRepository.delete(p);
        }
    }

    public void addVittoriaAllaSquadraIscrittaAlTorneo(Long squadraId, Long torneoId){
        SquadraIscritta si;
        si = this.squadraIscrittaRepository.findSquadraIscrittaBySquadraIdAndTorneoId(squadraId, torneoId);
        si.addVittoria();
        this.squadraIscrittaRepository.save(si);
    }

    public void removeVittoriaDallaSquadraIscrittaAlTorneo(Long squadraId, Long torneoId){
        SquadraIscritta si;
        si = this.squadraIscrittaRepository.findSquadraIscrittaBySquadraIdAndTorneoId(squadraId, torneoId);
        si.removeVittoria();
        this.squadraIscrittaRepository.save(si);
    }

}
