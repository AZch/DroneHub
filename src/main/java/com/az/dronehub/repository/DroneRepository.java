package com.az.dronehub.repository;

import com.az.dronehub.constants.DroneState;
import com.az.dronehub.entity.DroneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<DroneEntity, Long> {

    List<DroneEntity> findAllByState(DroneState state);
}
