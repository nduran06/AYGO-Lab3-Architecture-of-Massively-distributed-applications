package eci.aygo.eciuber.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eci.aygo.eciuber.model.Driver;
import eci.aygo.eciuber.model.User;
import eci.aygo.eciuber.service.DriverService;

@RestController
@RequestMapping("/drivers")
public class DriverController {

	@Autowired
	private DriverService driverService;

	@GetMapping
	public ResponseEntity<List<Driver>> getAllDrivers() {
		List<Driver> drivers = driverService.getAllDrivers();
		return ResponseEntity.ok(drivers);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getDriverById(@PathVariable("id") String id) {
		Optional<User> driver = driverService.getDriverById(id);
		return driver.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
		Driver createdDriver = driverService.createDriver(driver);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDriver);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Driver> updateDriver(@PathVariable("id") String id, @RequestBody Driver driver) {
		driver.setId(id);
		Driver updatedDriver = driverService.updateDriver(driver);
		return ResponseEntity.ok(updatedDriver);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDriver(@PathVariable("id") String id) {
		driverService.deleteDriver(id);
		return ResponseEntity.noContent().build();
	}
}