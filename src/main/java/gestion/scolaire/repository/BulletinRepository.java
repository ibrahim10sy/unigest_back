package gestion.scolaire.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestion.scolaire.model.AnneeScolaire;
import gestion.scolaire.model.Bulletin;
import gestion.scolaire.model.Classe;
import gestion.scolaire.model.Etudiant;
import gestion.scolaire.model.TypePeriode;

@Repository
public interface BulletinRepository extends JpaRepository<Bulletin, Long> {

    List<Bulletin> findByEtudiant(Etudiant etudiant);

    List<Bulletin> findByClasseAndAnneeScolaire(
            Classe classe,
            AnneeScolaire annee
    );

    List<Bulletin> findByEtudiantId(Long etudiantId);

    Optional<Bulletin> findByEtudiantIdAndAnneeScolaireIdAndPeriode(
            Long etudiantId,
            Long anneeId,
            Integer periode
    );

    Optional<Bulletin> findByEtudiantIdAndAnneeScolaireIdAndPeriodeAndTypePeriode(
            Long etudiantId,
            Long anneeScolaireId,
            Integer periode,
            TypePeriode typePeriode
    );
}
