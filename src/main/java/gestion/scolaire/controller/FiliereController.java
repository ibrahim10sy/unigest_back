package gestion.scolaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import gestion.scolaire.model.Filiere;
import gestion.scolaire.service.FiliereService;

@RestController
@RequestMapping("/api/filieres")
public class FiliereController {

    @Autowired
    private FiliereService filiereService;

    // 1️⃣ Ajouter une filière
    @PostMapping
    public ResponseEntity<Filiere> ajouterFiliere(@RequestParam String nom){
        return ResponseEntity.ok(filiereService.ajouterFiliere(nom));
    }

    // 2️⃣ Modifier une filière
    @PutMapping("/{id}")
    public ResponseEntity<Filiere> modifierFiliere(@PathVariable Long id,
                                                   @RequestParam(required = false) String nom,
                                                   @RequestParam(required = false) Boolean actif){
        return ResponseEntity.ok(filiereService.modifierFiliere(id, nom, actif));
    }

    // 3️⃣ Supprimer une filière
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerFiliere(@PathVariable Long id){
        filiereService.supprimerFiliere(id);
        return ResponseEntity.ok("Filière supprimée");
    }

    // 4️⃣ Récupérer une filière par ID
    @GetMapping("/{id}")
    public ResponseEntity<Filiere> getFiliereById(@PathVariable Long id){
        return ResponseEntity.ok(filiereService.getFiliereById(id));
    }

    // 5️⃣ Lister toutes les filières
    @GetMapping
    public ResponseEntity<List<Filiere>> getAllFilieres(){
        return ResponseEntity.ok(filiereService.getAllFilieres());
    }

    // 6️⃣ Lister toutes les filières actives
    @GetMapping("/actives")
    public ResponseEntity<List<Filiere>> getFilieresActives(){
        return ResponseEntity.ok(filiereService.getFilieresActives());
    }

    // 7️⃣ Rechercher filières par nom
    @GetMapping("/recherche")
    public ResponseEntity<List<Filiere>> rechercherParNom(@RequestParam String nom){
        return ResponseEntity.ok(filiereService.rechercherParNom(nom));
    }
}