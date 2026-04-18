package gestion.scolaire.dto;

import java.util.List;

import lombok.Data;

@Data
public class AjoutMatieresDTO {
    private Long classeId;
    private List<Long> matiereIds;
}
