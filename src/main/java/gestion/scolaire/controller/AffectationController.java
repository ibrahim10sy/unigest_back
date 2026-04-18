package gestion.scolaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gestion.scolaire.dto.AffectationDTO;
import gestion.scolaire.model.Affectation;
import gestion.scolaire.model.Matiere;
import gestion.scolaire.service.AffectationService;

@RestController
@RequestMapping("/api/affectations")
public class AffectationController {

    @Autowired
    private AffectationService affectationService;


    // 1️⃣ Ajouter une affectation
    @PostMapping
    public ResponseEntity<Affectation> ajouterAffectation(
            @RequestParam Long enseignantId,
            @RequestParam  List<Long> matiereIds,
            @RequestParam Long classeId){

        Affectation affectation =
                affectationService.ajouterAffectation(enseignantId, matiereIds, classeId);

        return ResponseEntity.ok(affectation);
    }


    @GetMapping
    public ResponseEntity<List<Affectation>> getAllMatieres(){

        return ResponseEntity.ok(
                affectationService.getAll()
        );
    }

    // 2️⃣ Modifier une affectation
    @PutMapping("/{id}")
    public ResponseEntity<Affectation> modifierAffectation(
            @PathVariable Long id,
            @RequestParam(required = false) Long enseignantId,
            @RequestParam(required = false)  List<Long> matiereIds,
            @RequestParam(required = false) Long classeId){

        Affectation affectation =
                affectationService.modifierAffectation(id, enseignantId, matiereIds, classeId);

        return ResponseEntity.ok(affectation);
    }


    // 3️⃣ Supprimer une affectation
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerAffectation(@PathVariable Long id){

        affectationService.supprimerAffectation(id);

        return ResponseEntity.ok("Affectation supprimée avec succès");
    }


    // 4️⃣ Récupérer une affectation par ID
    @GetMapping("/{id}")
    public ResponseEntity<Affectation> getAffectationById(@PathVariable Long id){

        return ResponseEntity.ok(affectationService.getAffectationById(id));
    }

    //  @GetMapping("/{id}/details")
    // public ResponseEntity<AffectationDTO> getAffectationDetailById(@PathVariable Long id) {
    //     return ResponseEntity.ok(affectationService.getAffectationDetailById(id));
    // }

    // 5️⃣ Affectations d'un enseignant
    @GetMapping("/enseignant/{enseignantId}")
    public ResponseEntity<List<Affectation>> getAffectationsParEnseignant(
            @PathVariable Long enseignantId){

        return ResponseEntity.ok(
                affectationService.getAffectationsParEnseignant(enseignantId)
        );
    }


    // 6️⃣ Affectations d'une classe
    @GetMapping("/classe/{classeId}")
    public ResponseEntity<List<Affectation>> getAffectationsParClasse(
            @PathVariable Long classeId){

        return ResponseEntity.ok(
                affectationService.getAffectationsParClasse(classeId)
        );
    }


    // 7️⃣ Affectations d'une matière
    // @GetMapping("/matiere/{matiereId}")
    // public ResponseEntity<List<Affectation>> getAffectationsParMatiere(
    //         @PathVariable Long matiereId){

    //     return ResponseEntity.ok(
    //             affectationService.getAffectationsParMatiere(matiereId)
    //     );
    // }


    // 8️⃣ Affectations d'une classe pour une année scolaire
    // @GetMapping("/classe/{classeId}/annee/{anneeId}")
    // public ResponseEntity<List<Affectation>> getAffectationsParClasseEtAnnee(
    //         @PathVariable Long classeId,
    //         @PathVariable Long anneeId){

    //     return ResponseEntity.ok(
    //             affectationService.getAffectationsParClasseEtAnnee(classeId, anneeId)
    //     );
    // }

}
