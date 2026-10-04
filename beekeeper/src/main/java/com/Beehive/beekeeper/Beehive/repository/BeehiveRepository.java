package com.Beehive.beekeeper.Beehive.repository;

import com.Beehive.beekeeper.Beehive.Beehive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeehiveRepository extends JpaRepository<Beehive, Long> {
    List<Beehive> findByLocation_Id(Integer locationId);
}