package com.Beehive.beekeeper.Location.commandhandlers;

import com.Beehive.beekeeper.Location.entities.Location;
import com.Beehive.beekeeper.Location.repositories.LocationRepository;
import com.Beehive.beekeeper.exceptions.LocationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateLocationCommandHandler implements  Command <UpdateLocationCommand, ResponseEntity> {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public ResponseEntity<ResponseEntity> execute(UpdateLocationCommand command) {

        boolean existingLocation = locationRepository.findById(command.getId()).isPresent();

        if (!existingLocation) {
            throw new LocationNotFoundException();
        } else {
            Location location = command.getLocation();
            location.setId(command.getId());
            locationRepository.save(location);
            return ResponseEntity.ok().build();
        }
    }
}