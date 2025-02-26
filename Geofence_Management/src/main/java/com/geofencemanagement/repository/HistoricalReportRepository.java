package com.geofencemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geofencemanagement.entity.HistoricalReportEntity;

public interface HistoricalReportRepository extends JpaRepository<HistoricalReportEntity, Long>  {
  //  HistoricalReportEntity findByVehicleId(long id);
    List<HistoricalReportEntity> findByGeofenceName(String geofenceName);
    List<HistoricalReportEntity> findByIdAndGeofenceName(Long id ,String geofenceName);

}
