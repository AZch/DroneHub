package com.az.dronehub.service;

import com.az.dronehub.config.AvailabilityConfig;
import com.az.dronehub.constants.DroneState;
import com.az.dronehub.dto.medication.MedicationLoadDto;
import com.az.dronehub.entity.DroneEntity;
import com.az.dronehub.entity.MedicationEntity;
import com.az.dronehub.exceptions.EntityNotFoundException;
import com.az.dronehub.exceptions.IncorrectPropertyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DroneValidationService {

    private final AvailabilityConfig availabilityConfig;

    public DroneEntity getValidDroneEntityById(Long id, Optional<DroneEntity> droneEntityOptional) {
        if (droneEntityOptional.isEmpty()) {
            throw new EntityNotFoundException("Drone", "id", String.valueOf(id));
        }
        return droneEntityOptional.get();
    }

    public void validateBattery(DroneEntity drone) {
        if (drone.getBatteryCapacity() < availabilityConfig.getMinimalBattery()) {
            throw new IncorrectPropertyException(
                "battery",
                String.format("more then %s", availabilityConfig.getMinimalBattery()),
                drone.getBatteryCapacity()
            );
        }
    }

    public void validateStateForLoading(DroneEntity drone) {
        validateState(drone, availabilityConfig.getAvailableStates());
    }

    public void validateStateForDelivery(DroneEntity drone) {
        if (!drone.getState().equals(DroneState.LOADED)) {
            throw new IncorrectPropertyException(
                "state",
                String.format("should be %s", DroneState.LOADED),
                drone.getState().getState()
            );
        }
    }

    public void validateStateForDelivered(DroneEntity drone) {
        if (!drone.getState().equals(DroneState.LOADED)) {
            throw new IncorrectPropertyException(
                "state",
                String.format("should be %s", DroneState.LOADED),
                drone.getState().getState()
            );
        }
    }

    public void validateStateForReturn(DroneEntity drone) {
        if (!drone.getState().equals(DroneState.LOADED)) {
            throw new IncorrectPropertyException(
                "state",
                String.format("should be %s", DroneState.LOADED),
                drone.getState().getState()
            );
        }
    }

    public void validateStateForReturning(DroneEntity drone) {
        if (!drone.getState().equals(DroneState.LOADED)) {
            throw new IncorrectPropertyException(
                "state",
                String.format("should be %s", DroneState.LOADED),
                drone.getState().getState()
            );
        }
    }

    public void validateWeight(DroneEntity drone, List<MedicationLoadDto> medications) {
        int medicationWeightSum = medications.stream().map(MedicationLoadDto::weightGr).reduce(0, Integer::sum);
        int alreadyLoadedWeightSum = drone.getMedications().stream().map(MedicationEntity::getWeightGr).reduce(0, Integer::sum);
        if (medicationWeightSum > drone.getWeightLimitGr() - alreadyLoadedWeightSum) {
            throw new IncorrectPropertyException("weight", "less then " + drone.getWeightLimitGr(), medicationWeightSum);
        }
    }

    public void validateState(DroneEntity drone, List<DroneState> availableStates) {
        if (!availableStates.contains(drone.getState())) {
            String possibleStates = availableStates.stream()
                .map(DroneState::getState)
                .collect(Collectors.joining(", "));
            throw new IncorrectPropertyException(
                "state",
                String.format("should be %s", possibleStates),
                drone.getState().getState()
            );
        }
    }
}
