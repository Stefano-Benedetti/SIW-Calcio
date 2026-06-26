package it.progettosiw.siwcalcio.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Arbitro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false, unique = true)
    private Long codiceArbitrale;

    public Arbitro(){}

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

    public Long getCodiceArbitrale() {
        return codiceArbitrale;
    }

    public void setCodiceArbitrale(Long codiceArbitrale) {
        this.codiceArbitrale = codiceArbitrale;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Arbitro arbitro = (Arbitro) o;
        return Objects.equals(id, arbitro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
