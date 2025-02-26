package com.geofencemanagement.service;

import java.util.List;


import com.geofencemanagement.entity.VehicleManagementEntity;

public interface VehicleManagementService {
	VehicleManagementEntity createVehicle(VehicleManagementEntity vehicleEntity);

	VehicleManagementEntity getVehicleById(long id);

	List<VehicleManagementEntity> getAllVehicles();

	void deleteVehicle(Long id);

}
