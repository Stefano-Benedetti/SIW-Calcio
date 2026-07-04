package it.progettosiw.siwcalcio.repository;

import it.progettosiw.siwcalcio.model.Partita;
import it.progettosiw.siwcalcio.model.Torneo;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.time.Year;
import java.util.Optional;

public interface TorneoRepository extends CrudRepository<Torneo,Long> {

    boolean existsByNomeAndAnno(String nome, Year anno);

    Optional<Torneo> findById(Long id);

    @EntityGraph(attributePaths = "iscrizioni")
    Optional<Torneo> findWithIscrizioniById(Long id);
}
