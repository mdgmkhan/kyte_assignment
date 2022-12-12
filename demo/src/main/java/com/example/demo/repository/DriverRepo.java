package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Driver;

public interface DriverRepo extends JpaRepository<Driver, Long> {

	@Query(value = "select * from Driver d where d.email=?1", nativeQuery = true)
	public Driver getByEmail(String email);

	@Query(value = "select * from Driver d where d.email=?1 and d.password=?2", nativeQuery = true)
	public Driver getByEmailAndPassword(String email, String password);

}
