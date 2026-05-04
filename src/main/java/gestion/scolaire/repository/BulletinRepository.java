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

    List<Bulletin> findByEtudiantId(Long etudiantId);

    List<Bulletin> findByClasseId(Long classeId);

    List<Bulletin> findByEtudiantIdAndAnneeScolaireId(
            Long etudiantId,
            Long anneeScolaireId
    );

    Optional<Bulletin> findByEtudiantIdAndAnneeScolaireIdAndPeriodeAndTypePeriode(
            Long etudiantId,
            Long anneeScolaireId,
            Integer periode,
            TypePeriode typePeriode
    );

    List<Bulletin> findByClasseIdAndAnneeScolaireId(
            Long classeId,
            Long anneeScolaireId
    );

    List<Bulletin> findByClasseIdAndAnneeScolaireIdAndPeriodeAndTypePeriode(
            Long classeId,
            Long anneeScolaireId,
            Integer periode,
            TypePeriode typePeriode
    );

    List<Bulletin> findByAnneeScolaireIdAndPeriodeAndTypePeriode(
            Long anneeScolaireId,
            Integer periode,
            TypePeriode typePeriode
    );
}
