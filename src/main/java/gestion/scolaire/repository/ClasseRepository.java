package gestion.scolaire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.scolaire.model.Classe;
import gestion.scolaire.model.Filiere;

public interface ClasseRepository extends JpaRepository<Classe, Long> {
    // Exemple : trouver toutes les classes par niveau
    // List<Classe> findByNiveau(String niveau);
    List<Classe> findByFiliere(Filiere filiere);
}
