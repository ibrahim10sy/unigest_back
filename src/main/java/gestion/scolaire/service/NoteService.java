package gestion.scolaire.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.scolaire.model.Affectation;
import gestion.scolaire.model.Etudiant;
import gestion.scolaire.model.Note;
import gestion.scolaire.model.TypeNote;
import gestion.scolaire.repository.AffectationRepository;
import gestion.scolaire.repository.EtudiantRepository;
import gestion.scolaire.repository.NoteRepository;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private AffectationRepository affectationRepository;


    // AJOUTER NOTE
    public Note ajouterNote(Long etudiantId,
                            Long affectationId,
                            double valeur,
                            TypeNote type,
                            int semestre){

        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElseThrow();
        Affectation affectation = affectationRepository.findById(affectationId).orElseThrow();

        Note note = new Note();

        note.setEtudiant(etudiant);
        note.setAffectation(affectation);
        note.setValeur(valeur);
        note.setType(type);
        note.setSemestre(semestre);
        note.setDateEvaluation(LocalDate.now());

        return noteRepository.save(note);
    }


    // MODIFIER NOTE
    public Note modifierNote(Long noteId,
                             double valeur,
                             TypeNote type){

        Note note = noteRepository.findById(noteId).orElseThrow();

        note.setValeur(valeur);
        note.setType(type);

        return noteRepository.save(note);
    }


    // SUPPRIMER NOTE
    public void supprimerNote(Long noteId){
        noteRepository.deleteById(noteId);
    }


    // RECUPERER PAR ID
    public Note getNoteById(Long noteId){
        return noteRepository.findById(noteId).orElseThrow();
    }


    // NOTES PAR ETUDIANT
    public List<Note> getNotesEtudiant(Long etudiantId){
        return noteRepository.findByEtudiantId(etudiantId);
    }


    // NOTES PAR AFFECTATION
    public List<Note> getNotesParAffectation(Long affectationId){
        return noteRepository.findByAffectationId(affectationId);
    }


    // NOTES PAR ETUDIANT ET SEMESTRE
    public List<Note> getNotesEtudiantSemestre(Long etudiantId, int semestre){
        return noteRepository.findByEtudiantIdAndSemestre(etudiantId, semestre);
    }


    // NOTES PAR AFFECTATION ET SEMESTRE
    public List<Note> getNotesAffectationSemestre(Long affectationId, int semestre){
        return noteRepository.findByAffectationIdAndSemestre(affectationId, semestre);
    }


    // MOYENNE ETUDIANT
    public double calculerMoyenneEtudiant(Long etudiantId, int semestre){

        List<Note> notes = noteRepository.findByEtudiantIdAndSemestre(etudiantId, semestre);

        double total = 0;

        for(Note n : notes){
            total += n.getValeur();
        }

        return notes.isEmpty() ? 0 : total / notes.size();
    }

}