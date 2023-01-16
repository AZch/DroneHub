package com.az.dronehub.handler.impl;

import com.az.dronehub.constants.DroneState;
import com.az.dronehub.dto.drone.DroneBatteryResponseDto;
import com.az.dronehub.dto.drone.DroneResponseDto;
import com.az.dronehub.entity.DroneEntity;
import com.az.dronehub.exceptions.EntityNotFoundException;
import com.az.dronehub.handler.DroneStateHandler;
import com.az.dronehub.mapper.DroneMapper;
import com.az.dronehub.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DroneStateHandlerImpl implements DroneStateHandler {

    private final DroneRepository droneRepository;
    private final DroneMapper droneMapper;

    @Override
    public DroneBatteryResponseDto getBatteryLevel(Long id) {
        Optional<DroneEntity> entity = droneRepository.findById(id);
        if (entity.isEmpty()) {
            throw new EntityNotFoundException("Drone", "id", String.valueOf(id));
        }

        return droneMapper.toBatteryResponse(entity.get());
    }

    @Override
    public List<DroneResponseDto> getAvailableDrones() {
        List<DroneEntity> entities = droneRepository.findAllAvailable(25, DroneState.IDLE);
        return entities.stream().map(droneMapper::toDto).toList();
    }

    @Override
    public DroneResponseDto getDrone(Long id) {
        Optional<DroneEntity> entity = droneRepository.findById(id);
        if (entity.isEmpty()) {
            throw new EntityNotFoundException("Drone", "id", String.valueOf(id));
        }

        return droneMapper.toDto(entity.get());
    }
}
