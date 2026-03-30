package gestion.scolaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestion.scolaire.model.Parent;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
}
