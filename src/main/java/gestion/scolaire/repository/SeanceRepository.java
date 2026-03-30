package gestion.scolaire.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestion.scolaire.model.Seance;
import gestion.scolaire.model.StatutSeance;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {

    // Séances par affectation
    List<Seance> findByAffectationId(Long affectationId);

    // Séances par date
    List<Seance> findByDate(LocalDate date);

    // Séances par affectation et date
    List<Seance> findByAffectationIdAndDate(Long affectationId, LocalDate date);

    // Séances en cours
    List<Seance> findByStatut(StatutSeance statut);
}
