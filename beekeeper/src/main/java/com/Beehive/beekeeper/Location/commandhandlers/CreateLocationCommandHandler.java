package com.Beehive.beekeeper.Location.commandhandlers;

import com.Beehive.beekeeper.Beehive.Beehive;
import com.Beehive.beekeeper.Beehive.repository.BeehiveRepository;
import com.Beehive.beekeeper.Location.entities.Location;
import com.Beehive.beekeeper.Location.repositories.LocationRepository;
import com.Beehive.beekeeper.exceptions.LocationsNotValidException;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateLocationCommandHandler implements Command <Location, ResponseEntity>{

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private BeehiveRepository beehiveRepository;

   // private static final Logger logger = LoggerFactory.getLogger(CreateLocationCommandHandler.class);

    private void validateLocation (Location location){
        if (StringUtils.isBlank(location.getName())){
          //  logger.error("Location Name Not Valid " + location.toString());
            throw new LocationsNotValidException("Location's name can't be empty");
        }

        if (location.getBeehivecount() <=0){
          //  logger.error("Location Beehive Count Not Valid " + location.toString());
            throw new LocationsNotValidException("Location's beehive count can't be negative or 0");
        }
    }
/*
    @Override
    public ResponseEntity execute(Location location) {
        logger.info("Executing " + getClass() + " with " + location.toString());
        validateLocation(location);
        locationRepository.save(location);
        return ResponseEntity.ok().build();
    }*/
@Override
@Transactional
public ResponseEntity execute(Location location) {
    //logger.info("Executing " + getClass() + " with " + location.toString());
    validateLocation(location);

    Location savedLocation = locationRepository.save(location);
    List<Beehive> beehives = new ArrayList<>();
    for (int i = 0; i < savedLocation.getBeehivecount(); i++) {
        Beehive beehive = new Beehive();
        beehive.setLocation(savedLocation);
        beehive.setQueen(true);
        beehive.setDiseased(false);
        beehive.setPopulation(Beehive.PopulationDensity.low); // Adjust to match your Enum values
        beehives.add(beehive);
    }

    beehiveRepository.saveAll(beehives);
    return ResponseEntity.ok().build();
}
}
