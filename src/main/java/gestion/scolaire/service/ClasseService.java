package gestion.scolaire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.scolaire.model.AnneeScolaire;
import gestion.scolaire.model.Classe;
import gestion.scolaire.model.Etudiant;
import gestion.scolaire.model.Filiere;
import gestion.scolaire.model.Inscription;
import gestion.scolaire.model.Niveau;
import gestion.scolaire.repository.AnneeScolaireRepository;
import gestion.scolaire.repository.ClasseRepository;
import gestion.scolaire.repository.FiliereRepository;
import gestion.scolaire.repository.InscriptionRepository;
import gestion.scolaire.repository.NiveauRepository;

@Service
public class ClasseService {

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private NiveauRepository niveauRepository;

    @Autowired
    private FiliereRepository filiereRepository;

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private AnneeScolaireRepository anneeScolaireRepository;


    // 1️⃣ Créer une classe
    public Classe creerClasse(String nom, Long niveauId, Long filiereId){

        Filiere filiere = filiereRepository.findById(filiereId)
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));
        Niveau niveau = niveauRepository.findById(niveauId)
                .orElseThrow(() -> new RuntimeException("Niveau introuvable"));

        Classe classe = new Classe();
        classe.setNom(nom);
        classe.setNiveau(niveau);
        classe.setFiliere(filiere);

        return classeRepository.save(classe);
    }


    // 2️⃣ Modifier une classe
    public Classe modifierClasse(Long id, String nom, Long niveauId, Long filiereId){

        Classe classe = classeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe introuvable"));

                 Niveau niveau = niveauRepository.findById(niveauId)
                .orElseThrow(() -> new RuntimeException("Niveau introuvable"));

        if(nom != null){
            classe.setNom(nom);
        }

        if(niveau != null){
            classe.setNiveau(niveau);
        }

        if(filiereId != null){
            Filiere filiere = filiereRepository.findById(filiereId)
                    .orElseThrow(() -> new RuntimeException("Filière introuvable"));

            classe.setFiliere(filiere);
        }

        return classeRepository.save(classe);
    }


    // 3️⃣ Supprimer une classe
    public void supprimerClasse(Long id){
        classeRepository.deleteById(id);
    }


    // 4️⃣ Trouver une classe par ID
    public Classe getClasseById(Long id){
        return classeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe introuvable"));
    }


    // 5️⃣ Lister toutes les classes
    public List<Classe> getAllClasses(){
        return classeRepository.findAll();
    }


    // 6️⃣ Classes par filière
    public List<Classe> getClassesParFiliere(Long filiereId){

        Filiere filiere = filiereRepository.findById(filiereId)
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));

        return classeRepository.findByFiliere(filiere);
    }


    // 7️⃣ Etudiants d'une classe pour une année scolaire
    public List<Etudiant> getEtudiantsParClasseEtAnnee(Long classeId, Long anneeId){

        Classe classe = classeRepository.findById(classeId)
                .orElseThrow(() -> new RuntimeException("Classe introuvable"));

        AnneeScolaire annee = anneeScolaireRepository.findById(anneeId)
                .orElseThrow(() -> new RuntimeException("Année scolaire introuvable"));

        List<Inscription> inscriptions =
                inscriptionRepository.findByClasseAndAnneeScolaire(classe, annee);

        return inscriptions.stream()
                .map(Inscription::getEtudiant)
                .toList();
    }

}
