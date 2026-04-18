package gestion.scolaire.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestion.scolaire.model.CategorieDepense;

@Repository
public interface CategorieDepenseRepository extends JpaRepository<CategorieDepense, Long> {

    // =========================
    // RECHERCHE PAR NOM (optionnel)
    // =========================
    Optional<CategorieDepense> findByNom(String nom);
}