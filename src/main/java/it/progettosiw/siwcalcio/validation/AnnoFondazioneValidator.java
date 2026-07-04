package it.progettosiw.siwcalcio.validation;

import it.progettosiw.siwcalcio.model.Squadra;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class AnnoFondazioneValidator implements Validator {

    @Override
    public void validate(Object o, Errors errors) {

        if (!(o instanceof Squadra s) || s.getFondazione() == null) return;

        int anno = s.getFondazione().getValue();

        // la squadra più antica del mondo è Sheffield FC, fondata nel 1857
        if (anno < 1857 || anno > LocalDate.now().getYear() )
            errors.rejectValue("fondazione", "squadra.annoDiFondazioneNonValido");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Squadra.class.equals(aClass);
    }
}
