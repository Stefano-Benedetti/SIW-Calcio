package it.progettosiw.siwcalcio.repository;

import it.progettosiw.siwcalcio.model.Utente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UtenteRepository extends CrudRepository<Utente,Long> {

    public Optional<Utente> findByUsername(String username);

    public boolean existsByUsername(String email);

}
