package com.Beehive.beekeeper.BeehiveNotes.commandhandlers;

import com.Beehive.beekeeper.Beehive.Beehive;
import com.Beehive.beekeeper.Beehive.repository.BeehiveRepository;
import com.Beehive.beekeeper.BeehiveNotes.NoteEntity;
import com.Beehive.beekeeper.BeehiveNotes.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateNoteCommandHandler {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private BeehiveRepository beehiveRepository;

    private static final Logger logger = LoggerFactory.getLogger(CreateNoteCommandHandler.class);

    public ResponseEntity<?> execute(NoteEntity noteEntity, Long beehiveId) {
        logger.info("Executing {} for Beehive ID: {}", getClass().getName(), beehiveId);

        Beehive beehive = beehiveRepository.getReferenceById(beehiveId);
        noteEntity.setBeehive(beehive);

        noteRepository.save(noteEntity);
        return ResponseEntity.ok().build();
    }
}