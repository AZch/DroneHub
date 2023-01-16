package com.az.dronehub.handler;

import com.az.dronehub.dto.drone.DroneResponseDto;
import com.az.dronehub.dto.medication.MedicationLoadDto;

import java.util.List;

public interface DroneLoadHandler {
    DroneResponseDto loadDroneWithMedications(Long id, List<MedicationLoadDto> medications);
}
