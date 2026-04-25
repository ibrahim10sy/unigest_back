package gestion.scolaire.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String matricule;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    private LocalDate dateNaissance;

    @ManyToOne
    private Parent parent;

    @OneToMany(mappedBy = "etudiant")
    @JsonIgnore
    private List<Inscription> inscription;

}