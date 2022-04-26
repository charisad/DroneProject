package com.collins.droneproject.repositories;

import com.collins.droneproject.entities.Medication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends CrudRepository<Medication, Long> {
        List<Medication> findByDrone_Id(long id);
}
