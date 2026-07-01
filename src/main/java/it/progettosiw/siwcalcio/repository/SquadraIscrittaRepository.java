package it.progettosiw.siwcalcio.repository;

import it.progettosiw.siwcalcio.model.SquadraIscritta;
import it.progettosiw.siwcalcio.model.Torneo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SquadraIscrittaRepository extends CrudRepository<SquadraIscritta,Long> {

    List<SquadraIscritta> findSquadraIscrittasByTorneoIdOrderByVittorieDesc(Long torneoId);

    void deleteByTorneoIdAndSquadraId(Long torneoId, Long squadraId);
}
