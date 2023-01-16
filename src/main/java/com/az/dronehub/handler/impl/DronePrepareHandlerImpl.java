package com.az.dronehub.handler.impl;

import com.az.dronehub.constants.DroneState;
import com.az.dronehub.dto.drone.DroneRegisterRequestDto;
import com.az.dronehub.dto.drone.DroneResponseDto;
import com.az.dronehub.entity.DroneEntity;
import com.az.dronehub.handler.DronePrepareHandler;
import com.az.dronehub.mapper.DroneMapper;
import com.az.dronehub.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DronePrepareHandlerImpl implements DronePrepareHandler {

    private final DroneRepository droneRepository;
    private final DroneMapper droneMapper;

    @Override
    public DroneResponseDto register(DroneRegisterRequestDto dto) {
        DroneEntity entity = droneMapper.toEntity(dto);
        entity.setState(DroneState.IDLE);
        entity.setBatteryCapacity(20);
        DroneEntity createdEntity = droneRepository.save(entity);
        return droneMapper.toDto(createdEntity);
    }

}
