package com.Beehive.beekeeper.Beehive.commandhandlers;

import com.Beehive.beekeeper.Beehive.Beehive;
import com.Beehive.beekeeper.Beehive.repository.BeehiveRepository;
import com.Beehive.beekeeper.Location.entities.Location;
import com.Beehive.beekeeper.exceptions.LocationsNotValidException;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CreateBeehiveCommandHandler implements Command <Beehive, ResponseEntity> {

    @Autowired
    private BeehiveRepository beehiveRepository;

    public ResponseEntity execute (Beehive beehive){
        beehiveRepository.save(beehive);
        return ResponseEntity.ok().build();
    }

}
