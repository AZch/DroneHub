package com.az.dronehub.service;

import com.az.dronehub.constants.DroneState;
import com.az.dronehub.dto.medication.MedicationLoadDto;
import com.az.dronehub.entity.DroneEntity;
import com.az.dronehub.entity.MedicationEntity;
import com.az.dronehub.exceptions.EntityNotFoundException;
import com.az.dronehub.exceptions.IncorrectPropertyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneValidationService {

    public DroneEntity getValidDroneEntityById(Long id, Optional<DroneEntity> droneEntityOptional) {
        if (droneEntityOptional.isEmpty()) {
            throw new EntityNotFoundException("Drone", "id", String.valueOf(id));
        }
        return droneEntityOptional.get();
    }

    public void validateBattery(DroneEntity drone) {
        if (drone.getBatteryCapacity() < 25) {
            throw new IncorrectPropertyException("battery", "more then 25", drone.getBatteryCapacity());
        }
    }

    public void validateState(DroneEntity drone) {
        if (!drone.getState().equals(DroneState.IDLE)) {
            throw new IncorrectPropertyException("state", DroneState.IDLE.getState(), drone.getState().getState());
        }
    }

    public void validateWeight(DroneEntity drone, List<MedicationLoadDto> medications) {
        int medicationWeightSum = medications.stream().map(MedicationLoadDto::weightGr).reduce(0, Integer::sum);
        int alreadyLoadedWeightSum = drone.getMedications().stream().map(MedicationEntity::getWeightGr).reduce(0, Integer::sum);
        if (medicationWeightSum > drone.getWeightLimitGr() - alreadyLoadedWeightSum) {
            throw new IncorrectPropertyException("weight", "less then " + drone.getWeightLimitGr(), medicationWeightSum);
        }
    }
}
