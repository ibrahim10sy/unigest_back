package gestion.scolaire.dto;

import lombok.Data;

@Data
public class PaiementResumeDTO {

    private double totalBrut;      // fraisInscription + fraisScolarite
    private double reduction;      // inscription.montantReduction
    private double totalNet;       // brut - reduction
    private double totalPaye;      // somme paiements
    private double resteAPayer;    // net - payé

    private String statutPaiement; // COMPLET / PARTIEL / IMPAYE
}