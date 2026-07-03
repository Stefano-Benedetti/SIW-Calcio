package it.progettosiw.siwcalcio.repository;

import it.progettosiw.siwcalcio.model.Partita;
import it.progettosiw.siwcalcio.model.Torneo;
import org.springframework.data.repository.CrudRepository;

import java.time.Year;
import java.util.Optional;

public interface TorneoRepository extends CrudRepository<Torneo,Long> {

    boolean existsByNomeAndAnno(String nome, Year anno);

    Optional<Torneo> findById(Long id);
}
