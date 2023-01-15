package com.az.dronehub.mapper;

import com.az.dronehub.dto.DroneBatteryResponseDto;
import com.az.dronehub.dto.DroneRegisterRequestDto;
import com.az.dronehub.dto.DroneRegisterResponseDto;
import com.az.dronehub.entity.DroneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DroneMapper {

    DroneMapper INSTANCE = Mappers.getMapper( DroneMapper.class );

    DroneRegisterResponseDto toRegisterResponse(DroneEntity entity);

    DroneEntity toEntity(DroneRegisterRequestDto dto);

    @Mapping(source = "batteryCapacity", target = "capacity")
    DroneBatteryResponseDto toBatteryResponse(DroneEntity entity);
}
