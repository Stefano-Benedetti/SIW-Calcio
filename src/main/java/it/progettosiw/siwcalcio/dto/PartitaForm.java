package it.progettosiw.siwcalcio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class PartitaForm {

    @NotNull
    private LocalDateTime data;

    @Size(min=1, max=50, message = "Deve essere massimo 50 caratteri")
    @NotBlank
    private String luogo;

    @NotNull
    private Long arbitroId;

    @NotNull
    private Long squadraHomeId;

    @NotNull
    private Long squadraAwayId;

    public PartitaForm(){

    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public Long getArbitroId() {
        return arbitroId;
    }

    public void setArbitroId(Long arbitroId) {
        this.arbitroId = arbitroId;
    }

    public Long getSquadraHomeId() {
        return squadraHomeId;
    }

    public void setSquadraHomeId(Long squadraHomeId) {
        this.squadraHomeId = squadraHomeId;
    }

    public Long getSquadraAwayId() {
        return squadraAwayId;
    }

    public void setSquadraAwayId(Long squadraAwayId) {
        this.squadraAwayId = squadraAwayId;
    }
}
