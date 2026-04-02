package gestion.scolaire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.scolaire.model.Etudiant;
import gestion.scolaire.repository.EtudiantRepository;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;
    

    public Etudiant creerEtudiant(Etudiant etudiant){
        return etudiantRepository.save(etudiant);
    }

    public List<Etudiant> listerEtudiants(){
        return etudiantRepository.findAll();
    }

    public Etudiant getEtudiant(Long id){
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé"));
    }

    public Etudiant modifierEtudiant(Long id, Etudiant data){

        Etudiant e = getEtudiant(id);

        e.setNom(data.getNom());
        e.setPrenom(data.getPrenom());
        e.setEmail(data.getEmail());
        e.setTelephone(data.getTelephone());

        return etudiantRepository.save(e);
    }

    public List<Etudiant> getEtudiantsParClasse(Long classeId){
        return etudiantRepository.findByInscriptionClasseId(classeId);
    }

     public Etudiant getEtudiantParMatricule(String matricule){

        return etudiantRepository.findByMatricule(matricule)
                .orElseThrow(() -> new RuntimeException("Etudiant introuvable"));
    }
    
    public void supprimerEtudiant(Long id){
        etudiantRepository.deleteById(id);
    }
}
