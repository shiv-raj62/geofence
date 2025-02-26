package com.geofencemanagement.service.impl;

import com.geofencemanagement.dto.GeofenceDTO;
import com.geofencemanagement.entity.GeofenceEntity;
import com.geofencemanagement.repository.GeofenceRepository;
import com.geofencemanagement.service.GeofenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeofenceServiceImpl implements GeofenceService {

    @Autowired
    private GeofenceRepository geofenceRepository;

    @Override
    public GeofenceEntity createGeofence(GeofenceEntity geofenceEntity) {
        return geofenceRepository.save(geofenceEntity);
    }

    @Override
    public GeofenceEntity getGeofenceById(Long id) {
        return geofenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Geofence not found with id: " + id));
    }

    @Override
    public GeofenceEntity getGeofenceByName(String name) {
        return geofenceRepository.findByName(name);
                //.orElseThrow(() -> new RuntimeException("Geofence not found with name: " + name));
    }

    @Override
    public List<GeofenceEntity> getAllGeofences() {
        return geofenceRepository.findAll();
    }

    @Override
    public boolean isVehicleAuthorized(String geofenceName, long id) {
        GeofenceEntity geofence = getGeofenceByName(geofenceName);
        return geofence.getAuthorizedVehicleIds().contains(id);
    }

    @Override
    public void deleteGeofence(Long geofenceId) {
        geofenceRepository.deleteById(geofenceId);
    }

	@Override
	public GeofenceEntity updateGeofence(Long id, GeofenceDTO geofenceDTO) {
		// TODO Auto-generated method stub
	    // Step 1: Fetch the existing Geofence by ID
        GeofenceEntity existingGeofence = geofenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Geofence not found with id: " + id));

        // Step 2: Update the fields of the existing Geofence with values from the GeofenceDTO
        existingGeofence.setName(geofenceDTO.getName());
        existingGeofence.setPolygon(geofenceDTO.getPolygon());
        existingGeofence.setAuthorizedVehicleIds(geofenceDTO.getAuthorizedVehicleIds());

        // Step 3: Save the updated Geofence entity to the database
        return geofenceRepository.save(existingGeofence);	}


}
