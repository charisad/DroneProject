package com.collins.droneproject.repositories;

import com.collins.droneproject.entities.Drone;
import com.collins.droneproject.enums.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends CrudRepository<Drone, Long> {
    List<Drone> findByState(State state);
}
