package com.Beehive.beekeeper.BeehiveNotes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/beehive/{beehiveId}")
    public ResponseEntity<NoteDTO> createOrUpdateNote(
            @PathVariable Long beehiveId,
            @RequestBody NoteDTO noteDTO) {

        NoteEntity updatedNote = noteService.createOrUpdateNoteForBeehive(
                beehiveId,
                noteDTO.getNoteJSON() != null ? noteDTO.getNoteJSON() : ""
        );
        return ResponseEntity.ok(new NoteDTO(updatedNote));
    }
}