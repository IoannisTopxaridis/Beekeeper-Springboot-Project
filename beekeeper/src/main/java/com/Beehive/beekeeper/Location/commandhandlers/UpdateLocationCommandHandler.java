package com.Beehive.beekeeper.Location.commandhandlers;

import com.Beehive.beekeeper.Location.entities.Location;
import com.Beehive.beekeeper.Location.entities.LocationDTO;
import com.Beehive.beekeeper.Location.repositories.LocationRepository;
import com.Beehive.beekeeper.exceptions.LocationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateLocationCommandHandler implements Command<UpdateLocationCommand, LocationDTO> {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    @CachePut(value = "locationCache", key = "#command.getId()")
    public ResponseEntity<LocationDTO> execute(UpdateLocationCommand command) {

        boolean existingLocation = locationRepository.findById(command.getId()).isPresent();

        if (!existingLocation) {
            throw new LocationNotFoundException();
        } else {
            Location location = command.getLocation();
            location.setId(command.getId());
            locationRepository.save(location);
            return ResponseEntity.ok(new LocationDTO(location));
        }
    }
}