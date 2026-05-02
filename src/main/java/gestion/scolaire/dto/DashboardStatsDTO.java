package gestion.scolaire.dto;

import java.util.List;

import lombok.Data;

@Data
public class DashboardStatsDTO {

    private long totalFilieres;
    private long totalClasses;
    private long totalEtudiants;
    private long totalInscriptions;
    private long totalAnneesScolaires;

    // année courante
    private long inscriptionsAnneeCourante;

    // évolution
    private List<EvolutionInscriptionDTO> evolutionInscriptions;
}