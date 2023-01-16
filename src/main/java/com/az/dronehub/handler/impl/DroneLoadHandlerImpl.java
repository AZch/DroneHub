package com.az.dronehub.handler.impl;

import com.az.dronehub.constants.DroneState;
import com.az.dronehub.dto.drone.DroneResponseDto;
import com.az.dronehub.dto.medication.MedicationLoadDto;
import com.az.dronehub.entity.DroneEntity;
import com.az.dronehub.exceptions.EntityNotFoundException;
import com.az.dronehub.exceptions.IncorrectPropertyException;
import com.az.dronehub.handler.DroneLoadHandler;
import com.az.dronehub.mapper.DroneMapper;
import com.az.dronehub.mapper.MedicationMapper;
import com.az.dronehub.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DroneLoadHandlerImpl implements DroneLoadHandler {

    private final DroneRepository droneRepository;
    private final DroneMapper droneMapper;
    private final MedicationMapper medicationMapper;

    @Override
    public DroneResponseDto load(Long droneId, List<MedicationLoadDto> medications) {
        Optional<DroneEntity> entity = droneRepository.findById(droneId);
        if (entity.isEmpty()) {
            throw new EntityNotFoundException("Drone", "id", String.valueOf(droneId));
        }

        DroneEntity drone = entity.get();
        if (!drone.getState().equals(DroneState.IDLE)) {
            throw new IncorrectPropertyException("state", DroneState.IDLE.getState(), drone.getState().getState());
        }

        if (drone.getBatteryCapacity() == 0) {
            throw new IncorrectPropertyException("battery", "more then 0", drone.getBatteryCapacity());
        }

        int medicationWeightSum = medications.stream().map(MedicationLoadDto::getWeight).reduce(0, Integer::sum);
        if (medicationWeightSum > drone.getWeightLimitGr()) {
            throw new IncorrectPropertyException("weight", "less then " + drone.getWeightLimitGr(), medicationWeightSum);
        }

        medications.stream().map(medicationMapper::toEntity).forEach(drone::addMedication);
        DroneEntity updatedDrone = droneRepository.save(drone);
        return droneMapper.toDto(updatedDrone);
    }
}
