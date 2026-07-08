package it.progettosiw.siwcalcio.model;

import it.progettosiw.siwcalcio.dto.GiocatoreForm;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Giocatore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false)
    private LocalDate nascita;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RuoloGiocatore ruolo;

    @Column(nullable = false)
    private int altezza;    //centimetri

    @ManyToOne
    @JoinColumn(nullable = false)
    private Squadra squadra;

    public Giocatore(){}

    public Giocatore(GiocatoreForm gf, Squadra s){
        this.nome = gf.getNome();
        this.cognome = gf.getCognome();
        this.nascita = gf.getNascita();
        this.ruolo = gf.getRuolo();
        this.altezza = gf.getAltezza();
        this.squadra = s;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Squadra getSquadra() {
        return squadra;
    }

    public void setSquadra(Squadra squadra) {
        this.squadra = squadra;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Giocatore giocatore = (Giocatore) o;
        return Objects.equals(id, giocatore.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
