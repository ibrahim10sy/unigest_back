package gestion.scolaire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestion.scolaire.model.Affectation;
import gestion.scolaire.model.Etudiant;
import gestion.scolaire.model.Note;
import gestion.scolaire.model.TypePeriode;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByEtudiantId(Long etudiantId);

    List<Note> findByAffectationId(Long affectationId);

    List<Note> findByEtudiantIdAndAnneeScolaireId(
            Long etudiantId,
            Long anneeScolaireId);

    List<Note> findByEtudiantIdAndAnneeScolaireIdAndPeriodeAndTypePeriode(
            Long etudiantId,
            Long anneeScolaireId,
            Integer periode,
            TypePeriode typePeriode);

    List<Note> findByAffectationIdAndAnneeScolaireIdAndPeriodeAndTypePeriode(
            Long affectationId,
            Long anneeScolaireId,
            Integer periode,
            TypePeriode typePeriode);

    List<Note> findByAffectationIdAndAnneeScolaireId(
            Long affectationId,
            Long anneeScolaireId);
}