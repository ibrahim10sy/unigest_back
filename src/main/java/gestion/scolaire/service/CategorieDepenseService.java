package gestion.scolaire.service;

import java.util.List;

import org.springframework.stereotype.Service;

import gestion.scolaire.model.CategorieDepense;
import gestion.scolaire.repository.CategorieDepenseRepository;
import lombok.*;

@Service
@RequiredArgsConstructor
public class CategorieDepenseService {

    private final CategorieDepenseRepository repository;

    // =========================
    // CREATE
    // =========================
    public CategorieDepense create(CategorieDepense categorie) {

        // éviter les doublons
        repository.findByNom(categorie.getNom())
                .ifPresent(c -> {
                    throw new RuntimeException("Cette catégorie existe déjà");
                });

        return repository.save(categorie);
    }

    // =========================
    // GET ALL
    // =========================
    public List<CategorieDepense> getAll() {
        return repository.findAll();
    }

    // =========================
    // GET BY ID
    // =========================
    public CategorieDepense getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));
    }

    // =========================
    // UPDATE
    // =========================
    public CategorieDepense update(Long id, CategorieDepense categorie) {

        CategorieDepense existing = getById(id);

        existing.setNom(categorie.getNom());
        existing.setDescription(categorie.getDescription());

        return repository.save(existing);
    }

    // =========================
    // DELETE
    // =========================
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
