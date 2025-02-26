package com.geofencemanagement.service;

import java.util.List;

import com.geofencemanagement.dto.GeofenceDTO;
import com.geofencemanagement.entity.GeofenceEntity;

public interface GeofenceService {


    GeofenceEntity createGeofence(GeofenceEntity geofenceEntity);

    GeofenceEntity getGeofenceById(Long id); // Fetch by ID

    GeofenceEntity getGeofenceByName(String name); // Fetch by Name

    List<GeofenceEntity> getAllGeofences();

    GeofenceEntity updateGeofence(Long id, GeofenceDTO geofenceDTO); // Update Geofence

    boolean isVehicleAuthorized(String geofenceName, long id);

    void deleteGeofence(Long geofenceId);
	

}
