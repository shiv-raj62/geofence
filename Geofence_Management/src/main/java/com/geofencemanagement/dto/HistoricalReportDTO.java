package com.geofencemanagement.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HistoricalReportDTO {

	private long id;
	private String geofenceName;
	private LocalDateTime entryTimestamp;
	private LocalDateTime exitTimestamp;
	private long durationInMinutes;
	private String authorizationStatus;
	private String alertMessage;
}
