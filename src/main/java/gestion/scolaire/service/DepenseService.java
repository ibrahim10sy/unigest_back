package gestion.scolaire.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import gestion.scolaire.model.CategorieDepense;
import gestion.scolaire.model.Depense;
import gestion.scolaire.repository.CategorieDepenseRepository;
import gestion.scolaire.repository.DepenseRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepenseService {

    private final DepenseRepository depenseRepository;
    private final CategorieDepenseRepository categorieRepository;

    // =========================
    // CREATE
    // =========================
    public Depense createDepense(Depense depense) {

        if (depense.getCategorieDepense() != null && depense.getCategorieDepense().getId() != null) {

            CategorieDepense categorie = categorieRepository.findById(
                    depense.getCategorieDepense().getId()
            ).orElseThrow(() -> new RuntimeException("Catégorie introuvable"));

            depense.setCategorieDepense(categorie);
        }

        depense.setDateCreation(LocalDate.now());

        return depenseRepository.save(depense);
    }


     // =========================
// UPDATE
// =========================
    public Depense updateDepense(Long id, Depense depenseDetails) {
    // 1. Trouver la dépense existante
    Depense depense = depenseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Dépense non trouvée avec l'id : " + id));

    // 2. Mettre à jour les champs simples
    depense.setLibelle(depenseDetails.getLibelle());
    depense.setMontant(depenseDetails.getMontant());
    depense.setDateDepense(depenseDetails.getDateDepense());
    depense.setDescription(depenseDetails.getDescription());
    depense.setModePaiement(depenseDetails.getModePaiement());

    // 3. Gérer la mise à jour de la catégorie (si fournie)
    if (depenseDetails.getCategorieDepense() != null && depenseDetails.getCategorieDepense().getId() != null) {
        CategorieDepense categorie = categorieRepository.findById(depenseDetails.getCategorieDepense().getId())
                .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));
        depense.setCategorieDepense(categorie);
    }

    // 4. Sauvegarder
    return depenseRepository.save(depense);
}

    // =========================
    // GET ALL
    // =========================
    public List<Depense> getAllDepenses() {
        return depenseRepository.findAll();
    }

    // =========================
    // GET BY ID
    // =========================
    public Depense getDepenseById(Long id) {
        return depenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dépense introuvable"));
    }

    // =========================
    // FILTER BY CATEGORIE
    // =========================
    public List<Depense> getDepensesByCategorie(Long categorieId) {
        return depenseRepository.findByCategorieDepenseId(categorieId);
    }

    // =========================
    // FILTER BY DATE
    // =========================
    public List<Depense> getDepensesByDateRange(LocalDate dateDebut, LocalDate dateFin) {
        return depenseRepository.findByDateDepenseBetween(dateDebut, dateFin);
    }

    // =========================
    // DELETE
    // =========================
    public void deleteDepense(Long id) {
        depenseRepository.deleteById(id);
    }

    // =========================
    // TOTAL
    // =========================
    public Double getTotalDepenses() {
        Double total = depenseRepository.sumMontant();
        return total != null ? total : 0.0;
    }

    public Double getTotalByCategorie(Long categorieId) {
    return depenseRepository.sumMontantByCategorieDepense(categorieId);
}

    // public Double getTotalByCategorie(Long categorieId) {
    //     Double total = depenseRepository.sumMontantByCategorieDepense(categorieId);
    //     return total != null ? total : 0.0;
    // }
}
