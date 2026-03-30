package gestion.scolaire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.scolaire.model.Matiere;
import gestion.scolaire.repository.MatiereRepository;

@Service
public class MatiereService {

    @Autowired
    private MatiereRepository matiereRepository;

    // Ajouter une matière
    public Matiere ajouterMatiere(String nom, double coefficient){
        Matiere matiere = new Matiere();
        matiere.setNom(nom);
        matiere.setCoefficient(coefficient);
        return matiereRepository.save(matiere);
    }

    // Modifier une matière
    public Matiere modifierMatiere(Long id, String nom, Double coefficient){
        Matiere matiere = matiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matière introuvable"));

        if(nom != null) matiere.setNom(nom);
        if(coefficient != null) matiere.setCoefficient(coefficient);

        return matiereRepository.save(matiere);
    }

    // Supprimer une matière
    public void supprimerMatiere(Long id){
        matiereRepository.deleteById(id);
    }

    // Récupérer une matière par ID
    public Matiere getMatiereById(Long id){
        return matiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matière introuvable"));
    }

    // Récupérer toutes les matières
    public List<Matiere> getAllMatieres(){
        return matiereRepository.findAll();
    }

    // Rechercher matières par nom
    public List<Matiere> rechercherParNom(String nom){
        return matiereRepository.findByNomContainingIgnoreCase(nom);
    }
}
