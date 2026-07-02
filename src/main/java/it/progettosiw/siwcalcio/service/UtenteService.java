package it.progettosiw.siwcalcio.service;

import it.progettosiw.siwcalcio.dto.RegistrationForm;
import it.progettosiw.siwcalcio.model.Credenziali;
import it.progettosiw.siwcalcio.model.Utente;
import it.progettosiw.siwcalcio.repository.CredenzialiRepository;
import it.progettosiw.siwcalcio.repository.UtenteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteService {

    private CredenzialiRepository credenzialiRepository;

    private UtenteRepository utenteRepository;

    public UtenteService(CredenzialiRepository credenzialiRepository, UtenteRepository utenteRepository){
        this.credenzialiRepository = credenzialiRepository;
        this.utenteRepository = utenteRepository;
    }


    public void register(RegistrationForm form){
        if (utenteRepository.existsByUsername(form.getUsername()))
            throw new RuntimeException("questo nome utente è già in uso");
        Utente utente = new Utente(form.getUsername());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String encrypted_ps = encoder.encode(form.getPassword());

        Credenziali credenziali = new Credenziali(form.getUsername(), encrypted_ps, utente);

        credenzialiRepository.save(credenziali);
    }

    public Utente getCurrentUser(){
        UserDetails userDetails = getCurrentUserDetails();

        Optional<Utente> optUser = utenteRepository.findByUsername(userDetails.getUsername());
        if(optUser.isEmpty()){
            throw new RuntimeException("utente corrente non trovato");
        }
        return optUser.get();
    }

    public UserDetails getCurrentUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication==null || (authentication instanceof AnonymousAuthenticationToken)) {
            return null;
        }
        return (UserDetails) authentication.getPrincipal();
    }

}
