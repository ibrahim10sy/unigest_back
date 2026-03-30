package gestion.scolaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import gestion.scolaire.model.Parent;
import gestion.scolaire.service.ParentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/parents")
@Tag(name = "Parents", description = "Gestion des parents d'étudiants")
public class ParentController {

    @Autowired
    private ParentService parentService;


    @Operation(summary = "Créer un parent",
            description = "Permet d'ajouter un nouveau parent dans le système")
    @ApiResponse(responseCode = "200", description = "Parent créé avec succès")
    @PostMapping
    public ResponseEntity<Parent> creerParent(@RequestBody Parent parent){

        return ResponseEntity.ok(parentService.creerParent(parent));
    }


    @Operation(summary = "Modifier un parent",
            description = "Met à jour les informations d'un parent")
    @PutMapping("/{id}")
    public ResponseEntity<Parent> modifierParent(
            @PathVariable Long id,
            @RequestBody Parent data){

        return ResponseEntity.ok(parentService.modifierParent(id, data));
    }


    @Operation(summary = "Supprimer un parent")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerParent(@PathVariable Long id){

        parentService.supprimerParent(id);
        return ResponseEntity.ok("Parent supprimé");
    }


    @Operation(summary = "Récupérer un parent par ID")
    @GetMapping("/{id}")
    public ResponseEntity<Parent> getParentById(@PathVariable Long id){

        return ResponseEntity.ok(parentService.getParentById(id));
    }


    @Operation(summary = "Lister tous les parents")
    @GetMapping
    public ResponseEntity<List<Parent>> getAllParents(){

        return ResponseEntity.ok(parentService.getAllParents());
    }

}
