package gestion.scolaire.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


import lombok.Data;

@Data
@Entity
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private double coefficient;

    @OneToMany(mappedBy = "matiere")
    @JsonIgnore
    private List<Affectation> affectations;
}
