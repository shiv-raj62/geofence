package com.geofencemanagement.controller;

import com.geofencemanagement.dto.VehiclePositionDTO;
import com.geofencemanagement.entity.VehiclePositionEntity;
import com.geofencemanagement.service.VehiclePositionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/positions")
public class VehiclePositionController {

    @Autowired
    private VehiclePositionService vehiclePositionService;

    @PostMapping
    public ResponseEntity<VehiclePositionEntity> updateVehiclePosition(@RequestBody VehiclePositionDTO positionDTO) {
        VehiclePositionEntity updatedPosition = vehiclePositionService.updateVehiclePosition(positionDTO);
        return ResponseEntity.ok(updatedPosition);
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<VehiclePositionEntity>> getVehiclePositionHistory(@PathVariable long id) {
        return ResponseEntity.ok(vehiclePositionService.getVehiclePositionHistory(id));
    }
}
