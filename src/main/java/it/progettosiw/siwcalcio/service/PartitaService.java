package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.dto.PartitaForm;
import it.progettosiw.siwcalcio.model.*;
import it.progettosiw.siwcalcio.repository.PartitaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PartitaService {

    private PartitaRepository partitaRepository;

    private SquadraService squadraService;

    private TorneoService torneoService;

    private ArbitroService arbitroService;

    private SquadraIscrittaService squadraIscrittaService;

    public PartitaService(PartitaRepository partitaRepository, SquadraService squadraService,
                          TorneoService torneoService, ArbitroService arbitroService, SquadraIscrittaService squadraIscrittaService){
        this.partitaRepository = partitaRepository;
        this.squadraService = squadraService;
        this.torneoService = torneoService;
        this.arbitroService = arbitroService;
        this.squadraIscrittaService = squadraIscrittaService;
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
        Squadra squadraHome = this.squadraService.getSquadraById(pf.getSquadraHomeId());
        Squadra squadraAway = this.squadraService.getSquadraById(pf.getSquadraAwayId());
        Torneo torneo = this.torneoService.getTorneoById(torneoId);

        Partita partita = new Partita(pf, arbitro, squadraHome, squadraAway, torneo);

        this.partitaRepository.save(partita);
    }

    public void inserisciRisultato(int goalsHome, int goalsAway, Long partitaId){
        Partita partita = this.getPartitaById(partitaId);
        partita.setGoalsHome(goalsHome);
        partita.setGoalsAway(goalsAway);
        partita.setStato(StatoPartita.PLAYED);

        Long squadraId;
        Long torneoId = partita.getTorneo().getId();
        if(goalsHome > goalsAway)
            squadraId = partita.getSquadraHome().getId();
        else
            squadraId = partita.getSquadraAway().getId();

        this.squadraIscrittaService.addVittoriaAllaSquadraIscrittaAlTorneo(squadraId, torneoId);

        this.partitaRepository.save(partita);
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

            this.squadraIscrittaService.removeVittoriaDallaSquadraIscrittaAlTorneo(squadraId, torneoId);
        }

        this.partitaRepository.deleteById(partitaId);
    }

}
