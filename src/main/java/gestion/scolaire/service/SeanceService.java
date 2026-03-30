package gestion.scolaire.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.scolaire.model.Affectation;
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

    // 1️⃣ Démarrer une séance
    public Seance demarrerSeance(Long affectationId){

        Affectation affectation = affectationRepository.findById(affectationId)
                .orElseThrow(() -> new RuntimeException("Affectation introuvable"));

        Seance seance = new Seance();
        seance.setAffectation(affectation);
        seance.setDate(LocalDate.now());
        seance.setHeureDebut(LocalTime.now());
        seance.setStatut(StatutSeance.EN_COURS);
        seance.setDateCreation(LocalDateTime.now());

        return seanceRepository.save(seance);
    }

    // 2️⃣ Terminer une séance
    public Seance terminerSeance(Long seanceId){
        Seance seance = seanceRepository.findById(seanceId)
                .orElseThrow(() -> new RuntimeException("Séance introuvable"));

        seance.setHeureFin(LocalTime.now());
        seance.setStatut(StatutSeance.TERMINEE);
        seance.setDateModification(LocalDateTime.now());

        return seanceRepository.save(seance);
    }

    // 3️⃣ Récupérer séances par date
    public List<Seance> getSeancesParDate(LocalDate date){
        return seanceRepository.findByDate(date);
    }

    // 4️⃣ Récupérer séances par affectation
    public List<Seance> getSeancesParAffectation(Long affectationId){
        return seanceRepository.findByAffectationId(affectationId);
    }

    // 5️⃣ Récupérer séances par affectation et date
    public List<Seance> getSeancesParAffectationEtDate(Long affectationId, LocalDate date){
        return seanceRepository.findByAffectationIdAndDate(affectationId, date);
    }

    // 6️⃣ Récupérer toutes les séances en cours
    public List<Seance> getSeancesEnCours(){
        return seanceRepository.findByStatut(StatutSeance.EN_COURS);
    }
}
