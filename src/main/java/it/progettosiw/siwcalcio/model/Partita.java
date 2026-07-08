package it.progettosiw.siwcalcio.model;

import it.progettosiw.siwcalcio.dto.PartitaForm;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Partita {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private String luogo;

    @Min(value = 0, message = "I goals non posso essere negativi")
    // la partita con più goals della storia è finita 149 - 0
    @Max(value = 150, message = "I goals non possono essere così tanti (spero...)")
    @NotNull
    @Column(nullable = false)
    private Integer goalsHome;

    @Min(value = 0, message = "I goals non posso essere negativi")
    // la partita con più goals della storia è finita 149 - 0
    @Max(value = 150, message = "I goals non possono essere così tanti (spero...)")
    @NotNull
    @Column(nullable = false)
    private Integer goalsAway;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoPartita stato;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Arbitro arbitro;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name="partita_id")
    private List<Commento> commenti;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Squadra squadraHome;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Squadra squadraAway;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Torneo torneo;

    public Partita(){}

    public Partita(PartitaForm pf, Arbitro arbitro, Squadra squadraHome, Squadra squadraAway, Torneo torneo){
        this.stato = StatoPartita.SCHEDULED;
        this.goalsAway = 0;
        this.goalsHome = 0;
        this.data = pf.getData();
        this.luogo = pf.getLuogo();
        this.arbitro = arbitro;
        this.squadraHome = squadraHome;
        this.squadraAway = squadraAway;
        this.torneo = torneo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getGoalsHome() {
        return goalsHome;
    }

    public void setGoalsHome(int goalsHome) {
        this.goalsHome = goalsHome;
    }

    public int getGoalsAway() {
        return goalsAway;
    }

    public void setGoalsAway(int goalsAway) {
        this.goalsAway = goalsAway;
    }

    public StatoPartita getStato() {
        return stato;
    }

    public void setStato(StatoPartita stato) {
        this.stato = stato;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public List<Commento> getCommenti() {
        return commenti;
    }

    public void setCommenti(List<Commento> commenti) {
        this.commenti = commenti;
    }

    public Squadra getSquadraHome() {
        return squadraHome;
    }

    public void setSquadraHome(Squadra squadraHome) {
        this.squadraHome = squadraHome;
    }

    public Squadra getSquadraAway() {
        return squadraAway;
    }

    public void setSquadraAway(Squadra squadraAway) {
        this.squadraAway = squadraAway;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Partita partita = (Partita) o;
        return Objects.equals(id, partita.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
