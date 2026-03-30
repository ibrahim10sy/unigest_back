package gestion.scolaire.model;

import jakarta.persistence.*;


import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean actif = true;
}
