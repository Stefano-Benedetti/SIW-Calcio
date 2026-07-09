package it.progettosiw.siwcalcio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Objects;

@Entity
public class Arbitro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Pattern(regexp = "^[\\p{L}' ]+$", message = "Sono ammesse solo lettere, spazi e apostrofi")
    @NotBlank
    @Size(min=2, max=50, message = "Deve essere tra i 2 e i 50 caratteri")
    @Column(nullable = false)
    private String nome;

    @Pattern(regexp = "^[\\p{L}' ]+$", message = "Sono ammesse solo lettere, spazi e apostrofi")
    @NotBlank
    @Size(min=2, max=50, message = "Deve essere tra i 2 e i 50 caratteri")
    @Column(nullable = false)
    private String cognome;

    @NotNull
    @Column(nullable = false, unique = true)
    private Long codiceArbitrale;

    public Arbitro(){}

    public Arbitro(String nome, String cognome, Long codiceArbitrale) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceArbitrale = codiceArbitrale;
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
