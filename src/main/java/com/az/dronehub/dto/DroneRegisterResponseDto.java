package com.az.dronehub.dto;

import com.az.dronehub.constants.DroneModel;
import com.az.dronehub.constants.DroneState;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DroneRegisterResponseDto {
    Long id;

    DroneModel model;

    int weightLimitGr;

    int batteryCapacity;

    DroneState state;
}
