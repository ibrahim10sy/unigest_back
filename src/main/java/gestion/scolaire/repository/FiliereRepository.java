package gestion.scolaire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestion.scolaire.model.Filiere;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    List<Filiere> findByNomContainingIgnoreCase(String nom);
    List<Filiere> findByActifTrue();
}