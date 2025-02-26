package com.geofencemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.geofencemanagement.entity.VehicleManagementEntity;

public interface VehicleManagementRepository  extends JpaRepository<VehicleManagementEntity, Long> {
	
	 VehicleManagementEntity findById(long id);
}
