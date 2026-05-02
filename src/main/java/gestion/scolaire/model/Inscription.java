package gestion.scolaire.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


import lombok.Data;

@Data
@Entity
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Etudiant etudiant;

    @ManyToOne(optional = false)
    private Classe classe;

    @ManyToOne(optional = false)
    private AnneeScolaire anneeScolaire;

    private LocalDate dateInscription;

    private double montantReduction;

    private String motifReduction;

    private String statut; // INSCRIT, ABANDONNE, TERMINE

    @OneToMany(mappedBy = "inscription")
    @JsonIgnore
    private List<Paiement> paiements;
}