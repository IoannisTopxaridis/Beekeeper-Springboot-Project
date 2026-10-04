package com.Beehive.beekeeper.BeehiveNotes;

import com.Beehive.beekeeper.Beehive.Beehive;
import com.Beehive.beekeeper.Beehive.repository.BeehiveRepository;
import com.Beehive.beekeeper.BeehiveNotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    NoteRepository noteRepository;

    @Autowired
    com.Beehive.beekeeper.Beehive.repository.BeehiveRepository beehiveRepository;

    public NoteService(NoteRepository noteRepository, BeehiveRepository beehiveRepository) {
        this.noteRepository = noteRepository;
        this.beehiveRepository = beehiveRepository;
    }

    @Transactional
    public NoteEntity createOrUpdateNoteForBeehive(Long beehiveId, String contentJson) {
        Beehive beehive = beehiveRepository.findById(beehiveId)
                .orElseThrow(() -> new RuntimeException("Beehive not found"));

        List<NoteEntity> existingNotes = beehive.getNotes();

        if (existingNotes != null && !existingNotes.isEmpty()) {
            NoteEntity existingNote = existingNotes.get(existingNotes.size() - 1); // Get latest note
            existingNote.setNoteJSON(contentJson);
            return noteRepository.save(existingNote);
        }

        NoteEntity newNote = new NoteEntity();
        newNote.setNoteJSON(contentJson);
        newNote.setBeehive(beehive);
        return noteRepository.save(newNote);
    }
}