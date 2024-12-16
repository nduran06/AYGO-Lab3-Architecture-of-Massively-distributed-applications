package eci.aygo.eciuber.event.type;

public enum RideEventType {

	// Initial request
	REQUESTED("Ride requested by user"),

	// Driver matching phase
	SEARCHING_DRIVER("Looking for nearby drivers"), NO_DRIVERS_AVAILABLE("No drivers available"),
	DRIVER_MATCHED("Driver found and matched"), DRIVER_ACCEPTED("Driver accepted the ride"),
	DRIVER_DECLINED("Driver declined the ride"),

	// Ride start phase
	DRIVER_ARRIVED("Driver arrived at pickup"), RIDER_PICKUP_CONFIRMED("Rider confirmed pickup"),
	RIDE_STARTED("Ride started"),

	// During ride
	ROUTE_UPDATED("Route has been updated"), DESTINATION_UPDATED("Destination changed"),

	// Ride completion
	RIDE_COMPLETED("Ride completed"), PAYMENT_INITIATED("Payment process started"),
	PAYMENT_COMPLETED("Payment successful"),

	// Cancellations
	CANCELLED_BY_RIDER("Cancelled by rider"), CANCELLED_BY_DRIVER("Cancelled by driver"),
	SYSTEM_CANCELLED("Cancelled by system"),

	// Issues/Emergency
	EMERGENCY_TRIGGERED("Emergency help requested"), ROUTE_DEVIATION("Significant route deviation detected");

	private final String description;

	RideEventType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
