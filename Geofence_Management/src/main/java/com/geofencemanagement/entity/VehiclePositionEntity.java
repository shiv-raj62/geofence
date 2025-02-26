package com.geofencemanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle_positions")
public class VehiclePositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "geofence_name", nullable = false)
    private String geofenceName;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "entry_timestamp")
    private LocalDateTime entryTimestamp;

    @Column(name = "exit_timestamp")
    private LocalDateTime exitTimestamp;

    @Column(name = "duration_in_minutes")
    private Long durationInMinutes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mappingvehicle_id", referencedColumnName = "id", nullable = false)
    private VehicleEntity vehicle;

    @PrePersist
    public void prePersist() {
        if (timestamp == null) {
            timestamp = LocalDateTime.now();
        }
    }

    public VehiclePositionEntity(VehicleEntity vehicle, String geofenceName) {
        this.vehicle = vehicle;
        this.geofenceName = geofenceName;
    }
}
