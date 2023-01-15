package com.az.dronehub.handler;

import com.az.dronehub.dto.DroneBatteryResponseDto;
import com.az.dronehub.dto.DroneRegisterRequestDto;
import com.az.dronehub.dto.DroneResponseDto;

import java.util.List;
import java.util.Optional;

public interface DroneHandler {
    DroneResponseDto register(DroneRegisterRequestDto body);

    Optional<DroneBatteryResponseDto> getBatteryLevel(Long id);

    List<DroneResponseDto> getAvailableDrones();
}
