package gestion.scolaire.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gestion.scolaire.model.Depense;

@Repository
public interface DepenseRepository extends JpaRepository<Depense, Long> {

    // =========================
    // FILTER BY CATEGORIE
    // =========================
    List<Depense> findByCategorieDepenseId(Long categorieId);

    // =========================
    // FILTER BY DATE RANGE
    // =========================
    List<Depense> findByDateDepenseBetween(LocalDate start, LocalDate end);

    // =========================
    // TOTAL DEPENSES
    // =========================
    @Query("SELECT COALESCE(SUM(d.montant),0) FROM Depense d")
    Double sumMontant();

    // =========================
    // TOTAL PAR CATEGORIE (bonus)
    // =========================
    @Query("SELECT SUM(d.montant) FROM Depense d WHERE d.categorieDepense.id =:categorieId")
    Double sumMontantByCategorieDepense(@Param("categorieId") Long categorieId);
}
