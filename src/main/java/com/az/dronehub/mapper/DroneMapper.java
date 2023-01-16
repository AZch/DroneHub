package com.az.dronehub.mapper;

import com.az.dronehub.dto.drone.DroneBatteryResponseDto;
import com.az.dronehub.dto.drone.DroneRegisterRequestDto;
import com.az.dronehub.dto.drone.DroneResponseDto;
import com.az.dronehub.entity.DroneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface DroneMapper {

    DroneResponseDto toDto(DroneEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "batteryCapacity", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "medications", ignore = true)
    DroneEntity toEntity(DroneRegisterRequestDto dto);

    @Mapping(target = "capacity", source = "batteryCapacity")
    DroneBatteryResponseDto toBatteryResponse(DroneEntity entity);
}
