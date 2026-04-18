package gestion.scolaire.controller;

import gestion.scolaire.model.ModePaiement;
import gestion.scolaire.model.Paiement;
import gestion.scolaire.service.PaiementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paiements")
@Tag(name = "Paiements", description = "Gestion des paiements des étudiants")
public class PaiementController {

    @Autowired
    private PaiementService paiementService;


    @Operation(summary = "Effectuer un paiement",
            description = "Permet d'enregistrer un paiement pour une inscription")
    @ApiResponse(responseCode = "200", description = "Paiement enregistré avec succès")
    @PostMapping
    public ResponseEntity<Paiement> effectuerPaiement(
            @Parameter(description = "ID de l'inscription") @RequestParam Long inscriptionId,
            @Parameter(description = "Montant payé") @RequestParam double montant,
            @Parameter(description = "Mode de paiement") @RequestParam ModePaiement mode){

        return ResponseEntity.ok(
                paiementService.effectuerPaiement(inscriptionId, montant, mode)
        );
    }


    @Operation(summary = "Modifier un paiement")
    @ApiResponse(responseCode = "200", description = "Paiement modifié")
    @PutMapping("/{id}")
    public ResponseEntity<Paiement> modifierPaiement(
            @PathVariable Long id,
            @RequestParam Long inscriptionId,
            @RequestParam double montant,
            @RequestParam ModePaiement mode){

        return ResponseEntity.ok(
                paiementService.modifierPaiement(id,inscriptionId, montant, mode)
        );
    }


    @Operation(summary = "Supprimer un paiement")
    @ApiResponse(responseCode = "200", description = "Paiement supprimé")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerPaiement(@PathVariable Long id){

        paiementService.supprimerPaiement(id);
        return ResponseEntity.ok("Paiement supprimé");
    }


    @Operation(summary = "Calculer le total payé pour une inscription")
    @GetMapping("/total/{inscriptionId}")
    public ResponseEntity<Double> calculerTotalPaye(@PathVariable Long inscriptionId){

        return ResponseEntity.ok(
                paiementService.calculerTotalPaye(inscriptionId)
        );
    }


    @Operation(summary = "Historique des paiements d'un étudiant")
    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<Paiement>> getPaiementsParEtudiant(
            @PathVariable Long etudiantId){

        return ResponseEntity.ok(
                paiementService.getPaiementsParEtudiant(etudiantId)
        );
    }


    @Operation(summary = "Historique des paiements par classe et année")
    @GetMapping("/classe/{classeId}/annee/{anneeId}")
    public ResponseEntity<List<Paiement>> getHistoriquePaiements(
            @PathVariable Long classeId,
            @PathVariable Long anneeId){

        return ResponseEntity.ok(
                paiementService.getHistoriquePaiements(classeId, anneeId)
        );
    }


    @Operation(summary = "Récupérer un paiement par ID")
    @GetMapping("/{id}")
    public ResponseEntity<Paiement> getPaiementById(@PathVariable Long id){

        return ResponseEntity.ok(
                paiementService.getPaiementById(id)
        );
    }


    @Operation(summary = "Historique des paiements d'un étudiant dans une classe et année")
    @GetMapping("/etudiant/{etudiantId}/classe/{classeId}/annee/{anneeId}")
    public ResponseEntity<List<Paiement>> getHistoriquePaiementsEtudiant(
            @PathVariable Long etudiantId,
            @PathVariable Long classeId,
            @PathVariable Long anneeId){

        return ResponseEntity.ok(
                paiementService.getHistoriquePaiementsEtudiant(etudiantId, classeId, anneeId)
        );
    }

}
