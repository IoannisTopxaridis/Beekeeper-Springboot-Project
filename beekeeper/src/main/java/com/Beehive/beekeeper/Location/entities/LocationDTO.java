package com.Beehive.beekeeper.Location.entities;

import lombok.Data;

@Data
public class LocationDTO {
    private String name;
    private int beehivecount;

    public LocationDTO(Location location) {
        this.name = location.getName();
        this.beehivecount = location.getBeehivecount();
    }
}
