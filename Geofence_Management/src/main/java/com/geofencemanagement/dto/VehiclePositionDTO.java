package com.geofencemanagement.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehiclePositionDTO {

	private Long id;
	private double latitude;
	private double longitude;
	private LocalDateTime timestamp;
	private String geofenceName;
	  private LocalDateTime entryTimestamp;

    
    private LocalDateTime exitTimestamp;

   
    private Long durationInMinutes;

}
