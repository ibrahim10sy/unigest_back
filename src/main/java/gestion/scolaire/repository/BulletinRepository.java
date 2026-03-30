package gestion.scolaire.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.scolaire.model.AnneeScolaire;
import gestion.scolaire.model.Bulletin;
import gestion.scolaire.model.Classe;
import gestion.scolaire.model.Etudiant;

public interface BulletinRepository extends JpaRepository<Bulletin, Long> {

    List<Bulletin> findByEtudiant(Etudiant etudiant);

    List<Bulletin> findByClasseAndAnneeScolaire(Classe classe, AnneeScolaire annee);
     List<Bulletin> findByEtudiantId(Long etudiantId);

    Optional<Bulletin> findByEtudiantIdAndSemestre(Long etudiantId, int semestre);
}
