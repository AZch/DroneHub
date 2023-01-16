package com.az.dronehub.handler.impl;

import com.az.dronehub.constants.DroneState;
import com.az.dronehub.dto.drone.DroneResponseDto;
import com.az.dronehub.dto.medication.MedicationLoadDto;
import com.az.dronehub.entity.DroneEntity;
import com.az.dronehub.entity.MedicationEntity;
import com.az.dronehub.handler.DroneLoadHandler;
import com.az.dronehub.mapper.DroneMapper;
import com.az.dronehub.mapper.MedicationMapper;
import com.az.dronehub.repository.DroneRepository;
import com.az.dronehub.repository.MedicationRepository;
import com.az.dronehub.service.DroneValidationService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static java.lang.Thread.sleep;

@Service
@RequiredArgsConstructor
public class DroneLoadHandlerImpl implements DroneLoadHandler {

    private final DroneRepository droneRepository;
    private final DroneMapper droneMapper;
    private final MedicationRepository medicationRepository;
    private final MedicationMapper medicationMapper;

    private final DroneValidationService droneValidationService;

    private final Random random = new Random();

    @SneakyThrows
    @Override
    public DroneResponseDto loadDroneWithMedications(Long droneId, List<MedicationLoadDto> medications) {
        Optional<DroneEntity> entity = droneRepository.findById(droneId);

        DroneEntity drone = droneValidationService.getValidDroneEntityById(droneId, entity);

        droneValidationService.validateState(drone);
        droneValidationService.validateBattery(drone);
        droneValidationService.validateWeight(drone, medications);

        drone.setState(DroneState.LOADING);
        droneRepository.save(drone);

        // simulating drone loading
        sleep(random.nextInt(10) * 1000);

        List<MedicationEntity> medicationEntities = medications.stream()
            .map(medicationMapper::toEntity)
            .peek(drone::addMedication)
            .toList();
        drone.setState(DroneState.LOADED);

        medicationRepository.saveAll(medicationEntities);
        DroneEntity updatedDrone = droneRepository.save(drone);
        return droneMapper.toDto(updatedDrone);
    }
}
