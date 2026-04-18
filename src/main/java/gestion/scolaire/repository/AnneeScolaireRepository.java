package gestion.scolaire.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import gestion.scolaire.model.AnneeScolaire;

public interface AnneeScolaireRepository extends JpaRepository<AnneeScolaire, Long> {
    Optional<AnneeScolaire> findByLibelle(String libelle);
    Optional<AnneeScolaire> findByActiveTrue();

    @Modifying
@Query("UPDATE AnneeScolaire a SET a.active = false")
void desactiverToutes();
}
