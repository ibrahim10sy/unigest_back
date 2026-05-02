package gestion.scolaire.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gestion.scolaire.model.Bulletin;
import gestion.scolaire.model.TypePeriode;
import gestion.scolaire.service.BulletinService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bulletins")
@RequiredArgsConstructor
public class BulletinController {

    private final BulletinService bulletinService;

    /**
     * Générer un bulletin
     */
    @PostMapping
    public ResponseEntity<Bulletin> genererBulletin(
            @RequestParam Long etudiantId,
            @RequestParam Integer periode,
            @RequestParam TypePeriode typePeriode) {

        return ResponseEntity.ok(
                bulletinService.genererBulletin(
                        etudiantId,
                        periode,
                        typePeriode
                )
        );
    }

    /**
     * Bulletin par id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Bulletin> getBulletin(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                bulletinService.getBulletin(id)
        );
    }

    /**
     * Tous les bulletins d’un étudiant
     */
    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<Bulletin>> getBulletinsEtudiant(
            @PathVariable Long etudiantId) {

        return ResponseEntity.ok(
                bulletinService.getBulletinsEtudiant(etudiantId)
        );
    }

    /**
     * Bulletin d’un étudiant pour une période
     */
    @GetMapping("/etudiant/{etudiantId}/periode")
    public ResponseEntity<Bulletin> getBulletinPeriode(
            @PathVariable Long etudiantId,
            @RequestParam Integer periode,
            @RequestParam TypePeriode typePeriode) {

        return ResponseEntity.ok(
                bulletinService.getBulletinEtudiantPeriode(
                        etudiantId,
                        periode,
                        typePeriode
                )
        );
    }

    /**
     * Supprimer un bulletin
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerBulletin(
            @PathVariable Long id) {

        bulletinService.supprimerBulletin(id);

        return ResponseEntity.ok("Bulletin supprimé");
    }
}