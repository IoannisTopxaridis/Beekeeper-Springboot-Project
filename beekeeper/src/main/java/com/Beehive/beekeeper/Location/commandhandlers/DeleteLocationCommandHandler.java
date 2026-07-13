package com.Beehive.beekeeper.Location.commandhandlers;

import com.Beehive.beekeeper.Location.entities.Location;
import com.Beehive.beekeeper.Location.repositories.LocationRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteLocationCommandHandler implements Command<Integer, ResponseEntity>{

    @Autowired private LocationRepository locationRepository;


    @Override
    public ResponseEntity<ResponseEntity> execute(Integer id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location wasn't found with id: "+ id));
        locationRepository.delete(location);
        System.out.println("Location: " + location.getName() + " with id: "+ location.getId()+" deleted!");
        return ResponseEntity.ok().build();
    }
}
