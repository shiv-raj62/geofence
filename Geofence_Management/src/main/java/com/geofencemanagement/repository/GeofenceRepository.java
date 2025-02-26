package com.geofencemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geofencemanagement.entity.GeofenceEntity;

public interface GeofenceRepository extends JpaRepository<GeofenceEntity, Long> {

	public GeofenceEntity findByName(String name); 
}
