package gestion.scolaire.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gestion.scolaire.model.ClasseMatiere;
import gestion.scolaire.service.ClasseMatiereService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/classe-matiere")
@RequiredArgsConstructor
public class ClasseMatiereController {

    private final ClasseMatiereService service;

    // ✅ Ajouter matière à une classe
    @PostMapping
    public ClasseMatiere add(
            @RequestParam Long classeId,
            @RequestParam Long matiereId,
            @RequestParam double coefficient
    ) {
        return service.addMatiereToClasse(classeId, matiereId, coefficient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClasseMatiere> update(
            @PathVariable Long id,
            @RequestBody ClasseMatiere classeMatiere
    ) {
        ClasseMatiere updated = service.update(id, classeMatiere);
        return ResponseEntity.ok(updated);
    }
    
    // ✅ Lister matières d'une classe
    @GetMapping("/classe/{classeId}")
    public List<ClasseMatiere> getByClasse(@PathVariable Long classeId) {
        return service.getMatieresByClasse(classeId);
    }

    // ✅ Supprimer
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
