package eci.aygo.eciuber.event;

import eci.aygo.eciuber.event.type.RideEventType;
import eci.aygo.eciuber.model.Location;

public class RideEvent extends BaseEvent {

	private final String rideId;
	private final String userId;
	private final String driverId;
	private final RideEventType type;
	private final Location location;

	public RideEvent(String rideId, String userId, String driverId, RideEventType type, Location location) {

		super(type.toString());

		this.rideId = rideId;
		this.userId = userId;
		this.driverId = driverId;
		this.type = type;
		this.location = location;
	}

	// getters and setters
	
	public String getRideId() {
		return rideId;
	}

	public String getUserId() {
		return userId;
	}

	public String getDriverId() {
		return driverId;
	}

	public RideEventType getType() {
		return type;
	}

	public Location getLocation() {
		return location;
	}

}
