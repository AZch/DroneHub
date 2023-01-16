package com.az.dronehub.handler;

import com.az.dronehub.dto.drone.DroneBatteryResponseDto;
import com.az.dronehub.dto.drone.DroneResponseDto;

import java.util.List;

public interface DroneStateHandler {
    DroneBatteryResponseDto getBatteryLevel(Long id);

    List<DroneResponseDto> getAvailableDrones();

}
