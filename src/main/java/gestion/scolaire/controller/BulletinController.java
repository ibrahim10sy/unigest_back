package gestion.scolaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gestion.scolaire.model.Bulletin;
import gestion.scolaire.service.BulletinService;

@RestController
@RequestMapping("/api/bulletins")
public class BulletinController {

    @Autowired
    private BulletinService bulletinService;


    // générer bulletin
    @PostMapping
    public ResponseEntity<Bulletin> genererBulletin(
            @RequestParam Long etudiantId,
            @RequestParam int semestre){

        return ResponseEntity.ok(
                bulletinService.genererBulletin(etudiantId, semestre)
        );
    }


    // bulletin par id
    @GetMapping("/{id}")
    public ResponseEntity<Bulletin> getBulletin(@PathVariable Long id){

        return ResponseEntity.ok(bulletinService.getBulletin(id));
    }


    // bulletins d'un étudiant
    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<Bulletin>> getBulletinsEtudiant(
            @PathVariable Long etudiantId){

        return ResponseEntity.ok(
                bulletinService.getBulletinsEtudiant(etudiantId)
        );
    }


    // bulletin d'un semestre
    @GetMapping("/etudiant/{etudiantId}/semestre/{semestre}")
    public ResponseEntity<Bulletin> getBulletinSemestre(
            @PathVariable Long etudiantId,
            @PathVariable int semestre){

        return ResponseEntity.ok(
                bulletinService.getBulletinEtudiantSemestre(etudiantId, semestre)
        );
    }


    // supprimer
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerBulletin(@PathVariable Long id){

        bulletinService.supprimerBulletin(id);

        return ResponseEntity.ok("Bulletin supprimé");
    }

}
