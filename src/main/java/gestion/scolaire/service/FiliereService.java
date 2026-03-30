package gestion.scolaire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.scolaire.model.Filiere;
import gestion.scolaire.repository.FiliereRepository;

@Service
public class FiliereService {

    @Autowired
    private FiliereRepository filiereRepository;

    // Ajouter une filière
    public Filiere ajouterFiliere(String nom){
        Filiere filiere = new Filiere();
        filiere.setNom(nom);
        filiere.setActif(true);
        return filiereRepository.save(filiere);
    }

    // Modifier une filière
    public Filiere modifierFiliere(Long id, String nom, Boolean actif){
        Filiere filiere = filiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));

        if(nom != null) filiere.setNom(nom);
        if(actif != null) filiere.setActif(actif);

        return filiereRepository.save(filiere);
    }

    // Supprimer une filière
    public void supprimerFiliere(Long id){
        filiereRepository.deleteById(id);
    }

    // Récupérer une filière par ID
    public Filiere getFiliereById(Long id){
        return filiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));
    }

    // Récupérer toutes les filières
    public List<Filiere> getAllFilieres(){
        return filiereRepository.findAll();
    }

    // Récupérer toutes les filières actives
    public List<Filiere> getFilieresActives(){
        return filiereRepository.findByActifTrue();
    }

    // Rechercher filières par nom
    public List<Filiere> rechercherParNom(String nom){
        return filiereRepository.findByNomContainingIgnoreCase(nom);
    }
}
