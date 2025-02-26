package com.geofencemanagement.service.impl;

import com.geofencemanagement.entity.HistoricalReportEntity;
import com.geofencemanagement.repository.HistoricalReportRepository;
import com.geofencemanagement.service.HistoricalReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricalReportServiceImpl implements HistoricalReportService {

    @Autowired
    private HistoricalReportRepository historicalReportRepository;

    @Override
    public HistoricalReportEntity createHistoricalReport(HistoricalReportEntity reportEntity) {
        return historicalReportRepository.save(reportEntity);
    }

    @Override
    public List<HistoricalReportEntity> getReports(Long id, String geofenceName) {
        return historicalReportRepository.findByIdAndGeofenceName(id, geofenceName);
    }
}
