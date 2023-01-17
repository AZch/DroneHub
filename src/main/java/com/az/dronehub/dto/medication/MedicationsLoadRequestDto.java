package com.az.dronehub.dto.medication;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record MedicationsLoadRequestDto(
    @NotEmpty List<@Valid MedicationLoadDto> medications
) {}
