package gestion.scolaire.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import gestion.scolaire.model.AnneeScolaire;
import gestion.scolaire.repository.AnneeScolaireRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnneeScolaireService {

    @Autowired
    private AnneeScolaireRepository anneeScolaireRepository;

    // Créer une année scolaire
    @Transactional
    public AnneeScolaire creerAnnee(AnneeScolaire annee) {

        // 🔴 Désactiver toutes les années existantes
        anneeScolaireRepository.desactiverToutes();

        // 🟢 Activer la nouvelle
        annee.setActive(true);
       annee.setDateAjout(LocalDate.now());

        return anneeScolaireRepository.save(annee);
    }

    // Modifier une année scolaire
    public AnneeScolaire modifierAnnee(Long id, AnneeScolaire data) {

        AnneeScolaire annee = anneeScolaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Année scolaire introuvable"));

        annee.setLibelle(data.getLibelle());
        annee.setDateDebut(data.getDateDebut());
        annee.setDateFin(data.getDateFin());

        if (data.isActive()) {
            desactiverToutesLesAnnees();
            annee.setActive(true);
        }

        return anneeScolaireRepository.save(annee);
    }

    // Supprimer
    public void supprimerAnnee(Long id) {
        anneeScolaireRepository.deleteById(id);
    }

    // Trouver par ID
    public AnneeScolaire getAnneeById(Long id) {
        return anneeScolaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Année scolaire introuvable"));
    }

    // Lister toutes les années
   public List<AnneeScolaire> getAllAnnees() {
    return anneeScolaireRepository.findAll(
        Sort.by(Sort.Direction.DESC, "dateAjout")
    );
}

    // Récupérer l'année active
    public AnneeScolaire getAnneeActive() {
        return anneeScolaireRepository.findByActiveTrue()
                .orElseThrow(() -> new RuntimeException("Aucune année scolaire active"));
    }

    // Activer une année scolaire
    public AnneeScolaire activerAnnee(Long id) {

        desactiverToutesLesAnnees();

        AnneeScolaire annee = anneeScolaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Année scolaire introuvable"));

        annee.setActive(true);

        return anneeScolaireRepository.save(annee);
    }

    // Desactiver une année scolaire
    public AnneeScolaire desactiverAnnee(Long id) {

        AnneeScolaire annee = anneeScolaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Année scolaire introuvable"));

        annee.setActive(false);

        return anneeScolaireRepository.save(annee);
    }

    // Désactiver toutes les années
    private void desactiverToutesLesAnnees() {

        List<AnneeScolaire> annees = anneeScolaireRepository.findAll();

        for (AnneeScolaire a : annees) {
            a.setActive(false);
        }

        anneeScolaireRepository.saveAll(annees);
    }

}