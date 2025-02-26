package com.geofencemanagement.entity;

import java.time.LocalDateTime;

import com.geofencemanagement.base.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table (name = "vehicles_managements")
public class VehicleManagementEntity extends BaseEntity{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    @Column(nullable = false)
    private String model;  // Vehicle model	

    @Column(nullable = false)
    private String owner;  // Vehicle owner

    @Column(nullable = false)
    private String licensePlate;  // Vehicle license plate

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;  // Creation timestamp

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
