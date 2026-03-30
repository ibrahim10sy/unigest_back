package gestion.scolaire.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.scolaire.model.AnneeScolaire;
import gestion.scolaire.model.Classe;
import gestion.scolaire.model.Etudiant;
import gestion.scolaire.model.Inscription;
import gestion.scolaire.repository.AnneeScolaireRepository;
import gestion.scolaire.repository.ClasseRepository;
import gestion.scolaire.repository.EtudiantRepository;
import gestion.scolaire.repository.InscriptionRepository;

@Service
public class InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private AnneeScolaireRepository anneeRepository;

    public Inscription inscrireEtudiant(Long etudiantId,
            Long classeId,
            Long anneeId,
            double montant) {

        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElseThrow();
        Classe classe = classeRepository.findById(classeId).orElseThrow();
        AnneeScolaire annee = anneeRepository.findById(anneeId).orElseThrow();

        Inscription inscription = new Inscription();

        inscription.setEtudiant(etudiant);
        inscription.setClasse(classe);
        inscription.setAnneeScolaire(annee);
        inscription.setDateInscription(LocalDate.now());
        inscription.setMontantTotal(montant);

        return inscriptionRepository.save(inscription);
    }

    public List<Etudiant> getEtudiantsParClasseEtAnnee(Long classeId, Long anneeId) {

        Classe classe = classeRepository.findById(classeId)
                .orElseThrow(() -> new RuntimeException("Classe introuvable"));

        AnneeScolaire annee = anneeRepository.findById(anneeId)
                .orElseThrow(() -> new RuntimeException("Année introuvable"));

        List<Inscription> inscriptions = inscriptionRepository.findByClasseAndAnneeScolaire(classe, annee);

        return inscriptions.stream()
                .map(Inscription::getEtudiant)
                .toList();
    }

    public List<Etudiant> getEtudiantsParClasse(Long classeId) {

        List<Inscription> inscriptions = inscriptionRepository.findByClasseId(classeId);

        return inscriptions.stream()
                .map(Inscription::getEtudiant)
                .toList();
    }

    public Inscription getInscription(Long id) {

        return inscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscription introuvable"));
    }

    public Inscription modifierInscription(Long id,
            Long classeId,
            Long anneeId) {

        Inscription inscription = inscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscription introuvable"));

        Classe classe = classeRepository.findById(classeId)
                .orElseThrow(() -> new RuntimeException("Classe introuvable"));

        AnneeScolaire annee = anneeRepository.findById(anneeId)
                .orElseThrow(() -> new RuntimeException("Année introuvable"));

        inscription.setClasse(classe);
        inscription.setAnneeScolaire(annee);

        return inscriptionRepository.save(inscription);
    }

    public void supprimerInscription(Long id){

    Inscription inscription = inscriptionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Inscription introuvable"));

    inscriptionRepository.delete(inscription);
}
}
