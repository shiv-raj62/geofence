package com.geofencemanagement.controller;


import com.geofencemanagement.entity.VehicleManagementEntity;
import com.geofencemanagement.service.VehicleManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleManagementController {

    @Autowired
    private VehicleManagementService vehicleManagementService;

    @PostMapping
    public ResponseEntity<VehicleManagementEntity> createVehicle(@RequestBody VehicleManagementEntity vehicleEntity) {
        return ResponseEntity.ok(vehicleManagementService.createVehicle(vehicleEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleManagementEntity> getVehicleById(@PathVariable long id) {
        return ResponseEntity.ok(vehicleManagementService.getVehicleById(id));
    }

    @GetMapping
    public ResponseEntity<List<VehicleManagementEntity>> getAllVehicles() {
        return ResponseEntity.ok(vehicleManagementService.getAllVehicles());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        vehicleManagementService.deleteVehicle(id);
        return ResponseEntity.ok("Vehicle deleted successfully.");
    }
}
