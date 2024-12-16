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

import eci.aygo.eciuber.model.Rider;
import eci.aygo.eciuber.model.User;
import eci.aygo.eciuber.service.RiderService;

@RestController
@RequestMapping("/riders")
public class RiderController {

	@Autowired
	private RiderService riderService;

	@GetMapping
	public ResponseEntity<List<Rider>> getAllRiders() {
		List<Rider> riders = riderService.getAllRiders();
		return ResponseEntity.ok(riders);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getRiderById(@PathVariable("id") String id) {
		Optional<User> rider = riderService.getRiderById(id);
		return rider.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<User> createRider(@RequestBody Rider rider) {
		User createdRider = riderService.createRider(rider);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRider);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateRider(@PathVariable("id") String id, @RequestBody Rider rider) {
		rider.setId(id);
		User updatedRider = riderService.updateRider(rider);
		return ResponseEntity.ok(updatedRider);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRider(@PathVariable("id") String id) {
		riderService.deleteRider(id);
		return ResponseEntity.noContent().build();
	}
}