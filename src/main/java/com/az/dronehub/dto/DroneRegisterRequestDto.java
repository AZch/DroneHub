package com.az.dronehub.dto;

import com.az.dronehub.constants.DroneModel;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Value
@Builder
public class DroneRegisterRequestDto {

    @NotEmpty
    private String serialNumber;

    @NotEmpty
    private DroneModel model;

    @NotEmpty
    @Max(500)
    private int weightLimitGr;
}
