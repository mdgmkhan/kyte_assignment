package com.example.demo.serviceimpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DriverRideStatusDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.entity.Driver;
import com.example.demo.entity.DriverRideStatus;
import com.example.demo.entity.Session;
import com.example.demo.enums.CommonStatus;
import com.example.demo.repository.DriverRepo;
import com.example.demo.repository.DriverRideStatusRepo;
import com.example.demo.repository.SessionRepo;
import com.example.demo.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	DriverRepo driverRepo;

	@Autowired
	SessionRepo sessionRepo;

	@Autowired
	DriverRideStatusRepo driverRideStatusRepo;

	@Override
	public Driver signUp(Driver driver) throws Exception {
		if (!validateDriver(driver)) {
			throw new Exception("Invalid driver data!");
		}

		Driver driverData = driverRepo.getByEmail(driver.getEmail());
		if (driverData != null)
			throw new Exception("Driver already exists with email : " + driver.getEmail());

		Driver driverNew = new Driver();
		driverNew.setAddress(driver.getAddress());
		driverNew.setEmail(driver.getEmail());
		driverNew.setFirstName(driver.getFirstName());
		driverNew.setLastName(driver.getLastName());
		driverNew.setLicenseNumber(driver.getLicenseNumber());
		driverNew.setMobile(driver.getMobile());
		driverNew.setPassword(driver.getPassword());
		driverNew.setUsername(driver.getUsername());
		driverNew.setVehicleNumber(driver.getVehicleNumber());
		driverNew.setCreatedAt(System.currentTimeMillis());
		driverNew.setModifiedAt(System.currentTimeMillis());
		driverNew.setStatus(CommonStatus.ACTIVE);

		return driverRepo.save(driverNew);
	}

	private boolean validateDriver(Driver driver) {
		if (driver.getEmail() != null && !driver.getEmail().isEmpty() && driver.getFirstName() != null
				&& !driver.getFirstName().isEmpty() && driver.getLastName() != null && !driver.getLastName().isEmpty()
				&& driver.getLicenseNumber() != null && !driver.getLicenseNumber().isEmpty()
				&& driver.getMobile() != null && !driver.getMobile().isEmpty() && driver.getPassword() != null
				&& !driver.getPassword().isEmpty() && driver.getUsername() != null && !driver.getUsername().isEmpty()
				&& driver.getVehicleNumber() != null && !driver.getVehicleNumber().isEmpty())
			return true;
		else
			return false;
	}

	@Override
	public Session login(LoginDto loginDto) throws Exception {
		if (!validateLoginInput(loginDto)) {
			throw new Exception("Invalid login data!");
		}

		Driver driverData = driverRepo.getByEmail(loginDto.getEmail());

		if (driverData == null)
			throw new Exception("User doesn't exist!");

		if (!driverData.getPassword().equals(loginDto.getPassword()))
			throw new Exception("Incorrect Password!");

		Session sessionData = sessionRepo.getSessionByDriverAndDeviceAndStatusActive(driverData.getId(),
				loginDto.getDevice());
		if (sessionData != null) {
			sessionData.setStatus(CommonStatus.INACTIVE);
			sessionData.setModifiedAt(System.currentTimeMillis());
			sessionRepo.save(sessionData);
		}

		Session session = new Session();
		session.setDriver(driverData);
		session.setDevice(loginDto.getDevice());
		session.setSessionId(UUID.randomUUID().toString());
		session.setStatus(CommonStatus.ACTIVE);
		session.setCreatedAt(System.currentTimeMillis());
		session.setModifiedAt(System.currentTimeMillis());

		return sessionRepo.save(session);
	}

	private boolean validateLoginInput(LoginDto loginDto) {
		if (loginDto.getPassword() != null && !loginDto.getPassword().isEmpty() && loginDto.getDevice() != null
				&& !loginDto.getDevice().isEmpty() && loginDto.getEmail() != null && !loginDto.getEmail().isEmpty())
			return true;
		else
			return false;
	}

	@Override
	public DriverRideStatus statusUpdate(DriverRideStatusDto driverRideStatusDto) throws Exception {
		if (!validateDriverRideStatusInput(driverRideStatusDto)) {
			throw new Exception("Invalid input data!");
		}
		// handle entries from same location
		DriverRideStatus driverRideStatusData = driverRideStatusRepo.getDriverRideStatusByDriverIdAndLocation(
				driverRideStatusDto.getDriverId(), driverRideStatusDto.getLocation());
		if (driverRideStatusData != null) {
			// make existing entries from same location as UNAVAILABLE and create fresh entry
			driverRideStatusData.setStatus(CommonStatus.UNAVAILABLE);
			driverRideStatusData.setModifiedAt(System.currentTimeMillis());
			driverRideStatusRepo.save(driverRideStatusData);
		}

		DriverRideStatus driverRideStatusDataNew = new DriverRideStatus();
		driverRideStatusDataNew.setDriver(driverRepo.findById(driverRideStatusDto.getDriverId()).get());
		driverRideStatusDataNew.setLocation(driverRideStatusDto.getLocation());
		driverRideStatusDataNew.setTime(driverRideStatusDto.getTime());
		driverRideStatusDataNew
				.setStatus(driverRideStatusDto.getAvailability() ? CommonStatus.AVAILABLE : CommonStatus.UNAVAILABLE);
		driverRideStatusDataNew.setCreatedAt(System.currentTimeMillis());
		driverRideStatusDataNew.setModifiedAt(System.currentTimeMillis());

		return driverRideStatusRepo.save(driverRideStatusDataNew);
	}

	private boolean validateDriverRideStatusInput(DriverRideStatusDto driverRideStatusDto) {
		if (driverRideStatusDto.getDriverId() != null && driverRideStatusDto.getLocation() != null
				&& driverRideStatusDto.getAvailability() != null)
			return true;
		else
			return false;
	}
}
