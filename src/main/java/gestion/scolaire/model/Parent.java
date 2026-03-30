package gestion.scolaire.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


import lombok.Data;

@Data
@Entity
public class Parent extends Utilisateur {

    private String telephone;

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<Etudiant> enfants;
}