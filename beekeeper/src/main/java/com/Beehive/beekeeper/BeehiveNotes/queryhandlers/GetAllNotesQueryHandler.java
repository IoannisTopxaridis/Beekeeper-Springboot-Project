package com.Beehive.beekeeper.BeehiveNotes.queryhandlers;

import com.Beehive.beekeeper.BeehiveNotes.NoteDTO;
import com.Beehive.beekeeper.BeehiveNotes.repository.NoteRepository;
import com.Beehive.beekeeper.Location.queryhandlers.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllNotesQueryHandler implements Query<Void, List<NoteDTO>> {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public ResponseEntity<List<NoteDTO>> execute(Void input){
        List<NoteDTO> noteDTO = noteRepository.getAllNotesDTO();
        return ResponseEntity.ok(noteDTO);
    }
}
