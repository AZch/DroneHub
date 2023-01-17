package com.az.dronehub.handler;

import com.az.dronehub.dto.drone.DroneResponseDto;
import com.az.dronehub.dto.medication.MedicationLoadDto;

import java.util.List;

public interface DroneDeliveryHandler {
    DroneResponseDto loadDroneWithMedications(Long id, List<MedicationLoadDto> medications);

    DroneResponseDto sendDroneDelivery(Long id);

    DroneResponseDto droneDelivered(Long id);

    DroneResponseDto droneReturning(Long id);

    DroneResponseDto droneReturned(Long id);
}
