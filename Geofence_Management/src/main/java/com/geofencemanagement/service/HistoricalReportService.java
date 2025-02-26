package com.geofencemanagement.service;

import com.geofencemanagement.entity.HistoricalReportEntity;

import java.util.List;

public interface HistoricalReportService {

    HistoricalReportEntity createHistoricalReport(HistoricalReportEntity reportEntity);

    List<HistoricalReportEntity> getReports( Long id,String geofenceName);
}
