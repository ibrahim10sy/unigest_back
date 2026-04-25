package gestion.scolaire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gestion.scolaire.dto.DocumentType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Medias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedia;

    private String fichier;

    private String fichierUrl;

    private String dateEnregistrement;

    @Enumerated(EnumType.STRING)
    private DocumentType type;

    private Long referenceId; 
}