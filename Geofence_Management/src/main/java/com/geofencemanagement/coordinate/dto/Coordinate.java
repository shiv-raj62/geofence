package com.geofencemanagement.coordinate.dto;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Coordinate {

    private double latitude;
    private double longitude;

   
}
