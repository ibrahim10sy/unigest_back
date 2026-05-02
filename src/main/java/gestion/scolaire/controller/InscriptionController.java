package gestion.scolaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gestion.scolaire.model.Etudiant;
import gestion.scolaire.model.Inscription;
import gestion.scolaire.service.InscriptionService;

@RestController
@RequestMapping("/api/inscriptions")
public class InscriptionController {

        @Autowired
        private InscriptionService inscriptionService;

        // 1️⃣ Inscrire un étudiant
        @PostMapping
        public ResponseEntity<Inscription> inscrireEtudiant(
                        @RequestParam Long etudiantId,
                        @RequestParam Long classeId,
                        @RequestParam Long anneeId,
                        @RequestParam double montantReduction,
                        @RequestParam String motifReduction) {

                return ResponseEntity.ok(
                                inscriptionService.inscrireEtudiant(
                                                etudiantId,
                                                montantReduction,
                                                motifReduction,
                                                classeId,
                                                anneeId));
        }

        // 2️⃣ Récupérer les inscriptions d'un étudiant
        @GetMapping("/etudiant/{id}")
        public ResponseEntity<List<Inscription>> getInscriptionsParEtudiant(@PathVariable Long id) {

                return ResponseEntity.ok(inscriptionService.getAllEtudiant(id));
        }

        @GetMapping("/{classeId}/etudiants")
        public ResponseEntity<List<Etudiant>> getEtudiantsActifsParClasse(
                        @PathVariable Long classeId) {

                return ResponseEntity.ok(
                                inscriptionService.getEtudiantsActifsParClasse(classeId));
        }

        // 2️⃣ Récupérer une inscription
        @GetMapping("/{id}")
        public ResponseEntity<Inscription> getInscription(@PathVariable Long id) {

                return ResponseEntity.ok(inscriptionService.getInscription(id));
        }

        @GetMapping
        public ResponseEntity<List<Inscription>> getAllInscription() {
                return ResponseEntity.ok(inscriptionService.getAll());
        }

        // 3️⃣ Modifier une inscription
        @PutMapping("/{id}")
        public ResponseEntity<Inscription> modifierInscription(
                        @PathVariable Long id,
                        @RequestParam Long classeId,
                        @RequestParam Long anneeId,
                        @RequestParam String motifReduction,
                        @RequestParam double montantReduction) {

                return ResponseEntity.ok(
                                inscriptionService.modifierInscription(

                                                id,
                                                classeId,
                                                anneeId,
                                                motifReduction,
                                                montantReduction));
        }

        // 4️⃣ Supprimer une inscription
        @DeleteMapping("/{id}")
        public ResponseEntity<String> supprimerInscription(@PathVariable Long id) {

                inscriptionService.supprimerInscription(id);

                return ResponseEntity.ok("Inscription supprimée");
        }

        // 5️⃣ Étudiants d'une classe
        @GetMapping("/classe/{classeId}")
        public ResponseEntity<List<Etudiant>> getEtudiantsParClasse(
                        @PathVariable Long classeId) {

                return ResponseEntity.ok(
                                inscriptionService.getEtudiantsParClasse(classeId));
        }

        // 6️⃣ Étudiants d'une classe pour une année
        @GetMapping("/classe/{classeId}/annee/{anneeId}")
        public ResponseEntity<List<Etudiant>> getEtudiantsParClasseEtAnnee(
                        @PathVariable Long classeId,
                        @PathVariable Long anneeId) {

                return ResponseEntity.ok(
                                inscriptionService.getEtudiantsParClasseEtAnnee(
                                                classeId,
                                                anneeId));
        }

}
