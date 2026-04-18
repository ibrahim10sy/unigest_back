package gestion.scolaire.controller;

import gestion.scolaire.model.Seance;
import gestion.scolaire.service.SeanceService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/seances")
@Tag(name = "Séances", description = "Gestion des séances de cours")
public class SeanceController {

    @Autowired
    private SeanceService seanceService;


    @Operation(summary = "Démarrer une séance",
            description = "Crée une nouvelle séance pour une affectation")
    @PostMapping("/demarrer")
    public ResponseEntity<Seance> demarrerSeance(
            @Parameter(description = "ID de l'affectation")
            @RequestParam Long affectationId,
            @RequestParam String matiere
        ){

        return ResponseEntity.ok(
                seanceService.demarrerSeance(affectationId, matiere)
        );
    }


    @Operation(summary = "Terminer une séance")
    @PutMapping("/{seanceId}/terminer")
    public ResponseEntity<Seance> terminerSeance(
            @PathVariable Long seanceId){

        return ResponseEntity.ok(
                seanceService.terminerSeance(seanceId)
        );
    }


    @Operation(summary = "Récupérer les séances par date")
    @GetMapping("/date")
    public ResponseEntity<List<Seance>> getSeancesParDate(
            @RequestParam LocalDate date){

        return ResponseEntity.ok(
                seanceService.getSeancesParDate(date)
        );
    }

    @Operation(summary = "Récupérer les séances par affectation")
    @GetMapping("/affectation/{affectationId}")
    public ResponseEntity<List<Seance>> getSeancesParAffectation(
            @PathVariable Long affectationId){

        return ResponseEntity.ok(
                seanceService.getSeancesParAffectation(affectationId)
        );
    }


    @Operation(summary = "Récupérer les séances par affectation et date")
    @GetMapping("/affectation/{affectationId}/date")
    public ResponseEntity<List<Seance>> getSeancesParAffectationEtDate(
            @PathVariable Long affectationId,
            @RequestParam LocalDate date){

        return ResponseEntity.ok(
                seanceService.getSeancesParAffectationEtDate(affectationId, date)
        );
    }


    @Operation(summary = "Récupérer toutes les séances en cours")
    @GetMapping("/encours")
    public ResponseEntity<List<Seance>> getSeancesEnCours(){

        return ResponseEntity.ok(
                seanceService.getSeancesEnCours()
        );
    }
   
    @Operation(summary = "Récupérer toutes les séances")
    @GetMapping
    public ResponseEntity<List<Seance>> getSeances(){

        return ResponseEntity.ok(
                seanceService.getSeances()
        );
    }

}
