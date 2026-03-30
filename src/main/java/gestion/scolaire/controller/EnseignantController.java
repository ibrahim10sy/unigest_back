package gestion.scolaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import gestion.scolaire.model.Enseignant;
import gestion.scolaire.service.EnseignantService;

@RestController
@RequestMapping("/api/enseignants")
public class EnseignantController {

    @Autowired
    private EnseignantService enseignantService;

    // 1️⃣ Créer un enseignant
    @PostMapping
    public ResponseEntity<Enseignant> creerEnseignant(@RequestBody Enseignant enseignant){
        return ResponseEntity.ok(enseignantService.creerEnseignant(enseignant));
    }

    // 2️⃣ Modifier un enseignant
    @PutMapping("/{id}")
    public ResponseEntity<Enseignant> modifierEnseignant(@PathVariable Long id,
                                                         @RequestBody Enseignant data){
        return ResponseEntity.ok(enseignantService.modifierEnseignant(id, data));
    }

    // 3️⃣ Supprimer un enseignant
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerEnseignant(@PathVariable Long id){
        enseignantService.supprimerEnseignant(id);
        return ResponseEntity.ok("Enseignant supprimé");
    }

    // 4️⃣ Trouver un enseignant par ID
    @GetMapping("/{id}")
    public ResponseEntity<Enseignant> getEnseignantById(@PathVariable Long id){
        return ResponseEntity.ok(enseignantService.getEnseignantById(id));
    }

    // 5️⃣ Lister tous les enseignants
    @GetMapping
    public ResponseEntity<List<Enseignant>> getAllEnseignants(){
        return ResponseEntity.ok(enseignantService.getAllEnseignants());
    }
}
