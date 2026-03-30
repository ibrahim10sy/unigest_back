package gestion.scolaire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.scolaire.model.AnneeScolaire;
import gestion.scolaire.model.Classe;
import gestion.scolaire.model.Etudiant;
import gestion.scolaire.model.Inscription;

public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
    List<Inscription> findByClasseAndAnneeScolaire(Classe classe, AnneeScolaire anneeScolaire);
    List<Inscription> findByEtudiant(Etudiant etudiant);

        List<Inscription> findByClasseId(Long classeId);

     

    List<Inscription> findByEtudiantId(Long etudiantId);

}
