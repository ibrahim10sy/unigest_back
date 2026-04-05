package gestion.scolaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestion.scolaire.model.AnneeScolaire;
import gestion.scolaire.service.AnneeScolaireService;

@RestController
@RequestMapping("/api/annee-scolaire")
public class AnneeScolaireController {

    @Autowired
    private AnneeScolaireService service;

    @GetMapping
    public List<AnneeScolaire> getAll() {
        return service.getAllAnnees();
    }

    @GetMapping("/{id}")
    public AnneeScolaire getById(@PathVariable Long id) {
        return service.getAnneeById(id);
    }

    @GetMapping("/active")
    public AnneeScolaire getActive() {
        return service.getAnneeActive();
    }

    @PostMapping
    public AnneeScolaire create(@RequestBody AnneeScolaire annee) {
        return service.creerAnnee(annee);
    }

    @PutMapping("/{id}")
    public AnneeScolaire update(@PathVariable Long id, @RequestBody AnneeScolaire annee) {
        return service.modifierAnnee(id, annee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.supprimerAnnee(id);
    }

    @PatchMapping("/{id}/activer")
    public AnneeScolaire activer(@PathVariable Long id) {
        return service.activerAnnee(id);
    }
}