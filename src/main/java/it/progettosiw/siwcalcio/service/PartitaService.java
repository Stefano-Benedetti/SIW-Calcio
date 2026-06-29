package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.model.Partita;
import it.progettosiw.siwcalcio.repository.PartitaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PartitaService {

    private PartitaRepository partitaRepository;

    public PartitaService(PartitaRepository partitaRepository){
        this.partitaRepository = partitaRepository;
    }

    public List<Partita> getPartiteDiOggi(Long torneo_id){
        return (List<Partita>) this.partitaRepository.findPartiteDiOggiScheduledByTorneo(torneo_id, LocalDateTime.now(), LocalDate.now().plusDays(1).atStartOfDay());
    }

}
