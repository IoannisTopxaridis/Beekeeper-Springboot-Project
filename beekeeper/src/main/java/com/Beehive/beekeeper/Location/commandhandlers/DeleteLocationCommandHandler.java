package com.Beehive.beekeeper.Location.commandhandlers;

import com.Beehive.beekeeper.Location.entities.Location;
import com.Beehive.beekeeper.Location.repositories.LocationRepository;
import com.Beehive.beekeeper.exceptions.LocationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteLocationCommandHandler implements Command<Integer, ResponseEntity> {

    @Autowired
    private LocationRepository locationRepository;
    
    @Override
    public ResponseEntity<ResponseEntity> execute(Integer id) {
        
        boolean existingLocation = locationRepository.findById(id).isPresent();
        
        if (!existingLocation) {
            throw new LocationNotFoundException();
        } else {
            Location location = locationRepository.findById(id).get();
            locationRepository.delete(location);
            return ResponseEntity.ok().build();
        }
    }
}
