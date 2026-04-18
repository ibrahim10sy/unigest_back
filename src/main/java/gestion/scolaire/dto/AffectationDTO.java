package gestion.scolaire.dto;

public class AffectationDTO {

    private Long id;

    // Enseignant
    private Long enseignantId;
    private String enseignantNom;

    // Matière
    private Long matiereId;
    private String matiereNom;

    // Classe
    private Long classeId;
    private String classeNom;

    // Filière
    private Long filiereId;
    private String filiereNom;

    // Niveau
    private Long niveauId;
    private String niveauNom;

    public AffectationDTO(Long id,
                          Long enseignantId, String enseignantNom,
                          Long matiereId, String matiereNom,
                          Long classeId, String classeNom,
                          Long filiereId, String filiereNom,
                          Long niveauId, String niveauNom) {
        this.id = id;
        this.enseignantId = enseignantId;
        this.enseignantNom = enseignantNom;
        this.matiereId = matiereId;
        this.matiereNom = matiereNom;
        this.classeId = classeId;
        this.classeNom = classeNom;
        this.filiereId = filiereId;
        this.filiereNom = filiereNom;
        this.niveauId = niveauId;
        this.niveauNom = niveauNom;
    }
}
