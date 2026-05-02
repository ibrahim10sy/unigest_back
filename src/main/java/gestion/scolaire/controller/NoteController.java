package gestion.scolaire.controller;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import gestion.scolaire.model.Note;
import gestion.scolaire.model.TypeNote;
import gestion.scolaire.model.TypePeriode;
import gestion.scolaire.service.NoteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    /**
     * Ajouter une note
     */
    @PostMapping
    public ResponseEntity<Note> ajouterNote(
            @RequestParam Long etudiantId,
            @RequestParam Long affectationId,
            @RequestParam double valeur,
            @RequestParam TypeNote type,
            @RequestParam Integer periode,
            @RequestParam TypePeriode typePeriode) {

        return ResponseEntity.ok(
                noteService.ajouterNote(
                        etudiantId,
                        affectationId,
                        valeur,
                        type,
                        periode,
                        typePeriode
                )
        );
    }

    /**
     * Modifier une note
     */
    @PutMapping("/{id}")
    public ResponseEntity<Note> modifierNote(
            @PathVariable Long id,
            @RequestParam double valeur,
            @RequestParam TypeNote type) {

        return ResponseEntity.ok(
                noteService.modifierNote(id, valeur, type)
        );
    }

    /**
     * Supprimer une note
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerNote(
            @PathVariable Long id) {

        noteService.supprimerNote(id);

        return ResponseEntity.ok("Note supprimée");
    }

    /**
     * Récupérer une note
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNote(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                noteService.getNoteById(id)
        );
    }

    /**
     * Notes d’un étudiant (année active)
     */
    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<Note>> getNotesEtudiant(
            @PathVariable Long etudiantId) {

        return ResponseEntity.ok(
                noteService.getNotesEtudiant(etudiantId)
        );
    }

    /**
     * Notes d’une affectation
     */
    @GetMapping("/affectation/{affectationId}")
    public ResponseEntity<List<Note>> getNotesParAffectation(
            @PathVariable Long affectationId) {

        return ResponseEntity.ok(
                noteService.getNotesParAffectation(affectationId)
        );
    }

    /**
     * Notes d’un étudiant par période
     */
    @GetMapping("/etudiant/{etudiantId}/periode")
    public ResponseEntity<List<Note>> getNotesEtudiantPeriode(
            @PathVariable Long etudiantId,
            @RequestParam Integer periode,
            @RequestParam TypePeriode typePeriode) {

        return ResponseEntity.ok(
                noteService.getNotesEtudiantPeriode(
                        etudiantId,
                        periode,
                        typePeriode
                )
        );
    }

    /**
     * Moyenne d’un étudiant par période
     */
    @GetMapping("/etudiant/{etudiantId}/moyenne")
    public ResponseEntity<Double> calculerMoyenne(
            @PathVariable Long etudiantId,
            @RequestParam Integer periode,
            @RequestParam TypePeriode typePeriode) {

        return ResponseEntity.ok(
                noteService.calculerMoyenneEtudiant(
                        etudiantId,
                        periode,
                        typePeriode
                )
        );
    }
}