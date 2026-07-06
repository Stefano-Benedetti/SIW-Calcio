package it.progettosiw.siwcalcio.validation;

import it.progettosiw.siwcalcio.dto.GiocatoreForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.Period;

@Component
public class EtaGiocatoreValidator implements Validator {
    @Override
    public void validate(Object o, Errors errors) {
        LocalDate data;

        if(o instanceof GiocatoreForm form)
            data = form.getNascita();
        else
            return;

        if (data != null) {
            int age = Period.between(data, LocalDate.now()).getYears();
            if (age < 14)
                errors.rejectValue("nascita", "giocatore.troppoGiovane");
            if (age > 100)
                errors.rejectValue("nascita", "giocatore.troppoAnziano");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return GiocatoreForm.class.equals(aClass);
    }
}
