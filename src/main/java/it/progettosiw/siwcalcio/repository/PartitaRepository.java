package it.progettosiw.siwcalcio.repository;

import it.progettosiw.siwcalcio.model.Partita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PartitaRepository extends CrudRepository<Partita,Long> {

    @Query(value = """
       SELECT p
        FROM Partita p
        WHERE p.torneo.id = :torneoId
            AND p.data >= :inizioGiorno
            AND p.data < :inizioGiornoSuccessivo
        ORDER BY p.data ASC
       """)
    List<Partita> findPartiteByDataByTorneo(@Param("torneoId") Long torneoId, @Param("inizioGiorno") LocalDateTime inizioGiorno, @Param("inizioGiornoSuccessivo") LocalDateTime inizioGiornoSuccessivo);

}
