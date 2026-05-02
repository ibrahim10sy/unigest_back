package gestion.scolaire.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.scolaire.dto.SeanceDTO;
import gestion.scolaire.model.Affectation;
import gestion.scolaire.model.AnneeScolaire;
import gestion.scolaire.model.Seance;
import gestion.scolaire.model.StatutSeance;
import gestion.scolaire.repository.AffectationRepository;
import gestion.scolaire.repository.SeanceRepository;

@Service
public class SeanceService {

    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private AffectationRepository affectationRepository;
    @Autowired
    private AnneeScolaireService annnAnneeScolaireService;

    // 1️⃣ Démarrer une séance
    public Seance demarrerSeance(Long affectationId, String matiere) {

        Affectation affectation = affectationRepository.findById(affectationId)
                .orElseThrow(() -> new RuntimeException("Affectation introuvable"));
        AnneeScolaire anneeActive = annnAnneeScolaireService.getAnneeActive();

        Seance seance = new Seance();
        seance.setAffectation(affectation);
        seance.setAnneeScolaire(anneeActive);
        seance.setMatiere(matiere);
        seance.setDate(LocalDate.now());
        seance.setHeureDebut(LocalTime.now());
        seance.setStatut(StatutSeance.EN_COURS);
        seance.setDateCreation(LocalDateTime.now());

        return seanceRepository.save(seance);
    }

    // 2️⃣ Terminer une séance
    public Seance terminerSeance(Long seanceId) {
        Seance seance = seanceRepository.findById(seanceId)
                .orElseThrow(() -> new RuntimeException("Séance introuvable"));

        seance.setHeureFin(LocalTime.now());
        seance.setStatut(StatutSeance.TERMINEE);
        seance.setDateModification(LocalDateTime.now());

        return seanceRepository.save(seance);
    }

    // 3️⃣ Récupérer séances par date
    public List<Seance> getSeancesParDate(LocalDate date) {
        return seanceRepository.findByDate(date);
    }

    public List<SeanceDTO> getSeancesDuJour() {
        return seanceRepository.findByDate(LocalDate.now())
                .stream()
                .map(s -> {
                    SeanceDTO dto = new SeanceDTO();
                    dto.setId(s.getId());
                    dto.setMatiere(s.getMatiere());
                    dto.setProfesseur(s.getAffectation().getEnseignant().getPrenom() + " "
                            + s.getAffectation().getEnseignant().getNom());
                    dto.setClasse(s.getAffectation().getClasse().getNom());
                    dto.setFiliere(s.getAffectation().getClasse().getFiliere().getNom());
                    dto.setHeureDebut(String.valueOf(s.getHeureDebut()));
                    dto.setHeureFin(String.valueOf(s.getHeureFin()));
                    dto.setStatut(s.getStatut().name());
                    return dto;
                })
                .toList();
    }

    // 4️⃣ Récupérer séances par affectation
    public List<Seance> getSeancesParAffectation(Long affectationId) {
        return seanceRepository.findByAffectationId(affectationId);
    }

    // 5️⃣ Récupérer séances par affectation et date
    public List<Seance> getSeancesParAffectationEtDate(Long affectationId, LocalDate date) {
        return seanceRepository.findByAffectationIdAndDate(affectationId, date);
    }

    // 6️⃣ Récupérer toutes les séances en cours
    public List<Seance> getSeancesEnCours() {
        return seanceRepository.findByStatut(StatutSeance.EN_COURS);
    }

    public List<Seance> getSeances() {
        return seanceRepository.findAll();
    }
}
