package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.model.SquadraIscritta;
import it.progettosiw.siwcalcio.repository.SquadraIscrittaRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SquadraIscrittaService {

    private SquadraIscrittaRepository squadraIscrittaRepository;

    public SquadraIscrittaService(SquadraIscrittaRepository squadraIscrittaRepository) {
        this.squadraIscrittaRepository = squadraIscrittaRepository;
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
}
