package gestion.scolaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.scolaire.model.ClasseMatiere;

import java.util.List;

public interface ClasseMatiereRepository extends JpaRepository<ClasseMatiere, Long> {

    List<ClasseMatiere> findByClasseId(Long classeId);

    boolean existsByClasseIdAndMatiereId(Long classeId, Long matiereId);
}
