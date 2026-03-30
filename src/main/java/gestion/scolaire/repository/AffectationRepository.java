package gestion.scolaire.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gestion.scolaire.model.Affectation;
import gestion.scolaire.model.Classe;
import gestion.scolaire.model.Enseignant;
import gestion.scolaire.model.Matiere;

@Repository
public interface AffectationRepository extends JpaRepository<Affectation, Long> {

    List<Affectation> findByEnseignant(Enseignant enseignant);

    List<Affectation> findByClasse(Classe classe);

    List<Affectation> findByMatiere(Matiere matiere);

    // @Query("SELECT a FROM Affectation a WHERE a.classe.id = :classeId AND a.classe.anneeScolaire.id = :anneeId")
    // List<Affectation> findByClasseEtAnnee(@Param("classeId") Long classeId,
    //                                        @Param("anneeId") Long anneeId);

}
