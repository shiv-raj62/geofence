package com.geofencemanagement.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geofencemanagement.entity.VehiclePositionEntity;

public interface VehiclePositionRepository  extends JpaRepository<VehiclePositionEntity, Long>{
	  List<VehiclePositionEntity> findById(long id);
	  List<VehiclePositionEntity> findByIdAndTimestampBetween(long id, LocalDateTime start, LocalDateTime end);
//	VehiclePositionEntity findByVehicleIdAndGeofenceName(String vehicleId, String geofenceName);
	Optional<VehiclePositionEntity> findByIdAndGeofenceName(long id, String geofenceName);


}
