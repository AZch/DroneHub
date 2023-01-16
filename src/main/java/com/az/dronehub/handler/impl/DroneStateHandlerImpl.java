package com.az.dronehub.handler.impl;

import com.az.dronehub.constants.DroneState;
import com.az.dronehub.dto.drone.DroneBatteryResponseDto;
import com.az.dronehub.dto.drone.DroneResponseDto;
import com.az.dronehub.entity.DroneEntity;
import com.az.dronehub.handler.DroneStateHandler;
import com.az.dronehub.mapper.DroneMapper;
import com.az.dronehub.repository.DroneRepository;
import com.az.dronehub.service.DroneValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DroneStateHandlerImpl implements DroneStateHandler {

    private final DroneRepository droneRepository;
    private final DroneMapper droneMapper;

    private final DroneValidationService droneValidationService;

    @Override
    public DroneBatteryResponseDto getBatteryLevel(Long id) {
        Optional<DroneEntity> entity = droneRepository.findById(id);
        DroneEntity drone = droneValidationService.getValidDroneEntityById(id, entity);
        return droneMapper.toBatteryResponse(drone);
    }

    @Override
    public List<DroneResponseDto> getAvailableDrones() {
        List<DroneEntity> entities = droneRepository.findAllAvailable(25, List.of(DroneState.IDLE, DroneState.LOADED));
        return entities.stream().map(droneMapper::toDto).toList();
    }

    @Override
    public DroneResponseDto getDrone(Long id) {
        Optional<DroneEntity> entity = droneRepository.findById(id);
        DroneEntity drone = droneValidationService.getValidDroneEntityById(id, entity);
        return droneMapper.toDto(drone);
    }
}
