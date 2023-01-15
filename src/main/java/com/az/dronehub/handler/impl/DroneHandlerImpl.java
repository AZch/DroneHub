package com.az.dronehub.handler.impl;

import com.az.dronehub.constants.DroneState;
import com.az.dronehub.dto.DroneBatteryResponseDto;
import com.az.dronehub.dto.DroneRegisterRequestDto;
import com.az.dronehub.dto.DroneResponseDto;
import com.az.dronehub.entity.DroneEntity;
import com.az.dronehub.handler.DroneHandler;
import com.az.dronehub.mapper.DroneMapper;
import com.az.dronehub.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DroneHandlerImpl implements DroneHandler {

    private final DroneRepository droneRepository;
    private final DroneMapper mapper;

    @Override
    public DroneResponseDto register(DroneRegisterRequestDto dto) {
        DroneEntity entity = mapper.toEntity(dto);
        entity.setState(DroneState.IDLE);
        DroneEntity createdEntity = droneRepository.save(entity);
        return mapper.toDto(createdEntity);
    }

    @Override
    public Optional<DroneBatteryResponseDto> getBatteryLevel(Long id) {
        Optional<DroneEntity> entity = droneRepository.findById(id);
        if (entity.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(mapper.toBatteryResponse(entity.get()));
    }

    @Override
    public List<DroneResponseDto> getAvailableDrones() {
        List<DroneEntity> entities = droneRepository.findAllByState(DroneState.IDLE);
        return entities.stream().map(mapper::toDto).toList();
    }
}
