package gestion.scolaire.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestion.scolaire.dto.UserView;
import gestion.scolaire.model.Role;
import gestion.scolaire.model.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    List<Utilisateur> findByRole(Role role);

    Optional<Utilisateur> findByEmail(String email);

    Optional<Utilisateur> findByEmailOrTelephone(String email, String telephone);

     Optional<UserView> findProjectedByEmailOrTelephone(String email, String telephone);
}