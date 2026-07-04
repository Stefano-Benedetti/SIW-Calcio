package it.progettosiw.siwcalcio.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
//una sola iscrizione per torneo
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"torneo_id","squadra_id"}))
public class SquadraIscritta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int vittorie;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Torneo torneo;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Squadra squadra;

    public SquadraIscritta(){
        this.vittorie = 0;
    }

    public SquadraIscritta(Torneo torneo, Squadra squadra){
        this.vittorie = 0;
        this.torneo = torneo;
        this.squadra = squadra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public Squadra getSquadra() {
        return squadra;
    }

    public void setSquadra(Squadra squadra) {
        this.squadra = squadra;
    }

    public int getVittorie() {
        return vittorie;
    }

    public void setVittorie(int vittorie) {
        this.vittorie = vittorie;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SquadraIscritta that = (SquadraIscritta) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public void addVittoria(){
        this.vittorie += 1;
    }

    public void removeVittoria() {
        this.vittorie -= 1;
    }
}
