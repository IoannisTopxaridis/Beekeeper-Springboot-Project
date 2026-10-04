package com.Beehive.beekeeper.Beehive;

import com.Beehive.beekeeper.BeehiveNotes.NoteEntity;
import com.Beehive.beekeeper.Location.entities.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "beehive")
@Data
public class Beehive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "queen")
    private Boolean queen;
    private Boolean diseased;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "population")
    private PopulationDensity population;

    @Column(name = "food", insertable = false)
    private LocalDateTime food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @JsonIgnore
    private Location location;

    @OneToMany(mappedBy = "beehive", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NoteEntity> notes = new ArrayList<>();

    public enum PopulationDensity {
        low,
        high
    }



}
