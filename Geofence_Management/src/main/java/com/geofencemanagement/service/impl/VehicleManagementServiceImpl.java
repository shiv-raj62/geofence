package com.geofencemanagement.service.impl;

import com.geofencemanagement.service.VehicleManagementService;

import com.geofencemanagement.entity.VehicleManagementEntity;
import com.geofencemanagement.repository.VehicleManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehicleManagementServiceImpl implements VehicleManagementService {

    @Autowired
    private VehicleManagementRepository vehicleRepository;

    @Override
    public VehicleManagementEntity createVehicle(VehicleManagementEntity vehicleEntity) {
        return vehicleRepository.save(vehicleEntity);
    }

    @Override
    public VehicleManagementEntity getVehicleById(long id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public List<VehicleManagementEntity> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}
