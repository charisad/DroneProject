package com.collins.droneproject.component;

import com.collins.droneproject.entities.Drone;
import com.collins.droneproject.enums.Model;
import com.collins.droneproject.enums.State;
import com.collins.droneproject.repositories.DroneRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PreLoadDB {
    private DroneRepository droneRepository;

    public PreLoadDB(DroneRepository droneRepository){
        this.droneRepository = droneRepository;
    }


    @PostConstruct
    private void postConstruct() {
        //add 10 drones
        for(int i = 0; i < 10; i++){
            droneRepository.save(new Drone("1234567890" + i, Model.LightWeight, 500, 100, State.IDLE));
        }
    }
    
}
