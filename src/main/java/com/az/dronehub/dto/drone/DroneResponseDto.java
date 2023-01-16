package com.az.dronehub.dto.drone;

import com.az.dronehub.constants.DroneModel;
import com.az.dronehub.constants.DroneState;
import com.az.dronehub.dto.medication.MedicationResponseDto;

import java.util.List;

public record DroneResponseDto (
    Long id,
    DroneModel model,
    String serialNumber,
    int weightLimitGr,
    int batteryCapacity,
    DroneState state,
    List<MedicationResponseDto> medications
) {}
