package com.geofencemanagement.controller;

import com.geofencemanagement.dto.AlertDTO;
import com.geofencemanagement.entity.AlertEntity;
import com.geofencemanagement.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    // Endpoint to create a custom alert
    @PostMapping
    public ResponseEntity<AlertEntity> createAlert(@RequestBody AlertDTO alertDTO) {
        AlertEntity alertEntity = alertService.createAlert(alertDTO);
        return ResponseEntity.ok(alertEntity);
    }

    // Endpoint to generate Unauthorized Access Alert
    @PostMapping("/unauthorized")
    public ResponseEntity<Void> generateUnauthorizedAccessAlert(
            @RequestParam long id, 
            @RequestParam String geofenceName) {
        alertService.generateUnauthorizedAccessAlert(id, geofenceName);
        return ResponseEntity.ok().build();
    }

    // Endpoint to generate Overstay Alert
    @PostMapping("/overstay")
    public ResponseEntity<Void> generateOverstayAlert(
            @RequestParam long id, 
            @RequestParam String geofenceName) {
        alertService.generateOverstayAlert(id, geofenceName);
        return ResponseEntity.ok().build();
    }

    // Endpoint to fetch all alerts
    @GetMapping
    public ResponseEntity<List<AlertEntity>> getAllAlerts() {
        List<AlertEntity> alerts = alertService.getAllAlerts();
        return ResponseEntity.ok(alerts);
    }

    // Endpoint to fetch alerts by vehicle ID
    @GetMapping("/vehicle/{id}")
    public ResponseEntity<AlertEntity> getAlertsByVehicleId(@PathVariable long id) {
        AlertEntity alerts = alertService.getAlertsById(id);
        return ResponseEntity.ok(alerts);
    }

    // Endpoint to fetch alerts by geofence name
    @GetMapping("/geofence/{geofenceName}")
    public ResponseEntity<List<AlertEntity>> getAlertsByGeofenceName(@PathVariable String geofenceName) {
        List<AlertEntity> alerts = alertService.getAlertsByGeofenceName(geofenceName);
        return ResponseEntity.ok(alerts);
    }

    // Endpoint to fetch alerts by vehicle ID and geofence name
    @GetMapping("/vehicle/{id}/geofence/{geofenceName}")
    public ResponseEntity<List<AlertEntity>> getAlertsByVehicleAndGeofence(
            @PathVariable long id, 
            @PathVariable String geofenceName) {
        List<AlertEntity> alerts = alertService.getAlertsByVehicleAndGeofence(id, geofenceName);
        return ResponseEntity.ok(alerts);
    }
}
