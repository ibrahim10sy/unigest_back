package gestion.scolaire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.scolaire.model.Matiere;

public interface MatiereRepository extends JpaRepository<Matiere, Long> {
    List<Matiere> findByNomContainingIgnoreCase(String nom);
}
