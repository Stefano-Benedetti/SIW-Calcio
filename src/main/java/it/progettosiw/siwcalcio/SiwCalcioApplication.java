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
//        System.out.println("---INIZIO TEST---");
//        testPartitaConArbitroETorneo();
//        //testPartitaSenzaArbitroETorneo();
//        System.out.println("---FINE TEST---");
        test();
    }

//    private void testPartitaConArbitroETorneo() {
//        StopWatch watch = new StopWatch();
//        watch.start("testPartitaConArbitroETorneo");
//        Partita p = partitaRepository.findById(1L).get();
//        p.getArbitro().getId();   //<<<<< caso in cui devo caricare tutta la pagina della partita
//        p.getTorneo().getId();    //<<<<<
//        p.getSquadraAway().getNome();
//        p.getSquadraHome().getNome();
//        watch.stop();
//        System.out.println(watch.prettyPrint());
//    }
//
//    private void testPartitaSenzaArbitroETorneo() {
//        StopWatch watch = new StopWatch();
//        watch.start("testPartitaSenzaArbitroETorneo");
//        Partita p = partitaRepository.findPartitaById(1L).get();
//        p.getArbitro().getId();   //<<<<< caso in cui devo caricare tutta la pagina della partita
//        p.getTorneo().getId();    //<<<<<
//        p.getSquadraAway().getNome();
//        p.getSquadraHome().getNome();
//        watch.stop();
//        System.out.println(watch.prettyPrint());
//    }

    private void test(){

    }

}
