package it.progettosiw.siwcalcio.model;

import jakarta.persistence.*;

import java.time.Year;
import java.util.List;
import java.util.Objects;

@Entity
public class Squadra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private Year fondazione;

    @Column(nullable = false)
    private String citta;

    @OneToMany(mappedBy = "squadra")
    private List<Giocatore> giocatori;

    @OneToMany(mappedBy = "squadra")
    private List<SquadraIscritta> iscrizioni;

    public Squadra(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Year getFondazione() {
        return fondazione;
    }

    public void setFondazione(Year fondazione) {
        this.fondazione = fondazione;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public List<Giocatore> getGiocatori() {
        return giocatori;
    }

    public void setGiocatori(List<Giocatore> giocatori) {
        this.giocatori = giocatori;
    }

    public List<SquadraIscritta> getIscrizioni() {
        return iscrizioni;
    }

    public void setIscrizioni(List<SquadraIscritta> iscrizioni) {
        this.iscrizioni = iscrizioni;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Squadra squadra = (Squadra) o;
        return id == squadra.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
