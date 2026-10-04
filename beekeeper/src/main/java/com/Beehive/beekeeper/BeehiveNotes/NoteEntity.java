package com.Beehive.beekeeper.BeehiveNotes;

import com.Beehive.beekeeper.Beehive.Beehive;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "notes")
@AllArgsConstructor
@NoArgsConstructor
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "noteJSON")
    private String noteJSON;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beehive_id", nullable = false)
    private Beehive beehive;


    public NoteEntity(Note note) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.noteJSON = objectMapper.writeValueAsString(note);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON Parse error", e);
        }
    }

    public Note convertToNote(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(this.noteJSON, Note.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON Parse error", e);
        }
    }
}