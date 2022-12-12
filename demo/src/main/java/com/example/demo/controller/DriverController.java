package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DriverRideStatusDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.entity.Driver;
import com.example.demo.entity.DriverRideStatus;
import com.example.demo.entity.Session;
import com.example.demo.service.DriverService;

@RestController
@RequestMapping("/driver")
public class DriverController {

	private static Logger log = LoggerFactory.getLogger(DriverController.class);

	@Autowired
	DriverService driverService;

	@GetMapping({ "/healthCheck", "/healthcheck" })
	@ResponseBody
	public String test() {
		return "Driver Onboarding Service is up!";
	}

	@PostMapping("/signUp")
	public Driver signUp(@RequestBody Driver driver) throws Exception {
		return driverService.signUp(driver);
	}

	@PostMapping("/login")
	public Session login(@RequestBody LoginDto loginDto) throws Exception {
		return driverService.login(loginDto);
	}

	@PostMapping("/status")
	public DriverRideStatus statusUpdate(@RequestBody DriverRideStatusDto driverRideStatusDto) throws Exception {
		return driverService.statusUpdate(driverRideStatusDto);
	}

}
