package com.az.dronehub.handler;

import com.az.dronehub.entity.DroneEntity;
import com.az.dronehub.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class DroneCheckHandler {

    private final DroneRepository droneRepository;

    private final Random random = new Random();

    @Scheduled(fixedDelay = 20000)
    public void DronesHealthCheck() {
        List<DroneEntity> drones = mockingRequestDroneStates();
        droneRepository.saveAll(drones);
    }

    // just mocking request to external system for this information
    // i think we also need self model for that external system, but for now we can use just entity
    private List<DroneEntity> mockingRequestDroneStates() {
        List<DroneEntity> drones = droneRepository.findAll();
        drones.forEach(drone -> {
            int oldDroneBattery = drone.getBatteryCapacity();
            drone.setBatteryCapacity(random.nextInt(100 + 1));
            String now = new Date().toString();
            log.info(String.format(
                "%s : Drone %s change battery capacity from %s -> %s",
                now, drone.getId(), oldDroneBattery, drone.getBatteryCapacity()
            ));
        });
        return drones;
    }
}
