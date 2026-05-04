package gestion.scolaire.controller;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        Note note = noteService.ajouterNote(
                etudiantId,
                affectationId,
                valeur,
                type,
                periode,
                typePeriode
        );

        return new ResponseEntity<>(note, HttpStatus.CREATED);
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
    public ResponseEntity<Void> supprimerNote(@PathVariable Long id) {
        noteService.supprimerNote(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Récupérer une note par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
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
     * Notes par affectation (année active)
     */
    @GetMapping("/affectation/{affectationId}")
    public ResponseEntity<List<Note>> getNotesParAffectation(
            @PathVariable Long affectationId) {

        return ResponseEntity.ok(
                noteService.getNotesParAffectation(affectationId)
        );
    }

    /**
     * Notes par affectation et période (année active)
     */
    @GetMapping("/affectation/{affectationId}/periode")
    public ResponseEntity<List<Note>> getNotesParAffectationEtPeriode(
            @PathVariable Long affectationId,
            @RequestParam Integer periode,
            @RequestParam TypePeriode typePeriode) {

        return ResponseEntity.ok(
                noteService.getNotesParAffectationEtPeriode(
                        affectationId,
                        periode,
                        typePeriode
                )
        );
    }

    /**
     * Notes par période et année scolaire
     */
    @GetMapping("/periode")
    public ResponseEntity<List<Note>> getNotesParPeriode(
            @RequestParam Long anneeScolaireId,
            @RequestParam Integer periode,
            @RequestParam TypePeriode typePeriode) {

        return ResponseEntity.ok(
                noteService.getNotesParPeriode(
                        anneeScolaireId,
                        periode,
                        typePeriode
                )
        );
    }

    /**
     * Notes par période (année active)
     */
    @GetMapping("/periode/active")
    public ResponseEntity<List<Note>> getNotesParPeriodeActive(
            @RequestParam Integer periode,
            @RequestParam TypePeriode typePeriode) {

        return ResponseEntity.ok(
                noteService.getNotesParPeriodeActive(
                        periode,
                        typePeriode
                )
        );
    }

    /**
     * Moyenne d’un étudiant par période
     */
    @GetMapping("/etudiant/{etudiantId}/moyenne")
    public ResponseEntity<Double> calculerMoyenneEtudiant(
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