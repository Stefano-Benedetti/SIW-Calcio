package it.progettosiw.siwcalcio;

import it.progettosiw.siwcalcio.model.*;
import it.progettosiw.siwcalcio.repository.PartitaRepository;
import it.progettosiw.siwcalcio.repository.SquadraRepository;
import it.progettosiw.siwcalcio.repository.TorneoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.List;

@SpringBootApplication
public class SiwCalcioApplication implements CommandLineRunner {

    private final PartitaRepository partitaRepository;

    private final TorneoRepository torneoRepository;

    private final SquadraRepository squadraRepository;

    public SiwCalcioApplication(PartitaRepository partitaRepository, TorneoRepository torneoRepository, SquadraRepository squadrarepository) {
        this.partitaRepository = partitaRepository;
        this.torneoRepository = torneoRepository;
        this.squadraRepository = squadrarepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SiwCalcioApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("---INIZIO TEST---");
        testSquadreConGiocatoriOSenza();
        System.out.println("---FINE TEST---");
    }

    private void testSquadreConGiocatoriOSenza() {
        StopWatch watch = new StopWatch();

        watch.start("testSquadreConGiocatoriOSenza");
        List<Squadra> ls = (List<Squadra>) squadraRepository.findAll();
        ls.get(1).getNome();
        watch.stop();

        //modificare giocatori a eager per il test

        System.out.println(watch.prettyPrint());
    }

}
