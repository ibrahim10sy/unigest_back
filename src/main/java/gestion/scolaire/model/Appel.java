package gestion.scolaire.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;


import lombok.Data;

@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"seance_id", "etudiant_id"})
    }
)

@Data
@Entity
public class Appel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Seance seance;

    @ManyToOne(optional = false)
    private Etudiant etudiant;

    @Enumerated(EnumType.STRING)
    private StatutPresence statut;

    private Integer minutesRetard = 0;

    // Nouveau
    private String motif; 

    private boolean justifie = false;

    private LocalDateTime dateJustification;
}