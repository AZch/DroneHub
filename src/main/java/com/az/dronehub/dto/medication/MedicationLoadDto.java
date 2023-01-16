package com.az.dronehub.dto.medication;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record MedicationLoadDto (
    @NotEmpty @Pattern(regexp = "(a-z|A-Z|0-9|-|_)*") String name,
    @Min(1) int weightGr,
    @NotEmpty @Pattern(regexp = "(A-Z|0-9|-|_)*") String code,
    String image
){}
