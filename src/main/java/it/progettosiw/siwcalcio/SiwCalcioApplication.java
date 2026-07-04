package it.progettosiw.siwcalcio;

import it.progettosiw.siwcalcio.model.Commento;
import it.progettosiw.siwcalcio.model.Partita;
import it.progettosiw.siwcalcio.model.SquadraIscritta;
import it.progettosiw.siwcalcio.model.Torneo;
import it.progettosiw.siwcalcio.repository.PartitaRepository;
import it.progettosiw.siwcalcio.repository.TorneoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.List;

@SpringBootApplication
public class SiwCalcioApplication implements CommandLineRunner {

    private PartitaRepository partitaRepository;

    private TorneoRepository torneoRepository;

    public SiwCalcioApplication(PartitaRepository partitaRepository, TorneoRepository torneoRepository) {
        this.partitaRepository = partitaRepository;
        this.torneoRepository = torneoRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SiwCalcioApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        testPartitaSenzaCommenti();
        testPartitaConCommenti();

        testTorneoSenzaIscrizioni();
        testTorneoConIscrizioni();
    }

    private void testPartitaSenzaCommenti() {
        StopWatch watch = new StopWatch();
        watch.start("testPartitaSenzaCommenti");
        Partita p = partitaRepository.findById(1L).get();
        List<Commento> commenti = p.getCommenti();
        System.out.println(commenti.toString());
        watch.stop();
        System.out.println(watch.prettyPrint());
    }

    private void testPartitaConCommenti() {
        StopWatch watch = new StopWatch();
        watch.start("testPartitaConCommenti");
        Partita p = partitaRepository.findWithCommentiById(1L).get();
        List<Commento> commenti = p.getCommenti();
        System.out.println(commenti.toString());
        watch.stop();
        System.out.println(watch.prettyPrint());
    }

    private void testTorneoSenzaIscrizioni(){
        StopWatch watch = new StopWatch();
        watch.start("testTorneoSenzaIscrizioni");
        Torneo t = torneoRepository.findById(1L).get();
        List<SquadraIscritta> iscrizioni = t.getIscrizioni();
        System.out.println(iscrizioni.toString());
        watch.stop();
        System.out.println(watch.prettyPrint());
    }

    private void testTorneoConIscrizioni(){
        StopWatch watch = new StopWatch();
        watch.start("testTorneoConIscrizioni");
        Torneo t = torneoRepository.findWithIscrizioniById(1L).get();
        List<SquadraIscritta> iscrizioni = t.getIscrizioni();
        System.out.println(iscrizioni.toString());
        watch.stop();
        System.out.println(watch.prettyPrint());
    }

}
