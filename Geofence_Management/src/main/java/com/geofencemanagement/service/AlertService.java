package com.geofencemanagement.service;

import com.geofencemanagement.dto.AlertDTO;
import com.geofencemanagement.entity.AlertEntity;

import java.util.List;

public interface AlertService {

    AlertEntity createAlert(AlertDTO alertDTO);

    void generateUnauthorizedAccessAlert(long id, String geofenceName);

    void generateOverstayAlert(long id, String geofenceName);

    List<AlertEntity> getAllAlerts();

    AlertEntity getAlertsById(long id);

    List<AlertEntity> getAlertsByGeofenceName(String geofenceName);

    List<AlertEntity> getAlertsByVehicleAndGeofence(long id, String geofenceName);
}
