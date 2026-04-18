package gestion.scolaire.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gestion.scolaire.dto.AjoutMatieresDTO;
import gestion.scolaire.model.Filiere;
import gestion.scolaire.repository.FiliereRepository;
import gestion.scolaire.service.FiliereService;

@RestController
@RequestMapping("/api/filieres")
public class FiliereController {

    @Autowired
    private FiliereService filiereService;

    @Autowired
    private FiliereRepository filiereRepository;

    // 1️⃣ Ajouter une filière
    @PostMapping
    public ResponseEntity<Filiere> ajouterFiliere(@RequestParam String nom,@RequestParam Long niveauId){
        return ResponseEntity.ok(filiereService.ajouterFiliere(nom,niveauId));
    }

    // 2️⃣ Modifier une filière
    @PutMapping("/{id}")
    public ResponseEntity<Filiere> modifierFiliere(@PathVariable Long id,
                                                   @RequestParam(required = false) String nom,
                                                   @RequestParam(required = false) Boolean actif,
                                                 @RequestParam Long niveauId
                                                ){
        return ResponseEntity.ok(filiereService.modifierFiliere(id, nom, actif,niveauId));
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

    @GetMapping("/par-niveau/{niveauId}")
public List<Filiere> getFilieresParNiveau(@PathVariable Long niveauId) {
    return filiereRepository.findByNiveauId(niveauId);
}


  @PatchMapping("/{id}/actif")
public ResponseEntity<?> updateActifFiliere(
    @PathVariable Long id,
    @RequestBody Map<String, Boolean> body) {

    Filiere filiere = filiereRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Filière introuvable"));

    filiere.setActif(body.get("actif"));
    filiereRepository.save(filiere);

    // ✅ On renvoie une Map qui sera transformée en JSON : {"message": "..."}
    Map<String, String> response = new HashMap<>();
    response.put("message", "Statut filière mis à jour");
    return ResponseEntity.ok(response);
}
}