package it.progettosiw.siwcalcio.dto;

import jakarta.validation.constraints.NotNull;

public class IscrizioneForm {

    @NotNull
    Long squadraId;

    public IscrizioneForm() {
    }

    public Long getSquadraId() {
        return squadraId;
    }

    public void setSquadraId(Long squadraId) {
        this.squadraId = squadraId;
    }
}
