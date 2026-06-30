package it.progettosiw.siwcalcio.repository;

import it.progettosiw.siwcalcio.model.SquadraIscritta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SquadraIscrittaRepository extends CrudRepository<SquadraIscritta,Long> {

    public List<SquadraIscritta> findSquadraIscrittasByTorneoIdOrderByVittorieDesc(Long torneoId);
}
