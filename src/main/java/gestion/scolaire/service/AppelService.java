package gestion.scolaire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.scolaire.model.Appel;
import gestion.scolaire.model.Etudiant;
import gestion.scolaire.model.Seance;
import gestion.scolaire.model.StatutPresence;
import gestion.scolaire.repository.AppelRepository;
import gestion.scolaire.repository.EtudiantRepository;
import gestion.scolaire.repository.SeanceRepository;

@Service
public class AppelService {

    @Autowired
    private AppelRepository appelRepository;

    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;


    public Appel faireAppel(Long seanceId,
                            Long etudiantId,
                            StatutPresence statut,
                            int retard,
                            String motif){

        Seance seance = seanceRepository.findById(seanceId).orElseThrow();
        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElseThrow();

        Appel appel = new Appel();

        appel.setSeance(seance);
        appel.setEtudiant(etudiant);
        appel.setStatut(statut);
        appel.setMinutesRetard(retard);
        appel.setMotif(motif);

        return appelRepository.save(appel);
    }

    public List<Appel> getAppelsParSeance(Long seanceId){
    return appelRepository.findBySeanceId(seanceId);
}

    public List<Appel> getAppelsParEtudiant(Long etudiantId){
    return appelRepository.findByEtudiantId(etudiantId);
    }

    public Appel getAppelEtudiantDansSeance(Long seanceId, Long etudiantId){

    return appelRepository
            .findBySeanceIdAndEtudiantId(seanceId, etudiantId)
            .orElseThrow(() -> new RuntimeException("Appel introuvable"));
}

 // supprimer un appel par id
    public void supprimerAppel(Long appelId){
        appelRepository.deleteById(appelId);
    }


    // supprimer l'appel d'un étudiant dans une séance
    public void supprimerAppelEtudiantSeance(Long seanceId, Long etudiantId){
        appelRepository.deleteBySeanceIdAndEtudiantId(seanceId, etudiantId);
    }


    // supprimer tous les appels d'une séance
    public void supprimerAppelsSeance(Long seanceId){
        appelRepository.deleteBySeanceId(seanceId);
    }
}
