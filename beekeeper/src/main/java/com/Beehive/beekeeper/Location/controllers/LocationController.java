package com.Beehive.beekeeper.Location.controllers;

import com.Beehive.beekeeper.Location.commandhandlers.CreateLocationCommandHandler;
import com.Beehive.beekeeper.Location.commandhandlers.DeleteLocationCommandHandler;
import com.Beehive.beekeeper.Location.commandhandlers.UpdateLocationCommand;
import com.Beehive.beekeeper.Location.commandhandlers.UpdateLocationCommandHandler;
import com.Beehive.beekeeper.Location.entities.Location;
import com.Beehive.beekeeper.Location.entities.LocationDTO;
import com.Beehive.beekeeper.Location.queryhandlers.GetAllLocationsQueryHandler;
import com.Beehive.beekeeper.Location.queryhandlers.GetLocationQueryHandler;
import com.Beehive.beekeeper.Location.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired private LocationRepository locationRepository;
    @Autowired private GetAllLocationsQueryHandler getAllLocationsQueryHandler;
    @Autowired private GetLocationQueryHandler getLocationQueryHandler;
    @Autowired private CreateLocationCommandHandler createLocationCommandHandler;
    @Autowired private UpdateLocationCommandHandler updateLocationCommandHandler;
    @Autowired private DeleteLocationCommandHandler deleteLocationCommandHandler;


    @GetMapping("/search/{mostBeehives}")
    public ResponseEntity<List<Location>> findLocationbyCount(@PathVariable Integer mostBeehives){
        return ResponseEntity.ok(locationRepository.findLocationsWithBeehiveCountLessThan(mostBeehives));

    }

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getLocation() {
        return getAllLocationsQueryHandler.execute(null);
    }

    @GetMapping ("/{id}")
    public ResponseEntity <LocationDTO> getLocation(@PathVariable int id){
        return getLocationQueryHandler.execute(id);
    }

    @PostMapping("/create")
    public ResponseEntity createLocation (@RequestBody Location location){
        return createLocationCommandHandler.execute(location);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateLocation(@PathVariable int id, @RequestBody Location location){
        UpdateLocationCommand command = new UpdateLocationCommand(id,location);
        return updateLocationCommandHandler.execute(command);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteLocation(@PathVariable int id){
        return deleteLocationCommandHandler.execute(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Location>> searchLocations(@RequestParam(value = "name") String name){
        return ResponseEntity.ok(locationRepository.findByNameContaining(name));
    }
}