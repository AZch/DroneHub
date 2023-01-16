package com.az.dronehub.mapper;

import com.az.dronehub.dto.medication.MedicationLoadDto;
import com.az.dronehub.entity.MedicationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MedicationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "drone", ignore = true)
    MedicationEntity toEntity(MedicationLoadDto dto);
}
