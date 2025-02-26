package com.geofencemanagement.service.impl;

import com.geofencemanagement.coordinate.dto.Coordinate;
import com.geofencemanagement.dto.VehiclePositionDTO;
import com.geofencemanagement.entity.GeofenceEntity;
import com.geofencemanagement.entity.VehicleEntity;
import com.geofencemanagement.entity.VehiclePositionEntity;
import com.geofencemanagement.repository.VehiclePositionRepository;
import com.geofencemanagement.repository.VehicleRepository;
import com.geofencemanagement.service.AlertService;
import com.geofencemanagement.service.GeofenceService;
import com.geofencemanagement.service.VehiclePositionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VehiclePositionServiceImpl implements VehiclePositionService {

    @Autowired
    private VehiclePositionRepository vehiclePositionRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private GeofenceService geofenceService;

    @Autowired
    private AlertService alertService;

    @Override
    public VehiclePositionEntity updateVehiclePosition(VehiclePositionDTO positionDTO) {
        // Retrieve vehicle by ID
        VehicleEntity vehicle = vehicleRepository.findById(positionDTO.getId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + positionDTO.getId()));

        // Retrieve geofence by name
        GeofenceEntity geofence = geofenceService.getGeofenceByName(positionDTO.getGeofenceName());
        if (geofence == null) {
            throw new RuntimeException("Geofence not found with name: " + positionDTO.getGeofenceName());
        }

        // Retrieve or lazily initialize the vehicle position
        VehiclePositionEntity positionEntity = vehiclePositionRepository
                .findByIdAndGeofenceName(vehicle.getId(), positionDTO.getGeofenceName())
                .orElseGet(() -> new VehiclePositionEntity(vehicle, positionDTO.getGeofenceName()));

        // Update position details
        positionEntity.setLatitude(positionDTO.getLatitude());
        positionEntity.setLongitude(positionDTO.getLongitude());
        positionEntity.setTimestamp(positionDTO.getTimestamp());
        positionEntity.setEntryTimestamp(positionDTO.getEntryTimestamp());
        positionEntity.setExitTimestamp(positionDTO.getExitTimestamp());
        positionEntity.setDurationInMinutes(positionDTO.getDurationInMinutes());
        

        // Check if inside geofence
        boolean isInside = isInsideGeofence(positionDTO.getLatitude(), positionDTO.getLongitude(), geofence);

        if (isInside) {
            handleGeofenceEntry(positionEntity, vehicle, geofence);
        } else {
            handleGeofenceExit(positionEntity, vehicle, geofence);
        }

        // Save and return the position entity
        return vehiclePositionRepository.save(positionEntity);
    }

    private void handleGeofenceEntry(VehiclePositionEntity positionEntity, VehicleEntity vehicle, GeofenceEntity geofence) {
        if (positionEntity.getEntryTimestamp() == null) {
            positionEntity.setEntryTimestamp(LocalDateTime.now());
        }

        boolean isAuthorized = geofenceService.isVehicleAuthorized(geofence.getName(), vehicle.getId());
        if (!isAuthorized) {
            alertService.generateUnauthorizedAccessAlert(vehicle.getId(), geofence.getName());
        }
    }

    private void handleGeofenceExit(VehiclePositionEntity positionEntity, VehicleEntity vehicle, GeofenceEntity geofence) {
        if (positionEntity.getEntryTimestamp() != null) {
            positionEntity.setExitTimestamp(LocalDateTime.now());
            long duration = calculateDurationInMinutes(positionEntity.getEntryTimestamp(), positionEntity.getExitTimestamp());
            positionEntity.setDurationInMinutes(duration);

            if (duration > geofence.getMaxAllowedDuration()) {
                alertService.generateOverstayAlert(vehicle.getId(), geofence.getName());
            }

            positionEntity.setEntryTimestamp(null);
        }
    }

    private boolean isInsideGeofence(double latitude, double longitude, GeofenceEntity geofence) {
        List<Coordinate> polygon = geofence.getPolygon();
        if (polygon.size() < 3) {
            return false;
        }

        boolean inside = false;
        for (int i = 0, j = polygon.size() - 1; i < polygon.size(); j = i++) {
            double lat1 = polygon.get(i).getLatitude();
            double lon1 = polygon.get(i).getLongitude();
            double lat2 = polygon.get(j).getLatitude();
            double lon2 = polygon.get(j).getLongitude();

            if ((lon1 > longitude) != (lon2 > longitude) &&
                    latitude < (lat2 - lat1) * (longitude - lon1) / (lon2 - lon1) + lat1) {
                inside = !inside;
            }
        }
        return inside;
    }

    private long calculateDurationInMinutes(LocalDateTime entryTime, LocalDateTime exitTime) {
        return Duration.between(entryTime, exitTime).toMinutes();
    }

    @Override
    public List<VehiclePositionEntity> getVehiclePositionHistory(long id) {
        return vehiclePositionRepository.findById(id);
    }
}
