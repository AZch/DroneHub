package com.az.dronehub.dto.medication;

public record MedicationResponseDto(
    long id,
    String name,
    int weight,
    String code,
    String image
) {}
