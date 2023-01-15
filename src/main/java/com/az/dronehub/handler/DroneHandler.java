package com.az.dronehub.handler;

import com.az.dronehub.dto.DroneBatteryResponseDto;
import com.az.dronehub.dto.DroneRegisterRequestDto;
import com.az.dronehub.dto.DroneRegisterResponseDto;

import java.util.Optional;

public interface DroneHandler {
    DroneRegisterResponseDto register(DroneRegisterRequestDto body);

    Optional<DroneBatteryResponseDto> getBatteryLevel(Long id);
}
