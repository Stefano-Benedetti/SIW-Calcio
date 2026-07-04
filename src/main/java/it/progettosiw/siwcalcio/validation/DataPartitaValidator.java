package it.progettosiw.siwcalcio.validation;

import it.progettosiw.siwcalcio.dto.PartitaForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;
import java.time.Period;

@Component
public class DataPartitaValidator implements Validator {

    @Override
    public void validate(Object o, Errors errors) {
        LocalDateTime data;

        if(!(o instanceof PartitaForm form) || form.getData()==null) return;

        data = form.getData();

        LocalDateTime min = LocalDateTime.of(2025,1,1,0,0);
        LocalDateTime max = LocalDateTime.now().plusYears(2);

        if(data.isBefore(min) || data.isAfter(max))
            errors.rejectValue("data", "partita.dataNonValida");

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PartitaForm.class.equals(aClass);
    }
}
