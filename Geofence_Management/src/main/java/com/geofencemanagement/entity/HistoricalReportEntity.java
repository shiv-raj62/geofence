package com.geofencemanagement.entity;

import java.time.LocalDateTime;

import com.geofencemanagement.base.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Historical_Report ")
public class HistoricalReportEntity extends BaseEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   

    @Column(nullable = false)
    private String geofenceName;  // Geofence name


    @Column(name = "is_authorized")
    private boolean isAuthorized;
    
    @Column(nullable = false)
    private LocalDateTime entryTimestamp;  // Timestamp when the vehicle entered

    @Column(nullable = false)
    private LocalDateTime exitTimestamp;  // Timestamp when the vehicle exited

    @Column(nullable = false)
    private long durationInMinutes;  // Duration of stay in minutes

    @Column(nullable = false)
    private String authorizationStatus;  // Authorized/Unauthorized

    @Column(name = "alert_message")
    private String alertMessage;  
}
