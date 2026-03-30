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

    private double valeur;

    @Enumerated(EnumType.STRING)
    private TypeNote type;

    private int semestre;

    private LocalDate dateEvaluation;
}
