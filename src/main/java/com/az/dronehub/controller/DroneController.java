package com.az.dronehub.controller;

import com.az.dronehub.dto.drone.DroneBatteryResponseDto;
import com.az.dronehub.dto.drone.DroneRegisterRequestDto;
import com.az.dronehub.dto.drone.DroneResponseDto;
import com.az.dronehub.dto.medication.MedicationLoadDto;
import com.az.dronehub.handler.DroneLoadHandler;
import com.az.dronehub.handler.DronePrepareHandler;
import com.az.dronehub.handler.DroneStateHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/drone")
@RequiredArgsConstructor
public class DroneController {

    private final DronePrepareHandler dronePrepareHandler;
    private final DroneStateHandler droneStateHandler;
    private final DroneLoadHandler droneLoadHandler;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public DroneResponseDto register(@Valid @RequestBody DroneRegisterRequestDto body) {
        return dronePrepareHandler.register(body);
    }

    @GetMapping("/battery/{id}")
    public DroneBatteryResponseDto batteryStatus(@PathVariable("id") Long id) {
        return droneStateHandler.getBatteryLevel(id);
    }

    @GetMapping("/available")
    public List<DroneResponseDto> getAvailableDrones() {
        return droneStateHandler.getAvailableDrones();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/load/{id}")
    public DroneResponseDto loadMedications(
        @PathVariable("id") Long id,
        @RequestBody List<MedicationLoadDto> medications
    ) {
        return droneLoadHandler.load(id, medications);
    }
}
