package gestion.scolaire.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class Affectation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Enseignant enseignant;

    @ManyToOne
    private Matiere matiere;

    @ManyToOne
    private Classe classe;

    @OneToMany(mappedBy = "affectation")
    @JsonIgnore
    private List<Seance> seances;
   
    @OneToMany(mappedBy = "affectation")
    @JsonIgnore
    private List<Note> notes;
}
