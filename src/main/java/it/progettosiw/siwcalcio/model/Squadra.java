package it.progettosiw.siwcalcio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.Year;
import java.util.List;
import java.util.Objects;

@Entity
public class Squadra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min=2, max=50, message = "Deve essere tra i 2 e i 50 caratteri")
    @Column(nullable = false, unique = true)
    private String nome;

    @NotNull
    @Column(nullable = false)
    private Year fondazione;

    @NotBlank
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ'’ -]+$", message = "Sono ammesse solo lettere, trattini, spazi e apostrofi")
    @Size(min=1, max=50, message = "Deve essere massimo 50 caratteri")
    @Column(nullable = false)
    private String citta;

    @OneToMany(mappedBy = "squadra", cascade = CascadeType.REMOVE)
    private List<Giocatore> giocatori;

    @OneToMany(mappedBy = "squadra")    //la rimozione delle iscrizioni è gestita separatamente
    private List<SquadraIscritta> iscrizioni;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Squadra squadra = (Squadra) o;
        return Objects.equals(id, squadra.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
