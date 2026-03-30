package gestion.scolaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import gestion.scolaire.model.Affectation;
import gestion.scolaire.model.Etudiant;
import gestion.scolaire.model.Role;
import gestion.scolaire.model.Utilisateur;
import gestion.scolaire.service.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/utilisateurs")
@Tag(name = "Utilisateurs", description = "Gestion des utilisateurs du système")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;


    @Operation(summary = "Ajouter un utilisateur")
    @ApiResponse(responseCode = "200", description = "Utilisateur créé avec succès")
    @PostMapping
    public ResponseEntity<Utilisateur> ajouterUtilisateur(@RequestBody Utilisateur user){

        return ResponseEntity.ok(utilisateurService.ajouterUtilisateur(user));
    }


    @Operation(summary = "Modifier un utilisateur")
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> modifierUtilisateur(
            @PathVariable Long id,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String prenom,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String telephone,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) Boolean actif){

        return ResponseEntity.ok(
                utilisateurService.modifierUtilisateur(
                        id, nom, prenom, email, telephone, password, actif
                )
        );
    }


    @Operation(summary = "Supprimer un utilisateur")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerUtilisateur(@PathVariable Long id){

        utilisateurService.supprimerUtilisateur(id);

        return ResponseEntity.ok("Utilisateur supprimé");
    }


    @Operation(summary = "Récupérer un utilisateur par ID")
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id){

        return ResponseEntity.ok(
                utilisateurService.getUtilisateurById(id)
        );
    }


    @Operation(summary = "Récupérer les utilisateurs par rôle")
    @GetMapping("/role/{role}")
    public ResponseEntity<List<Utilisateur>> getUtilisateursParRole(
            @PathVariable Role role){

        return ResponseEntity.ok(
                utilisateurService.getUtilisateursParRole(role)
        );
    }


    @Operation(summary = "Récupérer un utilisateur par email")
    @GetMapping("/email")
    public ResponseEntity<Utilisateur> getUtilisateurByEmail(
            @RequestParam String email){

        return ResponseEntity.ok(
                utilisateurService.getUtilisateurByEmail(email)
        );
    }


    @Operation(summary = "Récupérer les enfants d'un parent")
    @GetMapping("/parent/{parentId}/enfants")
    public ResponseEntity<List<Etudiant>> getEnfants(@PathVariable Long parentId){

        return ResponseEntity.ok(
                utilisateurService.getEnfants(parentId)
        );
    }


    @Operation(summary = "Récupérer les affectations d'un enseignant")
    @GetMapping("/enseignant/{enseignantId}/affectations")
    public ResponseEntity<List<Affectation>> getAffectations(
            @PathVariable Long enseignantId){

        return ResponseEntity.ok(
                utilisateurService.getAffectations(enseignantId)
        );
    }

}
