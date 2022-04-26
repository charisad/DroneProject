package com.collins.droneproject.repositories;

import com.collins.droneproject.entities.DroneBatteryAudit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneBatteryAuditRepository extends CrudRepository<DroneBatteryAudit, Long> {
}
