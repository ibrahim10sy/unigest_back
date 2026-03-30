package gestion.scolaire.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.scolaire.model.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

        List<Etudiant> findByInscriptionClasseId(Long id);
            Optional<Etudiant> findByMatricule(String matricule);

}
