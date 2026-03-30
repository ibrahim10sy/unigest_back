package gestion.scolaire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.scolaire.model.Bulletin;
import gestion.scolaire.model.LigneBulletin;

public interface LigneBulletinRepository extends JpaRepository<LigneBulletin, Long> {

    List<LigneBulletin> findByBulletin(Bulletin bulletin);
}
