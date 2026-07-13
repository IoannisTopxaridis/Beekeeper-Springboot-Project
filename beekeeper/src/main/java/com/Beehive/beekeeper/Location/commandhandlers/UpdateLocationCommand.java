package com.Beehive.beekeeper.Location.commandhandlers;

import com.Beehive.beekeeper.Location.entities.Location;
import lombok.Data;

@Data
public class UpdateLocationCommand {
    private int id;
    private Location location;

    public UpdateLocationCommand(int id, Location location) {
        this.id = id;
        this.location = location;
    }
}