package com.geofencemanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.geofencemanagement.base.entity.BaseEntity;
import com.geofencemanagement.coordinate.dto.Coordinate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "geofence_managements")
public class GeofenceEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    
    @ElementCollection
    @CollectionTable(name = "geofence_polygon", joinColumns = @JoinColumn(name = "geofence_id"))
    @Column(name = "coordinate")
    private List<Coordinate> polygon = new ArrayList<>();

   
    @ManyToMany
    @JoinTable(
            name = "geofence_authorized_vehicles", 
            joinColumns = @JoinColumn(name = "geofence_id"),  // Reference the 'id' of GeofenceEntity
            inverseJoinColumns = @JoinColumn(name = "id")  // Reference the 'id' of VehicleEntity
    )
    private List<VehicleEntity> authorizedVehicleIds = new ArrayList<>();


    @Column(nullable = false)
    private long maxAllowedDuration;  // Max allowed duration for vehicles in the geofence

    public void setPolygon(List<Coordinate> polygon) {
        this.polygon = polygon; // Set the polygon (list of coordinates)
    }

    public List<Coordinate> getPolygon() {
        return this.polygon; // Get the polygon (list of coordinates)
    }

	public void setAuthorizedVehicleIds(List<String> authorizedVehicleIds2) {
		// TODO Auto-generated method stub
		
	}
	
	 @Column(nullable = false, updatable = false)
	    private LocalDateTime createdDate;
	    
	    @Column(nullable = false, updatable = false)
	    private LocalDateTime updatedDate;


	    @PrePersist
	    public void prePersist() {
	        if (this.createdDate == null) {
	            this.createdDate = LocalDateTime.now(); // Set the creation time to now if not already set
	        }
	        if (this.updatedDate == null) {
	            this.updatedDate = LocalDateTime.now(); // Set the creation time to now if not already set
	        }
	        
	    }

}
