package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.model.Partita;
import it.progettosiw.siwcalcio.model.StatoPartita;
import it.progettosiw.siwcalcio.repository.PartitaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public List<Partita> getPartiteDiOggiPerTorneo(Long torneo_id){
        return this.partitaRepository.findPartiteByDataByTorneo(torneo_id, LocalDateTime.now().toLocalDate().atStartOfDay(), LocalDate.now().plusDays(1).atStartOfDay());
    }

    public Partita getPartitaById(Long id){
        Optional<Partita> partitaOpt = this.partitaRepository.findById(id);
        if(!partitaOpt.isPresent()){
            throw new RuntimeException("partita non trovata");
        }
        return partitaOpt.get();
    }

    public List<Partita> getCalendarioPerTorneo(Long id){
        Sort sort = Sort.by(Sort.Direction.ASC, "data");
        return this.partitaRepository.findPartitasByStatoAndTorneoId(StatoPartita.SCHEDULED, id, sort);
    }

    public List<Partita> getPartiteTerminatePerTorneo(Long id){
        Sort sort = Sort.by(Sort.Direction.DESC, "data");
        return this.partitaRepository.findPartitasByStatoAndTorneoId(StatoPartita.PLAYED, id, sort);
    }

}
