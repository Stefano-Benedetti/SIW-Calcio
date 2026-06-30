package it.progettosiw.siwcalcio.model;

import jakarta.persistence.*;

import java.time.Year;
import java.util.List;
import java.util.Objects;

@Entity
public class Squadra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private Year fondazione;

    @Column(nullable = false)
    private String citta;

    @OneToMany(mappedBy = "squadra", cascade = CascadeType.REMOVE)    //probabilmente da lasciare LAZY
    private List<Giocatore> giocatori;

    @OneToMany(mappedBy = "squadra", cascade = CascadeType.REMOVE)
    private List<SquadraIscritta> iscrizioni;

    @OneToMany(mappedBy = "squadraHome", cascade = CascadeType.REMOVE)
    private List<Partita> partiteInCasa;

    @OneToMany(mappedBy = "squadraAway", cascade = CascadeType.REMOVE)
    private List<Partita> partiteFuori;

    public Squadra(){}

    public long getId() {
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

    public List<Partita> getPartiteInCasa() {
        return partiteInCasa;
    }

    public void setPartiteInCasa(List<Partita> partiteInCasa) {
        this.partiteInCasa = partiteInCasa;
    }

    public List<Partita> getPartiteFuori() {
        return partiteFuori;
    }

    public void setPartiteFuori(List<Partita> partiteFuori) {
        this.partiteFuori = partiteFuori;
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
