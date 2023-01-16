package com.az.dronehub.handler.impl;

import com.az.dronehub.constants.DroneState;
import com.az.dronehub.dto.drone.DroneResponseDto;
import com.az.dronehub.dto.medication.MedicationLoadDto;
import com.az.dronehub.entity.DroneEntity;
import com.az.dronehub.entity.MedicationEntity;
import com.az.dronehub.exceptions.EntityNotFoundException;
import com.az.dronehub.exceptions.IncorrectPropertyException;
import com.az.dronehub.handler.DroneLoadHandler;
import com.az.dronehub.mapper.DroneMapper;
import com.az.dronehub.mapper.MedicationMapper;
import com.az.dronehub.repository.DroneRepository;
import com.az.dronehub.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DroneLoadHandlerImpl implements DroneLoadHandler {

    private final DroneRepository droneRepository;
    private final DroneMapper droneMapper;
    private final MedicationRepository medicationRepository;
    private final MedicationMapper medicationMapper;

    @Override
    public DroneResponseDto loadDroneWithMedications(Long droneId, List<MedicationLoadDto> medications) {
        Optional<DroneEntity> entity = droneRepository.findById(droneId);
        if (entity.isEmpty()) {
            throw new EntityNotFoundException("Drone", "id", String.valueOf(droneId));
        }

        DroneEntity drone = entity.get();

        validateState(drone);
        validateBattery(drone);
        validateWeight(drone, medications);

        List<MedicationEntity> medicationEntities = medications.stream()
            .map(medicationMapper::toEntity)
            .peek(drone::addMedication)
            .toList();
        medicationRepository.saveAll(medicationEntities);
        DroneEntity updatedDrone = droneRepository.saveAndFlush(drone);
        return droneMapper.toDto(updatedDrone);
    }

    private void validateBattery(DroneEntity drone) {
        if (drone.getBatteryCapacity() < 25) {
            throw new IncorrectPropertyException("battery", "more then 25", drone.getBatteryCapacity());
        }
    }

    private void validateState(DroneEntity drone) {
        if (!drone.getState().equals(DroneState.IDLE)) {
            throw new IncorrectPropertyException("state", DroneState.IDLE.getState(), drone.getState().getState());
        }
    }

    private void validateWeight(DroneEntity drone, List<MedicationLoadDto> medications) {
        int medicationWeightSum = medications.stream().map(MedicationLoadDto::weightGr).reduce(0, Integer::sum);
        int alreadyLoadedWeightSum = drone.getMedications().stream().map(MedicationEntity::getWeightGr).reduce(0, Integer::sum);
        if (medicationWeightSum > drone.getWeightLimitGr() - alreadyLoadedWeightSum) {
            throw new IncorrectPropertyException("weight", "less then " + drone.getWeightLimitGr(), medicationWeightSum);
        }
    }
}
