package com.az.dronehub.handler;

import com.az.dronehub.dto.drone.DroneRegisterRequestDto;
import com.az.dronehub.dto.drone.DroneResponseDto;

public interface DronePrepareHandler {
    DroneResponseDto register(DroneRegisterRequestDto body);

}
