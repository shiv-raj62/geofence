package com.geofencemanagement.dto;

import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlertDTO
{
	
	    private long vehicleId;
	
	    private String geofenceName;
	    private String alertType;
	    private LocalDateTime timestamp;
	   

}
