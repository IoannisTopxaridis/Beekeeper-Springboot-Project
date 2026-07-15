package com.Beehive.beekeeper.Location.commandhandlers;

import com.Beehive.beekeeper.Location.entities.Location;
import com.Beehive.beekeeper.Location.repositories.LocationRepository;
import com.Beehive.beekeeper.exceptions.LocationsNotValidException;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateLocationCommandHandler implements Command <Location, ResponseEntity>{

    @Autowired
    private LocationRepository locationRepository;

    private void validateLocation (Location location){
        if (StringUtils.isBlank(location.getName())){
            throw new LocationsNotValidException("Location's name can't be empty");
        }

        if (location.getBeehivecount() <=0){
            throw new LocationsNotValidException("Location's beehive count can't be negative or 0");
        }
    }

    @Override
    public ResponseEntity execute(Location location) {
        validateLocation(location);
        locationRepository.save(location);
        return ResponseEntity.ok().build();
    }
}
