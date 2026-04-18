package gestion.scolaire.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.scolaire.model.Inscription;
import gestion.scolaire.model.ModePaiement;
import gestion.scolaire.model.Paiement;
import gestion.scolaire.repository.InscriptionRepository;
import gestion.scolaire.repository.PaiementRepository;

@Service
public class PaiementService {

    @Autowired
    private PaiementRepository paiementRepository;

    @Autowired
    private InscriptionRepository inscriptionRepository;

    // 1️⃣ Ajouter un paiement
    public Paiement effectuerPaiement(Long inscriptionId,
                                      double montant,
                                      ModePaiement mode
                                      ){

        Inscription inscription = inscriptionRepository.findById(inscriptionId)
                .orElseThrow(() -> new RuntimeException("Inscription introuvable"));

        Paiement paiement = new Paiement();
         String matricule = genererRef();
        paiement.setInscription(inscription);
        paiement.setMontant(montant);
        paiement.setDatePaiement(LocalDate.now());
        paiement.setModePaiement(mode);
        paiement.setReference(matricule);

        return paiementRepository.save(paiement);
    }
    public String genererRef() {
    int annee = LocalDate.now().getYear();

    // Compter le nombre d'étudiants déjà existants
    long count = paiementRepository.count();

    // Incrément
    long numero = count + 1;

    // Formatage avec 4 chiffres
    return "PAI-" + annee + "-" + String.format("%04d", numero);
}

    // 2️⃣ Modifier un paiement
    public Paiement modifierPaiement(Long paiementId,
                                        Long inscriptionId,
                                     double montant,
                                     ModePaiement mode
                                     ){

        Paiement paiement = paiementRepository.findById(paiementId)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable"));

        paiement.setMontant(montant);
        paiement.setModePaiement(mode);
        if (inscriptionId != null) {
            Inscription inscription = inscriptionRepository.findById(inscriptionId)
                    .orElseThrow(() -> new RuntimeException("Inscription introuvable"));
            paiement.setInscription(inscription);
        }

        return paiementRepository.save(paiement);
    }

    // 3️⃣ Supprimer un paiement
    public void supprimerPaiement(Long paiementId){
        paiementRepository.deleteById(paiementId);
    }

    // 4️⃣ Somme des paiements pour une inscription
    public double calculerTotalPaye(Long inscriptionId){

        Inscription inscription = inscriptionRepository.findById(inscriptionId)
                .orElseThrow(() -> new RuntimeException("Inscription introuvable"));

        Double total = paiementRepository.sumMontantByInscription(inscription);
        return total == null ? 0 : total;
    }

    // 5️⃣ Récupérer les paiements d'un étudiant
    public List<Paiement> getPaiementsParEtudiant(Long etudiantId){
        return paiementRepository.findByEtudiantId(etudiantId);
    }

    // 6️⃣ Historique des paiements par classe et année scolaire
    public List<Paiement> getHistoriquePaiements(Long classeId, Long anneeId){
        return paiementRepository.findByClasseAndAnnee(classeId, anneeId);
    }

    // 7️⃣ Récupérer un paiement par ID
    public Paiement getPaiementById(Long paiementId){
        return paiementRepository.findById(paiementId)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable"));
    }

    public List<Paiement> getHistoriquePaiementsEtudiant(Long etudiantId,
                                                     Long classeId,
                                                     Long anneeId){
    return paiementRepository.findByEtudiantClasseAnnee(etudiantId, classeId, anneeId);
}
}