package eci.aygo.eciuber.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eci.aygo.eciuber.model.Driver;
import eci.aygo.eciuber.model.User;
import eci.aygo.eciuber.repository.DriverRepository;

@Service
public class DriverService {

	@Autowired
	private DriverRepository driverRepository;

	public List<Driver> getAllDrivers() {
		return (List<Driver>) driverRepository.findAll();
	}

	public Optional<User> getDriverById(String string) {
		return driverRepository.findById(string);
	}

	public Driver createDriver(Driver driver) {
		return driverRepository.save(driver);
	}

	public Driver updateDriver(Driver driver) {
		return driverRepository.save(driver);
	}

	public void deleteDriver(String id) {
		driverRepository.deleteById(id);
	}
}