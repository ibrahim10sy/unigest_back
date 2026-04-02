package gestion.scolaire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import gestion.scolaire.model.Admin;
import gestion.scolaire.model.Role;
import gestion.scolaire.repository.AdminRepository;
import java.util.List;


@Service
public class AdminService {

    @Autowired
     AdminRepository adminRepository;

     @Autowired
    private PasswordEncoder passwordEncoder; // Injecte le bean BCrypt

    public Admin creer(Admin admin){

      // 1. Vérifications d'existence
        if(adminRepository.existsByEmail(admin.getEmail())){
            throw new RuntimeException("Un admin avec cet email existe déjà");
        }

        if(adminRepository.existsByTelephone(admin.getTelephone())){
            throw new RuntimeException("Ce numéro est déjà utilisé");
        }

        // 2. Attribution du rôle
        admin.setRole(Role.ADMIN);

        // 3. HACHAGE du mot de passe (L'étape manquante)
        String encodedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodedPassword);

        return adminRepository.save(admin);
    }

    public List<Admin> liste(){
        return adminRepository.findAll();
    }

    public Admin getById(Long id){
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin introuvable"));
    }

    public Admin update(Long id, Admin admin){

        Admin existing = getById(id);
          if(admin.getPassword() != null){
         String encodedPassword = passwordEncoder.encode(admin.getPassword());
        existing.setPassword(encodedPassword);
     }

        existing.setNom(admin.getNom());
        existing.setPrenom(admin.getPrenom());
        existing.setEmail(admin.getEmail());
        existing.setTelephone(admin.getTelephone());

        return adminRepository.save(existing);
    }

    public void delete(Long id){
        adminRepository.deleteById(id);
    }
}