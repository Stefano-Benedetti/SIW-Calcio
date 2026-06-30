package it.progettosiw.siwcalcio.dto;

import it.progettosiw.siwcalcio.model.Giocatore;
import it.progettosiw.siwcalcio.model.RuoloGiocatore;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class GiocatoreForm {

    private String nome;

    private String cognome;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate nascita;

    private RuoloGiocatore ruolo;

    private int altezza;    //centimetri

    private Long squadraId;

    public GiocatoreForm(){}

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
