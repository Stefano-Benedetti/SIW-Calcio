package it.progettosiw.siwcalcio.repository;

import it.progettosiw.siwcalcio.model.Utente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UtenteRepository extends CrudRepository<Utente,Long> {

    Optional<Utente> findByUsername(String username);

    boolean existsByUsername(String email);

}
