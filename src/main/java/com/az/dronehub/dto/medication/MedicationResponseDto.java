package com.az.dronehub.dto.medication;

public record MedicationResponseDto(
    long id,
    String name,
    int weightGr,
    String code,
    String image
) {}
