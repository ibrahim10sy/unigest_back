package gestion.scolaire.controller;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import gestion.scolaire.model.Note;
import gestion.scolaire.model.TypeNote;
import gestion.scolaire.service.NoteService;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;


    // 1️⃣ Ajouter une note
    @PostMapping
    public ResponseEntity<Note> ajouterNote(
            @RequestParam Long etudiantId,
            @RequestParam Long affectationId,
            @RequestParam double valeur,
            @RequestParam TypeNote type,
            @RequestParam int semestre){

        return ResponseEntity.ok(
                noteService.ajouterNote(
                        etudiantId,
                        affectationId,
                        valeur,
                        type,
                        semestre
                )
        );
    }


    // 2️⃣ Modifier une note
    @PutMapping("/{id}")
    public ResponseEntity<Note> modifierNote(
            @PathVariable Long id,
            @RequestParam double valeur,
            @RequestParam TypeNote type){

        return ResponseEntity.ok(
                noteService.modifierNote(id, valeur, type)
        );
    }


    // 3️⃣ Supprimer une note
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerNote(@PathVariable Long id){

        noteService.supprimerNote(id);

        return ResponseEntity.ok("Note supprimée");
    }


    // 4️⃣ Récupérer une note
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNote(@PathVariable Long id){

        return ResponseEntity.ok(
                noteService.getNoteById(id)
        );
    }


    // 5️⃣ Notes d'un étudiant
    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<Note>> getNotesEtudiant(@PathVariable Long etudiantId){

        return ResponseEntity.ok(
                noteService.getNotesEtudiant(etudiantId)
        );
    }


    // 6️⃣ Notes d'une affectation
    @GetMapping("/affectation/{affectationId}")
    public ResponseEntity<List<Note>> getNotesParAffectation(@PathVariable Long affectationId){

        return ResponseEntity.ok(
                noteService.getNotesParAffectation(affectationId)
        );
    }


    // 7️⃣ Notes étudiant par semestre
    @GetMapping("/etudiant/{etudiantId}/semestre/{semestre}")
    public ResponseEntity<List<Note>> getNotesEtudiantSemestre(
            @PathVariable Long etudiantId,
            @PathVariable int semestre){

        return ResponseEntity.ok(
                noteService.getNotesEtudiantSemestre(etudiantId, semestre)
        );
    }


    // 8️⃣ Moyenne étudiant
    @GetMapping("/etudiant/{etudiantId}/moyenne/{semestre}")
    public ResponseEntity<Double> calculerMoyenne(
            @PathVariable Long etudiantId,
            @PathVariable int semestre){

        return ResponseEntity.ok(
                noteService.calculerMoyenneEtudiant(etudiantId, semestre)
        );
    }

}
