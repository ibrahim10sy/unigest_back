package gestion.scolaire.model;

import java.time.LocalDate;

import jakarta.persistence.*;


import lombok.Data;

@Data
@Entity
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Inscription inscription; // lien vers l'année scolaire et la classe

    private double montant;

    private LocalDate datePaiement;

    @Enumerated(EnumType.STRING)
    private ModePaiement modePaiement;

    private String reference;
}
