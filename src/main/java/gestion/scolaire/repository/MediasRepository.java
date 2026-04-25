package gestion.scolaire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestion.scolaire.dto.DocumentType;
import gestion.scolaire.model.Medias;


@Repository
public interface MediasRepository  extends JpaRepository<Medias, Long> {
 List<Medias> findByTypeAndReferenceId(DocumentType type, Long referenceId);

    
}
