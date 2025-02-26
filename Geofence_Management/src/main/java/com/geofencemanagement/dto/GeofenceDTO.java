package com.geofencemanagement.dto;

import java.util.ArrayList;
import java.util.List;

import com.geofencemanagement.coordinate.dto.Coordinate;

import jakarta.persistence.ElementCollection;
import lombok.Data;

@Data
public class GeofenceDTO {
	
	private String name;
	@ElementCollection
	private List<Coordinate> polygon = new ArrayList<>(); // Polygon as a string representation
	private List<String> authorizedVehicleIds;

}
