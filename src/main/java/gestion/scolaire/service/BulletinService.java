package gestion.scolaire.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.scolaire.model.Bulletin;
import gestion.scolaire.model.Etudiant;
import gestion.scolaire.repository.BulletinRepository;
import gestion.scolaire.repository.EtudiantRepository;

@Service
public class BulletinService {

    @Autowired
    private NoteService noteService;

    @Autowired
    private BulletinRepository bulletinRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;


    // 1️⃣ Générer un bulletin
    public Bulletin genererBulletin(Long etudiantId, int semestre){

        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Etudiant introuvable"));

        double moyenne = noteService.calculerMoyenneEtudiant(etudiantId, semestre);

        Bulletin bulletin = new Bulletin();

        bulletin.setEtudiant(etudiant);
        bulletin.setSemestre(semestre);
        bulletin.setMoyenneGenerale(moyenne);
        bulletin.setDateGeneration(LocalDate.now());

        return bulletinRepository.save(bulletin);
    }


    // 2️⃣ Récupérer un bulletin
    public Bulletin getBulletin(Long bulletinId){

        return bulletinRepository.findById(bulletinId)
                .orElseThrow(() -> new RuntimeException("Bulletin introuvable"));
    }


    // 3️⃣ Bulletins d'un étudiant
    public List<Bulletin> getBulletinsEtudiant(Long etudiantId){

        return bulletinRepository.findByEtudiantId(etudiantId);
    }


    // 4️⃣ Bulletin d'un semestre
    public Bulletin getBulletinEtudiantSemestre(Long etudiantId, int semestre){

        return bulletinRepository
                .findByEtudiantIdAndSemestre(etudiantId, semestre)
                .orElseThrow(() -> new RuntimeException("Bulletin introuvable"));
    }


    // 5️⃣ Supprimer bulletin
    public void supprimerBulletin(Long bulletinId){
        bulletinRepository.deleteById(bulletinId);
    }

}