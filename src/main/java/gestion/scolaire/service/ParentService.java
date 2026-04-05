package gestion.scolaire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.scolaire.model.Parent;
import gestion.scolaire.model.Role;
import gestion.scolaire.repository.ParentRepository;

@Service
public class ParentService {

    @Autowired
    private ParentRepository parentRepository;

    // Créer un parent
    public Parent creerParent(Parent parent){
        parent.setRole(Role.PARENT);
        parent.setActif(true);
        parent.setTelephone(parent.getTelephone());
        return parentRepository.save(parent);
    }

    // Modifier un parent
    public Parent modifierParent(Long id, Parent data){

        Parent parent = parentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent introuvable"));

        parent.setNom(data.getNom());
        parent.setPrenom(data.getPrenom());
        parent.setEmail(data.getEmail());
        parent.setTelephone(data.getTelephone());

        return parentRepository.save(parent);
    }

    // Supprimer
    public void supprimerParent(Long id){
        parentRepository.deleteById(id);
    }

    // Trouver par ID
    public Parent getParentById(Long id){
        return parentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent introuvable"));
    }

    // Lister tous les parents
    public List<Parent> getAllParents(){
        return parentRepository.findAll();
    }
}
