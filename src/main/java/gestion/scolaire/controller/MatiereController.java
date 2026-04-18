package gestion.scolaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import gestion.scolaire.model.Matiere;
import gestion.scolaire.service.MatiereService;


@RestController
@RequestMapping("/api/matieres")
public class MatiereController {

    @Autowired
    private MatiereService matiereService;

    // 1️⃣ Ajouter une matière
    @PostMapping
    public ResponseEntity<Matiere> ajouterMatiere(
           @RequestBody Matiere matiere){

        return ResponseEntity.ok(
                matiereService.ajouterMatiere(matiere)
        );
    }

    // 2️⃣ Modifier une matière
    @PutMapping("/{id}")
    public ResponseEntity<Matiere> modifierMatiere(
            @PathVariable Long id, @RequestBody Matiere matiere){

        return ResponseEntity.ok(
                matiereService.modifierMatiere(id, matiere)
        );
    }

    // 3️⃣ Supprimer une matière
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerMatiere(@PathVariable Long id){

        matiereService.supprimerMatiere(id);

        return ResponseEntity.ok("Matière supprimée");
    }

    // 4️⃣ Récupérer une matière
    @GetMapping("/{id}")
    public ResponseEntity<Matiere> getMatiereById(@PathVariable Long id){

        return ResponseEntity.ok(
                matiereService.getMatiereById(id)
        );
    }

    // 5️⃣ Lister toutes les matières
    @GetMapping
    public ResponseEntity<List<Matiere>> getAllMatieres(){

        return ResponseEntity.ok(
                matiereService.getAllMatieres()
        );
    }

    // 6️⃣ Rechercher par nom
    @GetMapping("/recherche")
    public ResponseEntity<List<Matiere>> rechercherParNom(
            @RequestParam String nom){

        return ResponseEntity.ok(
                matiereService.rechercherParNom(nom)
        );
    }
}
