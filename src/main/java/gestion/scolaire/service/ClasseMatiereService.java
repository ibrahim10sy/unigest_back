package gestion.scolaire.service;

import org.springframework.stereotype.Service;

import gestion.scolaire.model.Classe;
import gestion.scolaire.model.ClasseMatiere;
import gestion.scolaire.model.Matiere;
import gestion.scolaire.repository.ClasseMatiereRepository;
import gestion.scolaire.repository.ClasseRepository;
import gestion.scolaire.repository.MatiereRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClasseMatiereService {

    private final ClasseMatiereRepository repository;
    private final ClasseRepository classeRepository;
    private final MatiereRepository matiereRepository;

    // ✅ Ajouter une matière à une classe avec coefficient
    public ClasseMatiere addMatiereToClasse(Long classeId, Long matiereId, double coefficient) {

        if (repository.existsByClasseIdAndMatiereId(classeId, matiereId)) {
            throw new RuntimeException("Cette matière est déjà affectée à cette classe");
        }

        Classe classe = classeRepository.findById(classeId)
                .orElseThrow(() -> new RuntimeException("Classe introuvable"));

        Matiere matiere = matiereRepository.findById(matiereId)
                .orElseThrow(() -> new RuntimeException("Matière introuvable"));

        ClasseMatiere cm = new ClasseMatiere();
        cm.setClasse(classe);
        cm.setMatiere(matiere);
        cm.setCoefficient(coefficient);

        return repository.save(cm);
    }

    public ClasseMatiere update(Long id, ClasseMatiere classeMatiere) {

    ClasseMatiere existing = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Classe introuvable"));

    // ✅ Mise à jour
    existing.setClasse(classeMatiere.getClasse());
    existing.setMatiere(classeMatiere.getMatiere());
    existing.setCoefficient(classeMatiere.getCoefficient());

    return repository.save(existing);
    }

    // ✅ Lister les matières d'une classe
    public List<ClasseMatiere> getMatieresByClasse(Long classeId) {
        return repository.findByClasseId(classeId);
    }

    // ✅ Supprimer une affectation
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
