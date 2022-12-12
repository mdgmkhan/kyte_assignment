package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.LocationDto;
import com.example.demo.entity.DriverRideStatus;

public interface DriverRideStatusRepo extends JpaRepository<DriverRideStatus, Long> {

	@Query(value = "select * from driver_ride_status s where s.driver_id=?1 and JSON_CONTAINS(s.location,location) and s.status='AVAILABLE'", nativeQuery = true)
	DriverRideStatus getDriverRideStatusByDriverIdAndLocation(Long driverId, LocationDto location);

}
