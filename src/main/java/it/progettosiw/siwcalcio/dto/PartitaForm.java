package it.progettosiw.siwcalcio.dto;

import java.time.LocalDateTime;

public class PartitaForm {

    private LocalDateTime data;

    private String luogo;

    private Long arbitroId;

    private Long squadraHomeId;

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
