package gestion.scolaire.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ClasseMatiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;

    @ManyToOne
    @JoinColumn(name = "matiere_id", nullable = false)
    private Matiere matiere;

    private double coefficient;
}