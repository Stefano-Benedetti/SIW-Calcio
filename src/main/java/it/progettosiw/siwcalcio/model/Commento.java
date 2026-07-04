package it.progettosiw.siwcalcio.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Commento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String testo;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Utente utente;

    public Commento(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Commento commento = (Commento) o;
        return Objects.equals(id, commento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
