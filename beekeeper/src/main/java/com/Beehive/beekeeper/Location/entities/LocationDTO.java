package com.Beehive.beekeeper.Location.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocationDTO {
    private Integer id;
    private String name;
    private int beehivecount;

    public LocationDTO(Integer id, String name, int beehivecount) {
        this.id = id;
        this.name = name;
        this.beehivecount = beehivecount;
    }

    public LocationDTO(Location location) {
        if (location != null) {
            this.id = location.getId();
            this.name = location.getName();
            this.beehivecount = location.getBeehivecount();
        }
    }
}