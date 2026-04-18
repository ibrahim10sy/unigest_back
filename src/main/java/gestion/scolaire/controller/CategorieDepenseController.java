package gestion.scolaire.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gestion.scolaire.model.CategorieDepense;
import gestion.scolaire.service.CategorieDepenseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategorieDepenseController {

    private final CategorieDepenseService service;

    // =========================
    // CREATE
    // =========================
    @PostMapping
    public ResponseEntity<CategorieDepense> create(
            @RequestBody CategorieDepense categorie
    ) {
        return ResponseEntity.ok(service.create(categorie));
    }

    // =========================
    // GET ALL
    // =========================
    @GetMapping
    public ResponseEntity<List<CategorieDepense>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // =========================
    // GET BY ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<CategorieDepense> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getById(id));
    }

    // =========================
    // UPDATE
    // =========================
    @PutMapping("/{id}")
    public ResponseEntity<CategorieDepense> update(
            @PathVariable Long id,
            @RequestBody CategorieDepense categorie
    ) {
        return ResponseEntity.ok(service.update(id, categorie));
    }

    // =========================
    // DELETE
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
