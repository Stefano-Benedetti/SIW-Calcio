package it.progettosiw.siwcalcio.repository;

import it.progettosiw.siwcalcio.model.Squadra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SquadraRepository extends CrudRepository<Squadra,Long> {
    @Query("""
        SELECT s
        FROM Squadra s
        LEFT JOIN SquadraIscritta si
          ON si.squadra.id = s.id AND si.torneo.id = :torneoId
        WHERE si.id IS NULL
    """)
    List<Squadra> findSquadreNonIscritteAlTorneo(@Param("torneoId") Long torneoId);

    List<Squadra> findAllByOrderByNomeAsc();
}
