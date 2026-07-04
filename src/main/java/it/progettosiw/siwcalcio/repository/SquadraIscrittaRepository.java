package it.progettosiw.siwcalcio.repository;

import it.progettosiw.siwcalcio.model.SquadraIscritta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SquadraIscrittaRepository extends CrudRepository<SquadraIscritta,Long> {

    List<SquadraIscritta> findSquadraIscrittasByTorneoIdOrderByVittorieDesc(Long torneoId);

    void deleteByTorneoIdAndSquadraId(Long torneoId, Long squadraId);

    SquadraIscritta findSquadraIscrittaBySquadraIdAndTorneoId(Long squadraId, Long torneoId);

    boolean existsSquadraIscrittaBySquadraIdAndTorneoId(Long squadraId, Long torneoId);
}
