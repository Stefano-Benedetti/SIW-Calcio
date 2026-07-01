package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.model.Partita;
import it.progettosiw.siwcalcio.model.StatoPartita;
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

    public PartitaService(PartitaRepository partitaRepository){
        this.partitaRepository = partitaRepository;
    }

    public List<Partita> getPartiteDiOggiPerTorneo(Long torneoId){
        return this.partitaRepository.findPartiteByGiornoAndByTorneo(torneoId, LocalDateTime.now().toLocalDate().atStartOfDay(), LocalDate.now().plusDays(1).atStartOfDay());
    }

    public Partita getPartitaById(Long id){
        Optional<Partita> partitaOpt = this.partitaRepository.findById(id);
        if(!partitaOpt.isPresent()){
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

}
