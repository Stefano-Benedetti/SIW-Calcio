package it.progettosiw.siwcalcio.model;

import jakarta.persistence.*;

import java.time.Year;
import java.util.List;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"nome","anno"}))
public class Torneo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Year anno;

    @Column(nullable = false)
    private String descrizione;

    //non ha senso tenere le partite senza un torneo, ma i tornei non possono essere eliminati
    @OneToMany(mappedBy="torneo", orphanRemoval = true)
    private List<Partita> partite;

    // non ha senso tenere le iscrizioni senza un torneo, ma i tornei non possono essere eliminati
    @OneToMany(mappedBy = "torneo", orphanRemoval = true)
    private List<SquadraIscritta> iscrizioni;

    public Torneo(){}

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

    public Year getAnno() {
        return anno;
    }

    public void setAnno(Year anno) {
        this.anno = anno;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Partita> getPartite() {
        return partite;
    }

    public void setPartite(List<Partita> partite) {
        this.partite = partite;
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
        Torneo torneo = (Torneo) o;
        return Objects.equals(id, torneo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
