package gestion.scolaire.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;


import lombok.Data;

@Data
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Utilisateur expediteur;

    @ManyToOne
    private Utilisateur destinataire;

    private String contenu;

    private LocalDateTime dateEnvoi;
}
