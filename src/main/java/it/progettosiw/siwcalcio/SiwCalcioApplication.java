package it.progettosiw.siwcalcio;

import it.progettosiw.siwcalcio.model.*;
import it.progettosiw.siwcalcio.repository.ArbitroRepository;
import it.progettosiw.siwcalcio.repository.PartitaRepository;
import it.progettosiw.siwcalcio.repository.SquadraRepository;
import it.progettosiw.siwcalcio.repository.TorneoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SiwCalcioApplication implements CommandLineRunner {

    private final PartitaRepository partitaRepository;

    private final TorneoRepository torneoRepository;

    private final ArbitroRepository arbitroRepository;

    private final SquadraRepository squadraRepository;

    private static final Logger logger = LoggerFactory.getLogger(SiwCalcioApplication.class);

    public SiwCalcioApplication(PartitaRepository partitaRepository, TorneoRepository torneoRepository, ArbitroRepository arbitroRepository, SquadraRepository squadraRepository) {
        this.partitaRepository = partitaRepository;
        this.torneoRepository = torneoRepository;
        this.arbitroRepository = arbitroRepository;
        this.squadraRepository = squadraRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SiwCalcioApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // eseguire un test alla volta per evitare tempistiche inconsistenti
        // i tempi segnati sotto sono stati misurati da casa con un PC desktop potente
        logger.info("INIZIO TEST");
        //testTorneoConSquadre();
        testSquadreConGiocatoriOSenza();
        logger.info("FINE TEST");
    }

    // visto che nella pagina del torneo devo caricare i nomi di tutte le squadre
    // allora carico dal DB il torneo e faccio un join fetch con le sue iscrizioni
    private void testTorneoConSquadre(){
        StopWatch watch = new StopWatch();

        watch.start("testTorneoConSquadre");

        Torneo t = torneoRepository.findById(1L).get();               // circa 60 ms con 2 query
        //Torneo t = torneoRepository.findWithIscrizioniById(1L).get();   // circa 100 ms con 1 query pesante

        for(SquadraIscritta is : t.getIscrizioni()){
            is.getSquadra().getNome();
        }

        watch.stop();

        System.out.println(watch.prettyPrint());
    }
    // quindi una query molto pesante è peggio di due query

    // esempio di una situazione in cui avrebbe senso usare join fetch o EntityGraph
    private void testSquadreConGiocatoriOSenza() {
        StopWatch watch = new StopWatch();

        watch.start("testSquadreConGiocatoriOSenza");
        List<Squadra> ls = (List<Squadra>) squadraRepository.findAllWithGiocatori();    // circa 80 ms con 1 query pesante
        //List<Squadra> ls = (List<Squadra>) squadraRepository.findAll();               // circa 100 ms con 1+N query
        for(Squadra s : ls)
            s.getGiocatori().get(1).getNome();
        watch.stop();

        System.out.println(watch.prettyPrint());
    }
}
