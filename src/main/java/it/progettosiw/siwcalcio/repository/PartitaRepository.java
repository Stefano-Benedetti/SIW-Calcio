package it.progettosiw.siwcalcio.repository;

import it.progettosiw.siwcalcio.model.Partita;
import it.progettosiw.siwcalcio.model.StatoPartita;
import org.springframework.data.jpa.repository.EntityGraph;
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
    List<Partita> findPartiteByGiornoAndByTorneo(@Param("torneoId") Long torneoId, @Param("inizioGiorno") LocalDateTime inizioGiorno, @Param("inizioGiornoSuccessivo") LocalDateTime inizioGiornoSuccessivo);

    List<Partita> findPartitasByTorneoIdAndStatoOrderByDataDesc(Long torneoId, StatoPartita stato);

    List<Partita> findPartitasByTorneoIdAndStatoOrderByDataAsc(Long torneoId, StatoPartita stato);

    List<Partita> findPartitasByTorneoIdAndSquadraHomeIdOrTorneoIdAndSquadraAwayId(Long torneoId, Long squadraId, Long torneoId2, Long squadraId2);

    void deleteAllByTorneoIdAndSquadraHomeIdOrTorneoIdAndSquadraAwayId(Long torneoId, Long squadraId, Long torneoId2, Long squadraId2);

    boolean existsBySquadraAwayIdAndSquadraHomeIdAndData(Long squadraAwayId, Long squadraHomeId, LocalDateTime data);

    @Query("SELECT p FROM Partita p JOIN FETCH p.commenti WHERE p.id = :id")
    Optional<Partita> findByIdWithCommenti(Long id);
}
