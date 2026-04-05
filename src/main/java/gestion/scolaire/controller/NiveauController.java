package gestion.scolaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestion.scolaire.model.Niveau;
import gestion.scolaire.repository.NiveauRepository;

@RestController
@RequestMapping("/api/niveaux")
public class NiveauController {

    @Autowired
    private NiveauRepository niveauRepository;

    @GetMapping
    public List<Niveau> getAll() {
        return niveauRepository.findAll();
    }

    @PostMapping
    public Niveau save(@RequestBody Niveau niveau) {
        return niveauRepository.save(niveau);
    }

    @PutMapping("/{id}")
    public Niveau update(@PathVariable Long id, @RequestBody Niveau niveau) {
        Niveau existing = niveauRepository.findById(id).get();
        existing.setNom(niveau.getNom());
        return niveauRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        niveauRepository.deleteById(id);
    }
}