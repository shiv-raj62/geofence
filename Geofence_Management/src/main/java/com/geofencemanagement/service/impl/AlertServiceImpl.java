package com.geofencemanagement.service.impl;

import com.geofencemanagement.dto.AlertDTO;
import com.geofencemanagement.entity.AlertEntity;
import com.geofencemanagement.repository.AlertRepository;
import com.geofencemanagement.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Override
    public AlertEntity createAlert(AlertDTO alertDTO) {
        AlertEntity alertEntity = new AlertEntity();
        alertEntity.setGeofenceName(alertDTO.getGeofenceName());
        alertEntity.setAlertType(alertDTO.getAlertType());
        alertEntity.setTimestamp(alertDTO.getTimestamp());
        return alertRepository.save(alertEntity);
    }

    @Override
    public void generateUnauthorizedAccessAlert(long id, String geofenceName) {
        AlertDTO alertDTO = new AlertDTO(id, geofenceName, "Unauthorized Access", LocalDateTime.now());
        createAlert(alertDTO);
    }

    @Override
    public void generateOverstayAlert(long id, String geofenceName) {
        AlertDTO alertDTO = new AlertDTO(id, geofenceName, "Overstay", LocalDateTime.now());
        createAlert(alertDTO);
    }

    @Override
    public List<AlertEntity> getAllAlerts() {
        return alertRepository.findAll();
    }

    @Override
    public AlertEntity getAlertsById(long id) {
        return alertRepository.findById(id).get();
    }

    @Override
    public List<AlertEntity> getAlertsByGeofenceName(String geofenceName) {
        return alertRepository.findByGeofenceName(geofenceName);
    }

    @Override
    public List<AlertEntity> getAlertsByVehicleAndGeofence(long id, String geofenceName) {
        return alertRepository.findByIdAndGeofenceName(id, geofenceName);
    }
}
