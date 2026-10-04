package com.Beehive.beekeeper.BeehiveNotes;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoteDTO {

    @JsonRawValue
    private String noteJSON;

    public NoteDTO(String noteJSON) {
        this.noteJSON = noteJSON;
    }

    public NoteDTO(NoteEntity noteEntity) {
        this.noteJSON = noteEntity.getNoteJSON();
    }
}