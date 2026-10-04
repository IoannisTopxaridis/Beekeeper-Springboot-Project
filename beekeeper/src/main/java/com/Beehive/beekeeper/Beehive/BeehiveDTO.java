package com.Beehive.beekeeper.Beehive;

import com.Beehive.beekeeper.BeehiveNotes.NoteDTO;
import com.Beehive.beekeeper.Location.entities.Location;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor // Required for Jackson JSON deserialization in PUT/POST requests
public class BeehiveDTO {
    @NotNull
    private Long id;

    @NotNull
    private Boolean queen;

    @NotNull
    private Boolean diseased;

    @NotNull
    private Beehive.PopulationDensity populationDensity;

    private LocalDateTime food;

    private Location location;

    private List<NoteDTO> notes = new ArrayList<>();

    public BeehiveDTO(Beehive beehive) {
        if (beehive != null) {
            this.id = beehive.getId();
            this.queen = beehive.getQueen();
            this.diseased = beehive.getDiseased();
            this.populationDensity = beehive.getPopulation();
            this.food = beehive.getFood();
            this.location = null;
            if (beehive.getNotes() != null) {
                this.notes = beehive.getNotes()
                        .stream()
                        .map(NoteDTO::new)
                        .collect(Collectors.toList());
            }
        }
    }

    public BeehiveDTO(Long id, Boolean queen, Boolean diseased, Beehive.PopulationDensity populationDensity, LocalDateTime food, Location location) {
        this.id = id;
        this.queen = queen;
        this.diseased = diseased;
        this.populationDensity = populationDensity;
        this.food = food;
        this.location = location;
        this.notes = new ArrayList<>();
    }
}