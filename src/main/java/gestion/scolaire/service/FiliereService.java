package gestion.scolaire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gestion.scolaire.model.Filiere;
import gestion.scolaire.model.Matiere;
import gestion.scolaire.model.Niveau;
import gestion.scolaire.repository.FiliereRepository;
import gestion.scolaire.repository.MatiereRepository;
import gestion.scolaire.repository.NiveauRepository;

@Service
public class FiliereService {

    @Autowired
    private FiliereRepository filiereRepository;

    @Autowired
    private NiveauRepository niveauRepository;
    @Autowired
    private MatiereRepository matiereRepository;

    // Ajouter une filière
    // Ajouter une filière liée à un niveau
    public Filiere ajouterFiliere(String nom, Long niveauId,double fraisInscription, double fraisScolarite) {
        // On récupère le niveau parent
        Niveau niveau = niveauRepository.findById(niveauId)
                .orElseThrow(() -> new RuntimeException("Niveau introuvable avec l'ID : " + niveauId));

        Filiere filiere = new Filiere();
        filiere.setNom(nom);
        filiere.setActif(true);
        filiere.setFraisInscription(fraisInscription);
        filiere.setFraisScolarite(fraisScolarite);
        filiere.setNiveau(niveau); // On lie la filière au niveau

        return filiereRepository.save(filiere);
    }

    // Modifier une filière
    public Filiere modifierFiliere(Long id, String nom, Boolean actif, Long niveauId, double fraisInscription, double fraisScolarite) {
        Filiere filiere = filiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));

        if (nom != null)
            filiere.setNom(nom);
        if (actif != null)
            filiere.setActif(actif);
        if (fraisInscription != 0)
            filiere.setFraisInscription(fraisInscription);
        if (fraisScolarite != 0)
            filiere.setFraisScolarite(fraisScolarite);

        // Si on change le niveau de la filière
        if (niveauId != null) {
            Niveau niveau = niveauRepository.findById(niveauId)
                    .orElseThrow(() -> new RuntimeException("Niveau introuvable"));
            filiere.setNiveau(niveau);
        }

        return filiereRepository.save(filiere);
    }

    // Supprimer une filière
    public void supprimerFiliere(Long id) {
        filiereRepository.deleteById(id);
    }



    // Récupérer une filière par ID
    public Filiere getFiliereById(Long id) {
        return filiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));
    }

    // Récupérer toutes les filières
    public List<Filiere> getAllFilieres() {
        return filiereRepository.findAll();
    }

    // Récupérer toutes les filières actives
    public List<Filiere> getFilieresActives() {
        return filiereRepository.findByActifTrue();
    }

    // Rechercher filières par nom
    public List<Filiere> rechercherParNom(String nom) {
        return filiereRepository.findByNomContainingIgnoreCase(nom);
    }
}
