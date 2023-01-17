package com.az.dronehub.entity;

import com.az.dronehub.constants.DroneModel;
import com.az.dronehub.constants.DroneState;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drone")
@Data
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
    @Enumerated(value = EnumType.STRING)
    private DroneModel model;

    @Column(name = "weight_limit_gr")
    private int weightLimitGr;

    @Column(name = "battery_capacity", columnDefinition = "integer")
    private int batteryCapacity;

    @Column(name = "drone_state", columnDefinition = "varchar(50)")
    @Enumerated(value = EnumType.STRING)
    private DroneState state;

    // For simplest way i just use one to many relation,
    // but in real word we should define goods
    @OneToMany(mappedBy = "drone", cascade =
        CascadeType.ALL, orphanRemoval = true)
    private List<MedicationEntity> medications = new ArrayList<>();

    public void addMedication(MedicationEntity entity) {
        entity.setDrone(this);
        this.medications.add(entity);
    }
}
