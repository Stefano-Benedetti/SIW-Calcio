package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.model.*;
import it.progettosiw.siwcalcio.repository.PartitaRepository;
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

    private PartitaRepository partitaRepository;

    public SquadraIscrittaService(SquadraIscrittaRepository squadraIscrittaRepository, TorneoService torneoService,
                                  SquadraService squadraService, PartitaRepository partitaRepository) {
        this.squadraIscrittaRepository = squadraIscrittaRepository;
        this.torneoService = torneoService;
        this.squadraService = squadraService;
        this.partitaRepository = partitaRepository;
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
        this.rimuoviPartiteDiUnaSquadraETogliPunteggi(torneoId,squadraId);
        this.squadraIscrittaRepository.deleteByTorneoIdAndSquadraId(torneoId,squadraId);
    }

    private void rimuoviPartiteDiUnaSquadraETogliPunteggi(Long torneoId, Long squadraId){
        List<Partita> partiteDaEliminare =
                this.partitaRepository.findPartitasByTorneoIdAndSquadraHomeIdOrTorneoIdAndSquadraAwayId(torneoId, squadraId, torneoId, squadraId);

        for(Partita p : partiteDaEliminare){
            if(p.getStato().equals(StatoPartita.PLAYED)) {
                Long squadraVincitriceId;
                if (p.getGoalsHome() > p.getGoalsAway())
                    squadraVincitriceId = p.getSquadraHome().getId();
                else
                    squadraVincitriceId = p.getSquadraAway().getId();
                if(squadraVincitriceId.equals(squadraId))
                    this.removeVittoriaDallaSquadraIscrittaAlTorneo(squadraId, torneoId);
            }
        }
        this.partitaRepository.deleteAllByTorneoIdAndSquadraHomeIdOrTorneoIdAndSquadraAwayId(torneoId, squadraId, torneoId, squadraId);
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
