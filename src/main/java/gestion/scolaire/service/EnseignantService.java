package gestion.scolaire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import gestion.scolaire.model.Enseignant;
import gestion.scolaire.model.Role;
import gestion.scolaire.repository.EnseignantRepository;

@Service
public class EnseignantService {

    @Autowired
    private EnseignantRepository enseignantRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;

    // Créer
    public Enseignant creerEnseignant(Enseignant enseignant) {

        String encodedPassword = passwordEncoder.encode("123456");
        enseignant.setPassword(encodedPassword);
        enseignant.setRole(Role.ENSEIGNANT);
        enseignant.setActif(true);
        
        Enseignant saved = enseignantRepository.save(enseignant);

    if (saved.getEmail() != null && !saved.getEmail().isBlank()) {
        envoyerMailCreation(saved);
    }

    return saved;

    }

   private void envoyerMailCreation(Enseignant e) {
    // 1. Génération d'un mot de passe temporaire (ex: simple random)
    String tempPassword = "123456";
    
    // 2. Construction du HTML
    String htmlContent = """
        <div style="font-family: sans-serif; padding: 20px;">
            <h2>Bienvenue sur Lyuni-Gest</h2>
            <p>Bonjour %s %s,</p>
            <p>Votre compte enseignant a été créé avec succès.</p>
            <div style="background: #f4f4f4; padding: 15px; border-radius: 5px;">
                <p><strong>Email :</strong> %s</p>
                <p><strong>Mot de passe temporaire :</strong> <span style="color: red;">%s</span></p>
            </div>
            <p><a href="https://lyuni-gest.com/" style="padding: 10px 20px; background: #007bff; color: white; text-decoration: none;">Accéder à la plateforme</a></p>
            <p>⚠️ <i>Veuillez changer votre mot de passe à la première connexion.</i></p>
        </div>
        """.formatted(e.getPrenom(), e.getNom(), e.getEmail(), tempPassword);

    // 3. Appel du service
    emailService.envoyerEmailHtml(e.getEmail(), "Création de votre compte - Accès plateforme", htmlContent);
}

    // Modifier
    public Enseignant modifierEnseignant(Long id, Enseignant data) {

        Enseignant enseignant = enseignantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enseignant introuvable"));
        if (data.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(data.getPassword());
            enseignant.setPassword(encodedPassword);
        }
        enseignant.setNom(data.getNom());
        enseignant.setPrenom(data.getPrenom());
        enseignant.setEmail(data.getEmail());
        enseignant.setTelephone(data.getTelephone());
        enseignant.setSpecialite(data.getSpecialite());

        return enseignantRepository.save(enseignant);
    }

    // Supprimer
    public void supprimerEnseignant(Long id) {
        enseignantRepository.deleteById(id);
    }

    // Trouver par ID
    public Enseignant getEnseignantById(Long id) {
        return enseignantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enseignant introuvable"));
    }

    // Lister tous
    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }
}