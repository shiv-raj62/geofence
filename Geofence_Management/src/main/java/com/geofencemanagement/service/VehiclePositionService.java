package com.geofencemanagement.service;

import java.util.List;

import com.geofencemanagement.dto.VehiclePositionDTO;
import com.geofencemanagement.entity.VehiclePositionEntity;

public interface VehiclePositionService {

	  VehiclePositionEntity updateVehiclePosition(VehiclePositionDTO positionDTO);

	    List<VehiclePositionEntity> getVehiclePositionHistory(long id);

}
