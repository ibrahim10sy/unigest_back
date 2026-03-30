package gestion.scolaire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.scolaire.model.Affectation;
import gestion.scolaire.model.Etudiant;
import gestion.scolaire.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByEtudiantAndSemestre(Long etudiantId, int semestre);

    List<Note> findByAffectation(Affectation affectation);
    List<Note> findByEtudiantId(Long etudiantId);

    List<Note> findByAffectationId(Long affectationId);

    List<Note> findByEtudiantIdAndSemestre(Long etudiantId, int semestre);

    List<Note> findByAffectationIdAndSemestre(Long affectationId, int semestre);

    List<Note> findByEtudiantIdAndAffectationIdAndSemestre(Long etudiantId,
                                                           Long affectationId,
                                                           int semestre);
}
