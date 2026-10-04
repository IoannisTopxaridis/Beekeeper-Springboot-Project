package com.Beehive.beekeeper.Location.queryhandlers;

import com.Beehive.beekeeper.Location.entities.Location;
import com.Beehive.beekeeper.Location.entities.LocationDTO;
import com.Beehive.beekeeper.Location.repositories.LocationRepository;
import com.Beehive.beekeeper.exceptions.LocationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetLocationQueryHandler implements Query<Integer, LocationDTO> {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    @Cacheable("locationCache")
    public ResponseEntity<LocationDTO> execute(Integer id) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isEmpty()) {
            throw new LocationNotFoundException();
        }
        return ResponseEntity.ok(new LocationDTO(location.get()));
    }
}
