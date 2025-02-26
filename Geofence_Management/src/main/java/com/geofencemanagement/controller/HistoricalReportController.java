package com.geofencemanagement.controller;

import com.geofencemanagement.dto.HistoricalReportDTO;
import com.geofencemanagement.entity.HistoricalReportEntity;
import com.geofencemanagement.service.HistoricalReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reports")
public class HistoricalReportController {

    @Autowired
    private HistoricalReportService historicalReportService;

    // Endpoint to get historical reports for a specific vehicle and geofence
    @GetMapping("/{id}/{geofenceName}")
    public ResponseEntity<List<HistoricalReportDTO>> getHistoricalReportByVehicleIdAndGeofenceName(
        @PathVariable Long id,        
    @PathVariable String geofenceName) {

        List<HistoricalReportEntity> reports = historicalReportService.getReports(id, geofenceName);

        List<HistoricalReportDTO> reportDTOs = reports.stream()
                .map(report -> new HistoricalReportDTO(report.getId(), report.getGeofenceName(),
                        report.getEntryTimestamp(), report.getExitTimestamp(), report.getDurationInMinutes(),
                        report.isAuthorized() ? "Authorized" : "Unauthorized", report.getAlertMessage()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(reportDTOs);
    }

    // Endpoint to create a historical report
    @PostMapping
    public ResponseEntity<HistoricalReportDTO> createHistoricalReport(@RequestBody HistoricalReportEntity reportEntity) {
        HistoricalReportEntity savedReport = historicalReportService.createHistoricalReport(reportEntity);

        HistoricalReportDTO reportDTO = new HistoricalReportDTO(savedReport.getId(), savedReport.getGeofenceName(),
                savedReport.getEntryTimestamp(), savedReport.getExitTimestamp(), savedReport.getDurationInMinutes(),
                savedReport.isAuthorized() ? "Authorized" : "Unauthorized", savedReport.getAlertMessage());

        return ResponseEntity.ok(reportDTO);
    }
}
