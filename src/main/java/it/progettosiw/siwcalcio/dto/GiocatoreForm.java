package it.progettosiw.siwcalcio.dto;

import it.progettosiw.siwcalcio.model.Giocatore;
import it.progettosiw.siwcalcio.model.RuoloGiocatore;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class GiocatoreForm {

    @Pattern(regexp = "^[\\p{L}' ]+$", message = "Sono ammesse solo lettere, spazi e apostrofi")
    @NotBlank
    @Size(min=1, max=50, message = "Deve essere massimo 50 caratteri")
    private String nome;

    @Pattern(regexp = "^[\\p{L}' ]+$", message = "Sono ammesse solo lettere, spazi e apostrofi")
    @NotBlank
    @Size(min=1, max=50, message = "Deve essere massimo 50 caratteri")
    private String cognome;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate nascita;

    @NotNull
    private RuoloGiocatore ruolo;

    @Max(value = 250 , message = "Altezza non valida")
    @Min(value = 60, message = "Altezza non valida")
    private int altezza;    //centimetri

    @NotNull
    private Long squadraId;

    public GiocatoreForm(){
        this.altezza = 0;
    }

    public GiocatoreForm(Giocatore g){
        this.nome = g.getNome();
        this.cognome = g.getCognome();
        this.nascita = g.getNascita();
        this.ruolo = g.getRuolo();
        this.altezza = g.getAltezza();
        this.squadraId = g.getSquadra().getId();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getNascita() {
        return nascita;
    }

    public void setNascita(LocalDate nascita) {
        this.nascita = nascita;
    }

    public RuoloGiocatore getRuolo() {
        return ruolo;
    }

    public void setRuolo(RuoloGiocatore ruolo) {
        this.ruolo = ruolo;
    }

    public int getAltezza() {
        return altezza;
    }

    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }

    public Long getSquadraId() {
        return squadraId;
    }

    public void setSquadraId(Long squadraId) {
        this.squadraId = squadraId;
    }
}
