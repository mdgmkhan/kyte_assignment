package com.example.demo.service;

import com.example.demo.dto.DriverRideStatusDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.entity.Driver;
import com.example.demo.entity.DriverRideStatus;
import com.example.demo.entity.Session;

public interface DriverService {

	Driver signUp(Driver driver) throws Exception;

	Session login(LoginDto loginDto) throws Exception;

	DriverRideStatus statusUpdate(DriverRideStatusDto driverRideStatusDto) throws Exception;
}
