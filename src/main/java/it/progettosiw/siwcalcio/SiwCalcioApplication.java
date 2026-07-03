package it.progettosiw.siwcalcio;

import it.progettosiw.siwcalcio.model.Commento;
import it.progettosiw.siwcalcio.model.Partita;
import it.progettosiw.siwcalcio.repository.PartitaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.List;

@SpringBootApplication
public class SiwCalcioApplication implements CommandLineRunner {

    private PartitaRepository partitaRepository;

    public SiwCalcioApplication(PartitaRepository partitaRepository) {
        this.partitaRepository = partitaRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SiwCalcioApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        testPartitaSenzaCommenti();
        testPartitaConCommenti();
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

    private void testPartitaSenzaCommenti() {
        StopWatch watch = new StopWatch();
        watch.start("testPartitaSenzaCommenti");
        Partita p = partitaRepository.findById(1L).get();
        List<Commento> commenti = p.getCommenti();
        System.out.println(commenti.toString());
        watch.stop();
        System.out.println(watch.prettyPrint());
    }


}
