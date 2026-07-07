package it.progettosiw.siwcalcio.repository;

import it.progettosiw.siwcalcio.model.Torneo;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.time.Year;
import java.util.List;
import java.util.Optional;

public interface TorneoRepository extends CrudRepository<Torneo,Long> {

    boolean existsByNomeAndAnno(String nome, Year anno);

    Optional<Torneo> findById(Long id);

    @EntityGraph(attributePaths = "iscrizioni")
    Optional<Torneo> findWithIscrizioniById(Long id);

    List<Torneo> findAllByOrderByAnnoDesc();
}
