package gestion.scolaire.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gestion.scolaire.model.Affectation;
import gestion.scolaire.model.AnneeScolaire;
import gestion.scolaire.model.Etudiant;
import gestion.scolaire.model.Note;
import gestion.scolaire.model.TypeNote;
import gestion.scolaire.model.TypePeriode;
import gestion.scolaire.repository.AffectationRepository;
import gestion.scolaire.repository.EtudiantRepository;
import gestion.scolaire.repository.NoteRepository;
import lombok.*;

@Service
@RequiredArgsConstructor
@Transactional
public class NoteService {

    private final NoteRepository noteRepository;
    private final EtudiantRepository etudiantRepository;
    private final AffectationRepository affectationRepository;
    private final AnneeScolaireService anneeScolaireService;

    /**
     * Ajouter une note
     */
    public Note ajouterNote(
            Long etudiantId,
            Long affectationId,
            double valeur,
            TypeNote type,
            Integer periode,
            TypePeriode typePeriode) {

        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable"));

        Affectation affectation = affectationRepository.findById(affectationId)
                .orElseThrow(() -> new RuntimeException("Affectation introuvable"));

        AnneeScolaire anneeActive = anneeScolaireService.getAnneeActive();

        Note note = new Note();
        note.setEtudiant(etudiant);
        note.setAffectation(affectation);
        note.setAnneeScolaire(anneeActive);
        note.setValeur(valeur);
        note.setType(type);
        note.setPeriode(periode);
        note.setTypePeriode(typePeriode);
        note.setDateEvaluation(LocalDate.now());

        return noteRepository.save(note);
    }

    /**
     * Modifier une note
     */
    public Note modifierNote(
            Long noteId,
            double valeur,
            TypeNote type) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note introuvable"));

        note.setValeur(valeur);
        note.setType(type);

        return noteRepository.save(note);
    }

    /**
     * Supprimer une note
     */
    public void supprimerNote(Long noteId) {
        noteRepository.deleteById(noteId);
    }

    /**
     * Récupérer une note
     */
    public Note getNoteById(Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note introuvable"));
    }

    /**
     * Notes d’un étudiant (année active)
     */
    public List<Note> getNotesEtudiant(Long etudiantId) {
        AnneeScolaire anneeActive = anneeScolaireService.getAnneeActive();

        return noteRepository.findByEtudiantIdAndAnneeScolaireId(
                etudiantId,
                anneeActive.getId()
        );
    }

    /**
     * Notes d’un étudiant par période
     */
    public List<Note> getNotesEtudiantPeriode(
            Long etudiantId,
            Integer periode,
            TypePeriode typePeriode) {

        AnneeScolaire anneeActive = anneeScolaireService.getAnneeActive();

        return noteRepository
                .findByEtudiantIdAndAnneeScolaireIdAndPeriodeAndTypePeriode(
                        etudiantId,
                        anneeActive.getId(),
                        periode,
                        typePeriode
                );
    }


    public List<Note> getNotesParAffectation(Long affectationId) {

    AnneeScolaire anneeActive = anneeScolaireService.getAnneeActive();

    return noteRepository.findByAffectationIdAndAnneeScolaireId(
            affectationId,
            anneeActive.getId()
    );
}

    /**
     * Calcul moyenne étudiant
     */
    public double calculerMoyenneEtudiant(
            Long etudiantId,
            Integer periode,
            TypePeriode typePeriode) {

        List<Note> notes = getNotesEtudiantPeriode(
                etudiantId,
                periode,
                typePeriode
        );

        if (notes.isEmpty()) {
            return 0;
        }

        return notes.stream()
                .mapToDouble(Note::getValeur)
                .average()
                .orElse(0);
    }
}