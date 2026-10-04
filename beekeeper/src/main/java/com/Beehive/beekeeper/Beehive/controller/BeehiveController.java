package com.Beehive.beekeeper.Beehive.controller;

import com.Beehive.beekeeper.Beehive.Beehive;
import com.Beehive.beekeeper.Beehive.BeehiveDTO;
import com.Beehive.beekeeper.Beehive.commandhandlers.CreateBeehiveCommandHandler;
import com.Beehive.beekeeper.Beehive.repository.BeehiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/beehive")
@CrossOrigin(origins = "*")
public class BeehiveController {

    @Autowired
    BeehiveRepository beehiveRepository;

    @Autowired
    CreateBeehiveCommandHandler createBeehiveCommandHandler;

    @GetMapping("/{id}")
    public ResponseEntity<BeehiveDTO> getBeehiveById(@PathVariable Long id) {
        return beehiveRepository.findById(id)
                .map(beehive -> ResponseEntity.ok(new BeehiveDTO(beehive)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BeehiveDTO> updateBeehive(@PathVariable Long id, @RequestBody BeehiveDTO updatedDto) {
        return beehiveRepository.findById(id).map(beehive -> {
            if (updatedDto.getQueen() != null) {
                beehive.setQueen(updatedDto.getQueen());
            }
            if (updatedDto.getDiseased() != null) {
                beehive.setDiseased(updatedDto.getDiseased());
            }
            if (updatedDto.getPopulationDensity() != null) {
                beehive.setPopulation(updatedDto.getPopulationDensity());
            }

            Beehive saved = beehiveRepository.save(beehive);
            return ResponseEntity.ok(new BeehiveDTO(saved));
        }).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/location/{locationId}")
    public ResponseEntity<List<BeehiveDTO>> getBeehivesByLocation(@PathVariable Integer locationId) {
        List<Beehive> beehives = beehiveRepository.findByLocation_Id(locationId);

        List<BeehiveDTO> beehiveDTOs = beehives.stream()
                .map(BeehiveDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(beehiveDTOs);
    }

    @PostMapping("/create")
    public ResponseEntity createBeehive (@RequestBody Beehive beehive){
        return createBeehiveCommandHandler.execute(beehive);
    }

    


}
