package gestion.scolaire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.scolaire.model.Affectation;
import gestion.scolaire.model.Classe;
import gestion.scolaire.model.Enseignant;
import gestion.scolaire.model.Matiere;
import gestion.scolaire.repository.AffectationRepository;
import gestion.scolaire.repository.ClasseRepository;
import gestion.scolaire.repository.EnseignantRepository;
import gestion.scolaire.repository.MatiereRepository;

@Service
public class AffectationService {

    @Autowired
    private AffectationRepository affectationRepository;

    @Autowired
    private EnseignantRepository enseignantRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private MatiereRepository matiereRepository;

    // 1️⃣ Ajouter une affectation
    public Affectation ajouterAffectation(Long enseignantId, Long matiereId, Long classeId){

        Enseignant enseignant = enseignantRepository.findById(enseignantId)
                .orElseThrow(() -> new RuntimeException("Enseignant introuvable"));
        Matiere matiere = matiereRepository.findById(matiereId)
                .orElseThrow(() -> new RuntimeException("Matière introuvable"));
        Classe classe = classeRepository.findById(classeId)
                .orElseThrow(() -> new RuntimeException("Classe introuvable"));

        Affectation affectation = new Affectation();
        affectation.setEnseignant(enseignant);
        affectation.setMatiere(matiere);
        affectation.setClasse(classe);

        return affectationRepository.save(affectation);
    }

    // 2️⃣ Modifier une affectation
    public Affectation modifierAffectation(Long affectationId,
                                           Long enseignantId,
                                           Long matiereId,
                                           Long classeId){

        Affectation affectation = affectationRepository.findById(affectationId)
                .orElseThrow(() -> new RuntimeException("Affectation introuvable"));

        if(enseignantId != null){
            Enseignant enseignant = enseignantRepository.findById(enseignantId)
                    .orElseThrow(() -> new RuntimeException("Enseignant introuvable"));
            affectation.setEnseignant(enseignant);
        }

        if(matiereId != null){
            Matiere matiere = matiereRepository.findById(matiereId)
                    .orElseThrow(() -> new RuntimeException("Matière introuvable"));
            affectation.setMatiere(matiere);
        }

        if(classeId != null){
            Classe classe = classeRepository.findById(classeId)
                    .orElseThrow(() -> new RuntimeException("Classe introuvable"));
            affectation.setClasse(classe);
        }

        return affectationRepository.save(affectation);
    }

    // 3️⃣ Supprimer une affectation
    public void supprimerAffectation(Long affectationId){
        affectationRepository.deleteById(affectationId);
    }

    // 4️⃣ Récupérer par enseignant
    public List<Affectation> getAffectationsParEnseignant(Long enseignantId){
        Enseignant enseignant = enseignantRepository.findById(enseignantId)
                .orElseThrow(() -> new RuntimeException("Enseignant introuvable"));
        return affectationRepository.findByEnseignant(enseignant);
    }

    // 5️⃣ Récupérer par classe
    public List<Affectation> getAffectationsParClasse(Long classeId){
        Classe classe = classeRepository.findById(classeId)
                .orElseThrow(() -> new RuntimeException("Classe introuvable"));
        return affectationRepository.findByClasse(classe);
    }

    // 6️⃣ Récupérer par matière
    public List<Affectation> getAffectationsParMatiere(Long matiereId){
        Matiere matiere = matiereRepository.findById(matiereId)
                .orElseThrow(() -> new RuntimeException("Matière introuvable"));
        return affectationRepository.findByMatiere(matiere);
    }

    // 7️⃣ Récupérer par classe et année scolaire
    // public List<Affectation> getAffectationsParClasseEtAnnee(Long classeId, Long anneeId){
    //     return affectationRepository.findByClasseEtAnnee(classeId, anneeId);
    // }

    // 8️⃣ Récupérer par ID
    public Affectation getAffectationById(Long affectationId){
        return affectationRepository.findById(affectationId)
                .orElseThrow(() -> new RuntimeException("Affectation introuvable"));
    }
}