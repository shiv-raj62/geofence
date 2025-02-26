package com.geofencemanagement.repository;

import com.geofencemanagement.entity.AlertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlertRepository extends JpaRepository<AlertEntity, Long> {

    Optional<AlertEntity> findById(Long id);

    List<AlertEntity> findByGeofenceName(String geofenceName);

    List<AlertEntity> findByIdAndGeofenceName(Long id, String geofenceName);

}
