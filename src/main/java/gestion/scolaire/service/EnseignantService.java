package gestion.scolaire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.scolaire.model.Enseignant;
import gestion.scolaire.model.Role;
import gestion.scolaire.repository.EnseignantRepository;

@Service
public class EnseignantService {

    @Autowired
    private EnseignantRepository enseignantRepository;

    // Créer
    public Enseignant creerEnseignant(Enseignant enseignant){

        enseignant.setRole(Role.ENSEIGNANT);
        enseignant.setActif(true);

        return enseignantRepository.save(enseignant);
    }

    // Modifier
    public Enseignant modifierEnseignant(Long id, Enseignant data){

        Enseignant enseignant = enseignantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enseignant introuvable"));

        enseignant.setNom(data.getNom());
        enseignant.setPrenom(data.getPrenom());
        enseignant.setEmail(data.getEmail());
        enseignant.setTelephone(data.getTelephone());
        enseignant.setSpecialite(data.getSpecialite());

        return enseignantRepository.save(enseignant);
    }

    // Supprimer
    public void supprimerEnseignant(Long id){
        enseignantRepository.deleteById(id);
    }

    // Trouver par ID
    public Enseignant getEnseignantById(Long id){
        return enseignantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enseignant introuvable"));
    }

    // Lister tous
    public List<Enseignant> getAllEnseignants(){
        return enseignantRepository.findAll();
    }
}