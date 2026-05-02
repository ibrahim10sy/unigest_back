package gestion.scolaire.model;

import java.time.LocalDate;

import jakarta.persistence.*;


import lombok.Data;

@Data
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Etudiant etudiant;

    @ManyToOne(optional = false)
    private Affectation affectation;

    @ManyToOne(optional = false)
    private AnneeScolaire anneeScolaire;

    private double valeur;

    @Enumerated(EnumType.STRING)
    private TypeNote type;

    private Integer periode; // semestre ou trimestre

    @Enumerated(EnumType.STRING)
    private TypePeriode typePeriode; // SEMESTRE, TRIMESTRE

    private LocalDate dateEvaluation;
}