package com.geofencemanagement.base.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	 @Column(name = "created_date", nullable = false, updatable = false)
	    private LocalDateTime createdDate;

	    @Column(name = "updated_date",nullable = false, updatable = false)
	    private LocalDateTime updatedDate;

}
