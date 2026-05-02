package gestion.scolaire.dto;

import java.util.List;
import lombok.Data;

@Data
public class AppelBatchRequest {

    private Long seanceId;

    private List<AppelItem> appels;
}