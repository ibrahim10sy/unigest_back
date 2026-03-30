package gestion.scolaire.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;


import lombok.Data;

@Data
@Entity
public class Bulletin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Etudiant etudiant;

    @ManyToOne(optional = false)
    private Classe classe;

    private String anneeScolaire;

    private int semestre;

    private double moyenneGenerale;

    private Integer rang;

    private String appreciation;

    private String pdfUrl;

    @OneToMany(mappedBy = "bulletin", cascade = CascadeType.ALL)
    private List<LigneBulletin> lignes;

    public void setDateGeneration(LocalDate now) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDateGeneration'");
    }
}
