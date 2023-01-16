package com.az.dronehub.repository;

import com.az.dronehub.constants.DroneState;
import com.az.dronehub.entity.DroneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DroneRepository extends JpaRepository<DroneEntity, Long> {

    @Query("SELECT D FROM DroneEntity AS D " +
        "LEFT JOIN MedicationEntity AS M ON M.drone = D.id " +
        "WHERE D.batteryCapacity > :batteryCapacity AND D.state IN (:states) " +
        "GROUP BY D.id " +
        "HAVING COUNT(M.id) = 0 OR D.weightLimitGr > SUM(M.weightGr)")
    List<DroneEntity> findAllAvailable(@Param("batteryCapacity") int batteryCapacity, @Param("states") List<DroneState> states);
}
