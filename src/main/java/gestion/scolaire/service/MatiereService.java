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
    public Matiere ajouterMatiere(Matiere matiere ){
        
        return matiereRepository.save(matiere);
    }

    // Modifier une matière
    public Matiere modifierMatiere(Long id, Matiere matiere){
        Matiere m = matiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matière introuvable"));
        // m.setCoefficient(matiere.getCoefficient());
        m.setNom(matiere.getNom());

        return matiereRepository.save(m);
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
