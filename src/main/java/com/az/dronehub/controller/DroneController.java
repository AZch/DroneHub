package com.az.dronehub.controller;

import com.az.dronehub.dto.drone.DroneBatteryResponseDto;
import com.az.dronehub.dto.drone.DroneRegisterRequestDto;
import com.az.dronehub.dto.drone.DroneResponseDto;
import com.az.dronehub.handler.DroneDeliveryHandler;
import com.az.dronehub.handler.DronePrepareHandler;
import com.az.dronehub.handler.DroneStateHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/drone")
@RequiredArgsConstructor
public class DroneController {

    private final DronePrepareHandler dronePrepareHandler;
    private final DroneStateHandler droneStateHandler;
    private final DroneDeliveryHandler droneDeliveryHandler;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    @ResponseBody
    public DroneResponseDto register(@RequestBody @Valid DroneRegisterRequestDto body) {
        return dronePrepareHandler.register(body);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public DroneResponseDto getDroneState(@PathVariable("id") Long id) {
        return droneStateHandler.getDrone(id);
    }

    @GetMapping("/battery/{id}")
    @ResponseBody
    public DroneBatteryResponseDto batteryStatus(@PathVariable("id") Long id) {
        return droneStateHandler.getBatteryLevel(id);
    }

    @GetMapping("/available")
    @ResponseBody
    public List<DroneResponseDto> getAvailableDrones() {
        return droneStateHandler.getAvailableDrones();
    }

}
