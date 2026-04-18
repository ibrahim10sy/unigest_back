package gestion.scolaire.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gestion.scolaire.model.Depense;
import gestion.scolaire.service.DepenseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/depenses")
@RequiredArgsConstructor
public class DepenseController {

    private final DepenseService depenseService;

    // =========================
    // CREATE
    // =========================
    @PostMapping
    public ResponseEntity<Depense> create(@RequestBody Depense depense) {
        return ResponseEntity.ok(depenseService.createDepense(depense));
    }

    // =========================
    // GET ALL
    // =========================
    @GetMapping
    public ResponseEntity<List<Depense>> getAll() {
        return ResponseEntity.ok(depenseService.getAllDepenses());
    }

    // =========================
    // GET BY ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<Depense> getById(@PathVariable Long id) {
        return ResponseEntity.ok(depenseService.getDepenseById(id));
    }

    // =========================
    // FILTER BY CATEGORIE
    // =========================
    @GetMapping("/by-categorie")
    public ResponseEntity<List<Depense>> getByCategorie(
            @RequestParam Long categorieId
    ) {
        return ResponseEntity.ok(
                depenseService.getDepensesByCategorie(categorieId)
        );
    }

    // =========================
    // FILTER BY DATE
    // =========================
    @GetMapping("/by-date")
    public ResponseEntity<List<Depense>> getByDate(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateDebut,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFin
    ) {
        return ResponseEntity.ok(
                depenseService.getDepensesByDateRange(dateDebut, dateFin)
        );
    }

    // =========================
    // TOTAL
    // =========================
    @GetMapping("/total")
    public ResponseEntity<Double> getTotal() {
        return ResponseEntity.ok(depenseService.getTotalDepenses());
    }

    @GetMapping("/total/categorie/{categorieId}")
    public ResponseEntity<Double> getTotalByCategorie(@PathVariable Long categorieId) {
        Double total = depenseService.getTotalByCategorie(categorieId);
        return ResponseEntity.ok(total);
    }
    
    // =========================
    // DELETE
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        depenseService.deleteDepense(id);
        return ResponseEntity.ok().build();
    }
}
