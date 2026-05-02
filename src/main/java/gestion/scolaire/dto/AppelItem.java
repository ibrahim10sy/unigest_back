package gestion.scolaire.dto;

import gestion.scolaire.model.StatutPresence;
import lombok.Data;

@Data
public class AppelItem {
    private Long etudiantId;
    private StatutPresence statut;
    private int minutesRetard;
    private String motif;
}