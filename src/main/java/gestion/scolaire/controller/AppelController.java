package gestion.scolaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gestion.scolaire.model.Appel;
import gestion.scolaire.model.StatutPresence;
import gestion.scolaire.service.AppelService;

@RestController
@RequestMapping("/api/appels")
public class AppelController {

    @Autowired
    private AppelService appelService;


    // 1️⃣ Faire l'appel
    @PostMapping
    public ResponseEntity<Appel> faireAppel(
            @RequestParam Long seanceId,
            @RequestParam Long etudiantId,
            @RequestParam StatutPresence statut,
            @RequestParam(required = false, defaultValue = "0") int retard,
            @RequestParam(required = false) String motif){

        Appel appel = appelService.faireAppel(seanceId, etudiantId, statut, retard, motif);

        return ResponseEntity.ok(appel);
    }


    // 2️⃣ Appels d'une séance
    @GetMapping("/seance/{seanceId}")
    public ResponseEntity<List<Appel>> getAppelsParSeance(@PathVariable Long seanceId){

        return ResponseEntity.ok(appelService.getAppelsParSeance(seanceId));
    }


    // 3️⃣ Appels d'un étudiant
    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<Appel>> getAppelsParEtudiant(@PathVariable Long etudiantId){

        return ResponseEntity.ok(appelService.getAppelsParEtudiant(etudiantId));
    }


    // 4️⃣ Appel d'un étudiant dans une séance
    @GetMapping("/seance/{seanceId}/etudiant/{etudiantId}")
    public ResponseEntity<Appel> getAppelEtudiantDansSeance(
            @PathVariable Long seanceId,
            @PathVariable Long etudiantId){

        return ResponseEntity.ok(
                appelService.getAppelEtudiantDansSeance(seanceId, etudiantId)
        );
    }


    // 5️⃣ Supprimer un appel
    @DeleteMapping("/{appelId}")
    public ResponseEntity<String> supprimerAppel(@PathVariable Long appelId){

        appelService.supprimerAppel(appelId);

        return ResponseEntity.ok("Appel supprimé avec succès");
    }


    // 6️⃣ Supprimer l'appel d'un étudiant dans une séance
    @DeleteMapping("/seance/{seanceId}/etudiant/{etudiantId}")
    public ResponseEntity<String> supprimerAppelEtudiantSeance(
            @PathVariable Long seanceId,
            @PathVariable Long etudiantId){

        appelService.supprimerAppelEtudiantSeance(seanceId, etudiantId);

        return ResponseEntity.ok("Appel supprimé pour cet étudiant dans la séance");
    }


    // 7️⃣ Supprimer tous les appels d'une séance
    @DeleteMapping("/seance/{seanceId}")
    public ResponseEntity<String> supprimerAppelsSeance(@PathVariable Long seanceId){

        appelService.supprimerAppelsSeance(seanceId);

        return ResponseEntity.ok("Tous les appels de la séance ont été supprimés");
    }

}
