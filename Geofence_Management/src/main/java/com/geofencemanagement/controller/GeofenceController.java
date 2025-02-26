package com.geofencemanagement.controller;

import com.geofencemanagement.dto.GeofenceDTO;
import com.geofencemanagement.entity.GeofenceEntity;
import com.geofencemanagement.service.GeofenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/geofences")
public class GeofenceController {

    @Autowired
    private GeofenceService geofenceService;

    @PostMapping
    public ResponseEntity<GeofenceEntity> createGeofence(@RequestBody GeofenceEntity geofenceDTO) {
        GeofenceEntity geofence = geofenceService.createGeofence(geofenceDTO);
        return ResponseEntity.ok(geofence);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeofenceEntity> getGeofenceById(@PathVariable Long id) {
        return ResponseEntity.ok(geofenceService.getGeofenceById(id)); // Fetch by ID
    }

    @GetMapping
    public ResponseEntity<List<GeofenceEntity>> getAllGeofences() {
        return ResponseEntity.ok(geofenceService.getAllGeofences());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeofenceEntity> updateGeofence(@PathVariable Long id, @RequestBody GeofenceDTO geofenceDTO) {
        return ResponseEntity.ok(geofenceService.updateGeofence(id, geofenceDTO)); // Update Geofence
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGeofence(@PathVariable Long id) {
        geofenceService.deleteGeofence(id);
        return ResponseEntity.ok("Geofence deleted successfully.");
    }
}
