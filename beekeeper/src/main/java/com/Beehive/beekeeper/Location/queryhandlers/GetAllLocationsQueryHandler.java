package com.Beehive.beekeeper.Location.queryhandlers;

import com.Beehive.beekeeper.Location.entities.Location;
import com.Beehive.beekeeper.Location.entities.LocationDTO;
import com.Beehive.beekeeper.Location.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllLocationsQueryHandler implements Query<Void, List<LocationDTO>> {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public ResponseEntity<List<LocationDTO>> execute(Void input) {
        List<LocationDTO> locationDTO = locationRepository
                .findAll()
                .stream()
                .map(LocationDTO::new)
                .toList();

        return ResponseEntity.ok(locationDTO);
    }
}
