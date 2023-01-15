package com.az.dronehub.controller;

import com.az.dronehub.dto.DroneBatteryResponseDto;
import com.az.dronehub.dto.DroneRegisterRequestDto;
import com.az.dronehub.dto.DroneResponseDto;
import com.az.dronehub.handler.DroneHandler;
import com.az.dronehub.mapper.DroneMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drone")
@RequiredArgsConstructor
public class DroneController {

    private final DroneHandler droneHandler;
    private final DroneMapper mapper;

    @PostMapping("/register")
    public DroneResponseDto register(@RequestBody DroneRegisterRequestDto body) {
        return droneHandler.register(body);
    }

    @GetMapping("/battery/{id}")
    public DroneBatteryResponseDto batteryStatus(@PathVariable("id") Long id) throws Exception {
        Optional<DroneBatteryResponseDto> dto = droneHandler.getBatteryLevel(id);
        if (dto.isEmpty()) {
            throw new Exception("Not found");
        }
        return dto.get();
    }

    @GetMapping("/available")
    public List<DroneResponseDto> getAvailableDrones() {
        return droneHandler.getAvailableDrones();
    }
}
