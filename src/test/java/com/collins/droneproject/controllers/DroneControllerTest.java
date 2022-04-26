package com.collins.droneproject.controllers;

import com.collins.droneproject.dto.DroneDTO;
import com.collins.droneproject.dto.MedicationDTO;
import com.collins.droneproject.enums.Model;
import com.collins.droneproject.enums.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DroneControllerTest {

    @Autowired
    private DroneController controller;

    public DroneDTO getIdleDroneDTO(){
        return new DroneDTO("12345678", Model.LightWeight, 500, 100, State.IDLE);
    }

    public MedicationDTO getMedicationDTO(){
        return new MedicationDTO("drone1", 500, "drone_100gms", "shorturl.at/fiAEM");
    }


    @Test
    public void contextLoads() throws Exception {
        assertNotNull(controller);
    }

    @Test
    void register() {
        DroneDTO expectedDrone = getIdleDroneDTO();
        ResponseEntity<DroneDTO> registeredDrone = controller.register(expectedDrone);
        DroneDTO actualDrone = registeredDrone.getBody();

        assertNotNull(actualDrone);
        assertEquals(expectedDrone.getSerialNumber(), actualDrone.getSerialNumber());
    }

    @Test
    void loadDrone() {
        MedicationDTO expectedMedicationDTO = getMedicationDTO();
        assertDoesNotThrow(() -> {
            ResponseEntity<MedicationDTO> medicationDTOResponseEntity = controller.loadDrone(1L, expectedMedicationDTO);
            assertEquals(HttpStatus.OK, medicationDTOResponseEntity.getStatusCode());

            MedicationDTO body = medicationDTOResponseEntity.getBody();
            assertNotNull(body);
            assertNotNull(body.getDrone());
        });
    }

    @Test
    void dronesAvailable() {
        assertDoesNotThrow(() -> {
            ResponseEntity<List<DroneDTO>> droneDTOResponseEntity = controller.dronesAvailable();
            assertEquals(HttpStatus.OK, droneDTOResponseEntity.getStatusCode());

            List<DroneDTO> droneDTO = droneDTOResponseEntity.getBody();
            assertNotNull(droneDTO);
        });
    }

    @Test
    void getDrone() {
        assertDoesNotThrow(() -> {
            ResponseEntity<DroneDTO> droneDTOResponseEntity = controller.getDrone(1L);
            assertEquals(HttpStatus.OK, droneDTOResponseEntity.getStatusCode());

            DroneDTO droneDTO = droneDTOResponseEntity.getBody();
            assertNotNull(droneDTO);
        });
    }
}