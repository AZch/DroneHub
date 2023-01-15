package com.az.dronehub.entity;

import com.az.dronehub.constants.DroneModel;
import com.az.dronehub.constants.DroneState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drone")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DroneEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "model")
    private DroneModel model;

    @Column(name = "weight_limit_gr")
    private int weightLimitGr;

    @Column(name = "battery_capacity")
    private int batteryCapacity;

    @Column(name = "drone_state")
    private DroneState state;
}
