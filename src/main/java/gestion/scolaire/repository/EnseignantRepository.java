package gestion.scolaire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.scolaire.model.Enseignant;

public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
    List<Enseignant> findByNomContainingIgnoreCase(String nom);
}