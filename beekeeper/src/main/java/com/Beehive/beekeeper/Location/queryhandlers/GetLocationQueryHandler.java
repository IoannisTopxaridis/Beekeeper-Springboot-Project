package com.Beehive.beekeeper.Location.queryhandlers;

import com.Beehive.beekeeper.Location.entities.Location;
import com.Beehive.beekeeper.Location.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class GetLocationQueryHandler implements Query<Integer, Location> {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public ResponseEntity<Location> execute(Integer id) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isEmpty()) {
            throw new RuntimeException("Location not found");
        }
        return ResponseEntity.ok(location.get());
    }
}
