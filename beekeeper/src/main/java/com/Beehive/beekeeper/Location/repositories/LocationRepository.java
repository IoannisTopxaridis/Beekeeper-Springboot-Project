package com.Beehive.beekeeper.Location.repositories;

import com.Beehive.beekeeper.Location.entities.Location;
import com.Beehive.beekeeper.Location.entities.LocationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface LocationRepository extends JpaRepository<Location, Integer> {
    //@Query SQL statment
    //(value = "SELECT ...>= :mostBeehives", nativeQuery = true) for using only THIS DB
    @Query("SELECT p FROM Location p WHERE beehivecount >= :mostBeehives")
    List<Location> findLocationsWithBeehiveCountLessThan(@Param("mostBeehives") Integer mostBeehives);

    @Query("SELECT new com.Beehive.beekeeper.Location.entities.LocationDTO(l.id, l.name, l.beehivecount) FROM Location l")
    List<LocationDTO> getAllLocationDTO();

    List<Location> findByNameContaining(String keyword);


}