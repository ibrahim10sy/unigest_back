package gestion.scolaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gestion.scolaire.model.Classe;
import gestion.scolaire.model.Etudiant;
import gestion.scolaire.service.ClasseService;

@RestController
@RequestMapping("/api/classes")
public class ClasseController {

    @Autowired
    private ClasseService classeService;


    // 1️⃣ Créer une classe
    @PostMapping
    public ResponseEntity<Classe> creerClasse(
            @RequestParam String nom,
            @RequestParam Long niveauId,
            @RequestParam Long filiereId) {

        Classe classe = classeService.creerClasse(nom, niveauId, filiereId);
        return ResponseEntity.ok(classe);
    }


    // 2️⃣ Modifier une classe
    @PutMapping("/{id}")
    public ResponseEntity<Classe> modifierClasse(
            @PathVariable Long id,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) Long niveauId,
            @RequestParam(required = false) Long filiereId) {

        Classe classe = classeService.modifierClasse(id, nom, niveauId, filiereId);
        return ResponseEntity.ok(classe);
    }


    // 3️⃣ Supprimer une classe
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerClasse(@PathVariable Long id) {

        classeService.supprimerClasse(id);
        return ResponseEntity.ok("Classe supprimée avec succès");
    }


    // 4️⃣ Récupérer une classe par ID
    @GetMapping("/{id}")
    public ResponseEntity<Classe> getClasseById(@PathVariable Long id) {

        Classe classe = classeService.getClasseById(id);
        return ResponseEntity.ok(classe);
    }


    // 5️⃣ Lister toutes les classes
    @GetMapping
    public ResponseEntity<List<Classe>> getAllClasses() {

        return ResponseEntity.ok(classeService.getAllClasses());
    }


    // 6️⃣ Classes par filière
    @GetMapping("/filiere/{filiereId}")
    public ResponseEntity<List<Classe>> getClassesParFiliere(@PathVariable Long filiereId) {

        return ResponseEntity.ok(classeService.getClassesParFiliere(filiereId));
    }


    // 7️⃣ Étudiants d'une classe pour une année scolaire
    @GetMapping("/{classeId}/annee/{anneeId}/etudiants")
    public ResponseEntity<List<Etudiant>> getEtudiantsParClasseEtAnnee(
            @PathVariable Long classeId,
            @PathVariable Long anneeId) {

        return ResponseEntity.ok(
                classeService.getEtudiantsParClasseEtAnnee(classeId, anneeId)
        );
    }

}
