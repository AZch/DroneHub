package com.az.dronehub.controller;

import com.az.dronehub.dto.drone.DroneResponseDto;
import com.az.dronehub.dto.medication.MedicationLoadDto;
import com.az.dronehub.handler.DroneDeliveryHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DroneDeliveryHandler droneDeliveryHandler;

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/load/{id}")
    @ResponseBody
    public DroneResponseDto loadMedications(
        @PathVariable("id") Long id,
        @RequestBody @Valid List<MedicationLoadDto> medications
    ) {
        return droneDeliveryHandler.loadDroneWithMedications(id, medications);
    }

    @PutMapping("/send/{id}")
    @ResponseBody
    public DroneResponseDto sendDroneDelivery(@PathVariable("id") Long id) {
        return droneDeliveryHandler.sendDroneDelivery(id);
    }

    @PutMapping("/delivered/{id}")
    @ResponseBody
    public DroneResponseDto droneDelivered(@PathVariable("id") Long id) {
        return droneDeliveryHandler.droneDelivered(id);
    }

    @PutMapping("/returning/{id}")
    @ResponseBody
    public DroneResponseDto droneReturning(@PathVariable("id") Long id) {
        return droneDeliveryHandler.droneReturning(id);
    }

    @PutMapping("/returned/{id}")
    @ResponseBody
    public DroneResponseDto droneReturned(@PathVariable("id") Long id) {
        return droneDeliveryHandler.droneReturned(id);
    }
}
