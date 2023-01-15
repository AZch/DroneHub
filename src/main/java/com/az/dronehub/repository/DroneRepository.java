package com.az.dronehub.repository;

import com.az.dronehub.entity.DroneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<DroneEntity, Long> {
}
