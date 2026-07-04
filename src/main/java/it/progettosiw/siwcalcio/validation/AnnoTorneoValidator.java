package it.progettosiw.siwcalcio.validation;

import it.progettosiw.siwcalcio.model.Torneo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class AnnoTorneoValidator implements Validator {

    @Override
    public void validate(Object o, Errors errors) {

        if (!(o instanceof Torneo t) || t.getAnno() == null) return;
        int anno = t.getAnno().getValue();

        int annoCorrente = LocalDate.now().getYear();

        // si può modificare anche un torneo vecchio, la data minima non può essere (anno corrente - 1)
        // 2025 perché è (anno di creazione del sistema - 1)
        if (anno < 2025 || anno > annoCorrente + 1)
            errors.rejectValue("anno", "torneo.annoNonValido");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Torneo.class.equals(aClass);
    }
}
