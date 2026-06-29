package it.progettosiw.siwcalcio.model;

import jakarta.persistence.*;

import java.time.Year;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;

@Entity
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

    @OneToMany(cascade = CascadeType.REMOVE)    //non ha senso tenere le partite senza un torneo, ma i tornei non andranno eliminati
    @JoinColumn(name = "torneo_id")     //forse lascio LAZY, ma da testare per le partite giornaliere
    private List<Partita> partite;

    //forse lascio LAZY (dipende se le metto in una pagina dedicata), REMOVE perché non ha senso l'iscrizione senza torneo
    @OneToMany(mappedBy = "torneo", cascade = CascadeType.REMOVE)   // in realtà i tornei non saranno eliminati
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
