package com.az.dronehub.mapper;

import com.az.dronehub.dto.DroneBatteryResponseDto;
import com.az.dronehub.dto.DroneRegisterRequestDto;
import com.az.dronehub.dto.DroneResponseDto;
import com.az.dronehub.entity.DroneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DroneMapper {

    DroneResponseDto toDto(DroneEntity entity);

    DroneEntity toEntity(DroneRegisterRequestDto dto);

    @Mapping(source = "batteryCapacity", target = "capacity")
    DroneBatteryResponseDto toBatteryResponse(DroneEntity entity);
}
