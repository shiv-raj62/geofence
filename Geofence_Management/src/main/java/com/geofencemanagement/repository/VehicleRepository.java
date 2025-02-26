package com.geofencemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geofencemanagement.entity.VehicleEntity;


public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
	public Optional<VehicleEntity> findById(long id);

}
