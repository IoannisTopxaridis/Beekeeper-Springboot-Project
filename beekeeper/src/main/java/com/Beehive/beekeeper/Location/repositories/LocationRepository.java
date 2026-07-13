package com.Beehive.beekeeper.Location.repositories;

import com.Beehive.beekeeper.Location.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface LocationRepository extends JpaRepository<Location, Integer> {
}
