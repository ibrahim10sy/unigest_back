package gestion.scolaire.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvolutionInscriptionDTO {

    private String annee;
    private long nombreInscriptions;
}
