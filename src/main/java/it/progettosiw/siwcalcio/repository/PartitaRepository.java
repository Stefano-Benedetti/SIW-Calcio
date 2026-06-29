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
       SELECT *
       FROM Partita p
       WHERE p.torneo_id = :torneoId
         AND p.stato = 'SCHEDULED'
         AND p.data >= :inizioGiorno
         AND p.data < :inizioGiornoSuccessivo
       """, nativeQuery = true)
    List<Partita> findPartiteDiOggiScheduledByTorneo(@Param("torneoId") Long torneoId, @Param("inizioGiorno") LocalDateTime inizioGiorno, @Param("inizioGiornoSuccessivo") LocalDateTime inizioGiornoSuccessivo);

}
