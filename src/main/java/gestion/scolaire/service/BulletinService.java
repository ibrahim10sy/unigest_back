package gestion.scolaire.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gestion.scolaire.model.AnneeScolaire;
import gestion.scolaire.model.Bulletin;
import gestion.scolaire.model.Classe;
import gestion.scolaire.model.Etudiant;
import gestion.scolaire.model.Inscription;
import gestion.scolaire.model.TypePeriode;
import gestion.scolaire.repository.BulletinRepository;
import gestion.scolaire.repository.EtudiantRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BulletinService {

    private final NoteService noteService;
    private final BulletinRepository bulletinRepository;
    private final EtudiantRepository etudiantRepository;
    private final AnneeScolaireService anneeScolaireService;

    /**
     * Générer un bulletin
     */
    public Bulletin genererBulletin(
            Long etudiantId,
            Integer periode,
            TypePeriode typePeriode) {

        // Vérifier étudiant
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable"));

        // Année scolaire active
        AnneeScolaire anneeActive = anneeScolaireService.getAnneeActive();

        // Récupérer inscription active de l'étudiant
        Inscription inscriptionActive = etudiant.getInscription()
                .stream()
                .filter(inscription -> inscription.getAnneeScolaire().getId().equals(anneeActive.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Aucune inscription active trouvée"));

        // Vérifier si bulletin existe déjà
        Optional<Bulletin> bulletinExistant = bulletinRepository
                .findByEtudiantIdAndAnneeScolaireIdAndPeriodeAndTypePeriode(
                        etudiantId,
                        anneeActive.getId(),
                        periode,
                        typePeriode);

        if (bulletinExistant.isPresent()) {
            throw new RuntimeException("Le bulletin existe déjà pour cette période");
        }

        // Calcul moyenne
        double moyenne = noteService.calculerMoyenneEtudiant(
                etudiantId,
                periode,
                typePeriode);

        // Création bulletin
        Bulletin bulletin = new Bulletin();
        bulletin.setEtudiant(etudiant);
        bulletin.setClasse(inscriptionActive.getClasse());
        bulletin.setAnneeScolaire(anneeActive);
        bulletin.setPeriode(periode);
        bulletin.setTypePeriode(typePeriode);
        bulletin.setMoyenneGenerale(moyenne);
        bulletin.setDateGeneration(LocalDate.now());

        return bulletinRepository.save(bulletin);
    }

    /**
     * Récupérer un bulletin par ID
     */
    @Transactional(readOnly = true)
    public Bulletin getBulletin(Long bulletinId) {
        return bulletinRepository.findById(bulletinId)
                .orElseThrow(() -> new RuntimeException("Bulletin introuvable"));
    }

    /**
     * Tous les bulletins d’un étudiant
     */
    @Transactional(readOnly = true)
    public List<Bulletin> getBulletinsEtudiant(Long etudiantId) {
        return bulletinRepository.findByEtudiantId(etudiantId);
    }

    /**
     * Bulletin d’un étudiant pour une période précise
     * (année scolaire active)
     */
    @Transactional(readOnly = true)
    public Bulletin getBulletinEtudiantPeriode(
            Long etudiantId,
            Integer periode,
            TypePeriode typePeriode) {

        AnneeScolaire anneeActive = anneeScolaireService.getAnneeActive();

        return bulletinRepository
                .findByEtudiantIdAndAnneeScolaireIdAndPeriodeAndTypePeriode(
                        etudiantId,
                        anneeActive.getId(),
                        periode,
                        typePeriode)
                .orElseThrow(() -> new RuntimeException("Bulletin introuvable"));
    }

    /**
     * Bulletins d’une classe pour l’année active
     */
    @Transactional(readOnly = true)
    public List<Bulletin> getBulletinsClasse(Long classeId) {

        AnneeScolaire anneeActive = anneeScolaireService.getAnneeActive();

        Classe classe = new Classe();
        classe.setId(classeId);

        return bulletinRepository.findByClasseAndAnneeScolaire(
                classe,
                anneeActive);
    }

    /**
     * Régénérer un bulletin
     */
    public Bulletin regenererBulletin(
            Long etudiantId,
            Integer periode,
            TypePeriode typePeriode) {

        Bulletin bulletin = getBulletinEtudiantPeriode(
                etudiantId,
                periode,
                typePeriode);

        double nouvelleMoyenne = noteService.calculerMoyenneEtudiant(
                etudiantId,
                periode,
                typePeriode);

        bulletin.setMoyenneGenerale(nouvelleMoyenne);
        bulletin.setDateGeneration(LocalDate.now());

        return bulletinRepository.save(bulletin);
    }

    /**
     * Supprimer bulletin
     */
    public void supprimerBulletin(Long bulletinId) {

        Bulletin bulletin = bulletinRepository.findById(bulletinId)
                .orElseThrow(() -> new RuntimeException("Bulletin introuvable"));

        bulletinRepository.delete(bulletin);
    }
}