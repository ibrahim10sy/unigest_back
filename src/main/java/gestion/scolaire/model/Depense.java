package gestion.scolaire.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

@Data
@Entity
public class Depense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle; // ex: Achat craies

    private Double montant;

    private LocalDate dateDepense;

    private String description;

    private String modePaiement;

    private LocalDate dateCreation;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private CategorieDepense categorieDepense;
    
    @ManyToOne
    @JoinColumn(name = "annee_scolaire_id")
    private AnneeScolaire anneeScolaire;

}
