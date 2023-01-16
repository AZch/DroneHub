package com.az.dronehub.dto.drone;

import com.az.dronehub.constants.DroneModel;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DroneRegisterRequestDto (
    @NotBlank(message = "serialNumber cant be blank") String serialNumber,
    @NotNull DroneModel model,
    @Min(1) @Max(500) int weightLimitGr
) {}
