package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.exceptions.SquadraDaDisiscrivereNonIscrittaException;
import it.progettosiw.siwcalcio.exceptions.SquadraNonEsisteException;
import it.progettosiw.siwcalcio.model.*;
import it.progettosiw.siwcalcio.repository.SquadraIscrittaRepository;
import it.progettosiw.siwcalcio.repository.SquadraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SquadraIscrittaService {

    private SquadraIscrittaRepository squadraIscrittaRepository;

    private TorneoService torneoService;

    private SquadraRepository squadraRepository;

    private PartitaService partitaService;

    public SquadraIscrittaService(SquadraIscrittaRepository squadraIscrittaRepository, TorneoService torneoService,
                                  SquadraRepository squadraRepository, PartitaService partitaService) {
        this.squadraIscrittaRepository = squadraIscrittaRepository;
        this.torneoService = torneoService;
        this.squadraRepository = squadraRepository;
        this.partitaService = partitaService;
    }

    @Transactional(readOnly = true)
    public List<SquadraIscritta> getClassificaPerTorneo(Long id){
        return this.squadraIscrittaRepository.findSquadraIscrittasByTorneoIdOrderByVittorieDesc(id);
    }

    public List<Integer> getPosizioniClassifica(List<SquadraIscritta> classifica){
        List<Integer> posizioni = new ArrayList<>();

        if (classifica == null || classifica.isEmpty())
            return posizioni;

        Integer vittoriePrecedenti = null;
        int posizioneCorrente = 0;

        for (SquadraIscritta s : classifica) {
            int vittorieCorrenti = s.getVittorie();
            if (vittoriePrecedenti == null || vittorieCorrenti != vittoriePrecedenti)
                posizioneCorrente++;
            posizioni.add(posizioneCorrente);
            vittoriePrecedenti = vittorieCorrenti;
        }
        
        return posizioni;
    }

    @Transactional
    public void addIscrizioneAlTorneo(Long torneoId, Long squadraId){
        Torneo torneo = this.torneoService.getTorneoById(torneoId);
        Squadra squadra = this.getSquadraById(squadraId);
        SquadraIscritta squadraIscritta = new SquadraIscritta(torneo, squadra);
        this.squadraIscrittaRepository.save(squadraIscritta);
    }

    private Squadra getSquadraById(Long id){
        Optional<Squadra> squadraOpt = this.squadraRepository.findById(id);
        if(squadraOpt.isEmpty()){
            throw new SquadraNonEsisteException("la squadra selezionata non esiste");
        }
        return squadraOpt.get();
    }

    @Transactional
    public void removeIscrizioneAlTorneo(Long torneoId, Long squadraId){
        if (!this.squadraIscrittaRepository.existsSquadraIscrittaBySquadraIdAndTorneoId(squadraId,torneoId))
            throw new SquadraDaDisiscrivereNonIscrittaException("la squadra selezionata non è iscritta al torneo");
        this.partitaService.rimuoviPartiteDiUnaSquadraETogliPunteggi(torneoId,squadraId);
        this.squadraIscrittaRepository.deleteByTorneoIdAndSquadraId(torneoId,squadraId);
    }
}
