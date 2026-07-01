package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.model.Squadra;
import it.progettosiw.siwcalcio.model.SquadraIscritta;
import it.progettosiw.siwcalcio.model.Torneo;
import it.progettosiw.siwcalcio.repository.SquadraIscrittaRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SquadraIscrittaService {

    private SquadraIscrittaRepository squadraIscrittaRepository;

    private TorneoService torneoService;

    private SquadraService squadraService;

    private PartitaService partitaService;

    public SquadraIscrittaService(SquadraIscrittaRepository squadraIscrittaRepository, TorneoService torneoService, SquadraService squadraService, PartitaService partitaService) {
        this.squadraIscrittaRepository = squadraIscrittaRepository;
        this.torneoService = torneoService;
        this.squadraService = squadraService;
        this.partitaService = partitaService;
    }

    public List<SquadraIscritta> getClassificaPerTorneo(Long id){
        return this.squadraIscrittaRepository.findSquadraIscrittasByTorneoIdOrderByVittorieDesc(id);
    }

    public List<Integer> getPosizioniClassifica(List<SquadraIscritta> classifica){
        List<Integer> posizioni = new ArrayList<>();
        if (classifica == null || classifica.isEmpty()) {
            return posizioni;
        }

        int vittoriePrecedenti = -1;
        int posizioneCorrente = 0;

        for (int i = 0; i < classifica.size(); i++) {
            SquadraIscritta s = classifica.get(i);
            int vittorieCorrenti = s.getVittorie();

            if (vittoriePrecedenti == -1 || vittorieCorrenti != vittoriePrecedenti) {
                posizioneCorrente = i + 1;
            }

            posizioni.add(posizioneCorrente);
            vittoriePrecedenti = vittorieCorrenti;
        }

        return posizioni;
    }

    public void addIscrizioneAlTorneo(Long torneoId, Long squadraId){
        Torneo torneo = this.torneoService.getTorneoById(torneoId);
        Squadra squadra = this.squadraService.getSquadraById(squadraId);
        SquadraIscritta squadraIscritta = new SquadraIscritta(torneo, squadra);
        this.squadraIscrittaRepository.save(squadraIscritta);
    }

    @Transactional
    public void removeIscrizioneAlTorneo(Long torneoId, Long squadraId){
        this.partitaService.removePartitaDiTorneoConSquadra(torneoId,squadraId);
        this.squadraIscrittaRepository.deleteByTorneoIdAndSquadraId(torneoId,squadraId);
    }
}
