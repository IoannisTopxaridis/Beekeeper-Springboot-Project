package com.Beehive.beekeeper.BeehiveNotes.repository;

import com.Beehive.beekeeper.BeehiveNotes.NoteDTO;
import com.Beehive.beekeeper.BeehiveNotes.NoteEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {


    @Query("SELECT new com.Beehive.beekeeper.BeehiveNotes.NoteDTO(n.noteJSON) FROM NoteEntity n")
    List<NoteDTO> getAllNotesDTO();

    @Query("SELECT new com.Beehive.beekeeper.BeehiveNotes.NoteDTO(n.noteJSON) " +
            "FROM NoteEntity n WHERE n.beehive.id = :beehiveId")
    List<NoteDTO> findNotesByBeehiveId(@Param("beehiveId") Long beehiveId);



}
