package eci.aygo.eciuber.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eci.aygo.eciuber.model.Rider;
import eci.aygo.eciuber.model.User;
import eci.aygo.eciuber.repository.RiderRepository;

@Service
public class RiderService {

	@Autowired
	private RiderRepository riderRepository;

	public List<Rider> getAllRiders() {
		return (List<Rider>) riderRepository.findAll();
	}

	public Optional<User> getRiderById(String string) {
		return riderRepository.findById(string);
	}

	public User createRider(Rider rider) {
		return riderRepository.save(rider);
	}

	public User updateRider(Rider rider) {
		return riderRepository.save(rider);
	}

	public void deleteRider(String id) {
		riderRepository.deleteById(id);
	}
}
