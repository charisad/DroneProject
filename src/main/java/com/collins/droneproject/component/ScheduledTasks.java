package com.collins.droneproject.component;

import com.collins.droneproject.entities.Drone;
import com.collins.droneproject.entities.DroneBatteryAudit;
import com.collins.droneproject.repositories.DroneBatteryAuditRepository;
import com.collins.droneproject.repositories.DroneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private final DroneRepository droneRepository;
    private final DroneBatteryAuditRepository droneBatteryAuditRepository;
    private boolean auditDroneBatteryStatus = false;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    public ScheduledTasks(DroneRepository droneRepository, DroneBatteryAuditRepository droneBatteryAuditRepository) {
        this.droneRepository = droneRepository;
        this.droneBatteryAuditRepository = droneBatteryAuditRepository;
    }

    @Scheduled(fixedRate = 10000)
    public void auditDroneBattery() {
        log.info("Running Drone Audit Log at {}", dateFormat.format(new Date()));
        if(!auditDroneBatteryStatus){
            auditDroneBatteryStatus = true;
            Iterable<Drone> all = droneRepository.findAll();
            List<DroneBatteryAudit> droneBatteryAuditList = new ArrayList<>();
            all.forEach(drone -> droneBatteryAuditList.add(new DroneBatteryAudit(drone)));
            droneBatteryAuditRepository.saveAll(droneBatteryAuditList);
            auditDroneBatteryStatus = false;
        }
    }
}
