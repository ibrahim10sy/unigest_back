package gestion.scolaire.service;

import java.util.List;

import org.springframework.stereotype.Service;

import gestion.scolaire.dto.DashboardStatsDTO;
import gestion.scolaire.dto.EvolutionInscriptionDTO;
import gestion.scolaire.model.AnneeScolaire;
import gestion.scolaire.repository.AnneeScolaireRepository;
import gestion.scolaire.repository.ClasseRepository;
import gestion.scolaire.repository.EtudiantRepository;
import gestion.scolaire.repository.FiliereRepository;
import gestion.scolaire.repository.InscriptionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final FiliereRepository filiereRepository;
    private final ClasseRepository classeRepository;
    private final EtudiantRepository etudiantRepository;
    private final InscriptionRepository inscriptionRepository;
    private final AnneeScolaireRepository anneeRepository;

    public DashboardStatsDTO getDashboardStats() {

        DashboardStatsDTO dto = new DashboardStatsDTO();

        // totaux globaux
        dto.setTotalFilieres(filiereRepository.count());
        dto.setTotalClasses(classeRepository.count());
        dto.setTotalEtudiants(etudiantRepository.count());
        dto.setTotalInscriptions(inscriptionRepository.count());
        dto.setTotalAnneesScolaires(anneeRepository.count());

        // inscriptions année courante
        long inscriptionsCourantes =
                inscriptionRepository.countByAnneeScolaireActiveTrue();

        dto.setInscriptionsAnneeCourante(inscriptionsCourantes);

        // évolution annuelle
        List<AnneeScolaire> annees = anneeRepository.findAll();

        List<EvolutionInscriptionDTO> evolution = annees.stream()
                .map(a -> new EvolutionInscriptionDTO(
                        a.getLibelle(),
                        inscriptionRepository.countByAnneeScolaireId(a.getId())
                ))
                .toList();

        dto.setEvolutionInscriptions(evolution);

        return dto;
    }
}
