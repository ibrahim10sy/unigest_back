package gestion.scolaire.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.scolaire.model.AnneeScolaire;

public interface AnneeScolaireRepository extends JpaRepository<AnneeScolaire, Long> {
    Optional<AnneeScolaire> findByLibelle(String libelle);
    Optional<AnneeScolaire> findByActiveTrue();
}
