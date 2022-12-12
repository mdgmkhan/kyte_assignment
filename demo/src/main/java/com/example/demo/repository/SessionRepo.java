package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Session;

public interface SessionRepo extends JpaRepository<Session, Long> {

	@Query(value = "select * from session s where s.driver_id=?1 and s.device=?2 and s.status='ACTIVE'", nativeQuery = true)
	Session getSessionByDriverAndDeviceAndStatusActive(Long driverId, String device);
}
