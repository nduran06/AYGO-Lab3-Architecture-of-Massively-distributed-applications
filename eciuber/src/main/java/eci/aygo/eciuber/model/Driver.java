package eci.aygo.eciuber.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import eci.aygo.eciuber.config.serializer.CurrencySerializer;
import eci.aygo.eciuber.model.enums.DriverStatus;
import eci.aygo.eciuber.model.enums.UserType;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
@JsonIgnoreProperties(ignoreUnknown = true)
public class Driver extends User {

	private DriverLicense driverLicense;
	private VehicleInfo vehicleInfo;
	private Location currentLocation;
	private Integer totalTrips;
	private BigDecimal earnings;

	public Driver(String id, String name, String email, String password, String phone, Double rating,
			LocalDateTime createdTime, LocalDateTime lastUpdateTime, DriverLicense driverLicense,
			VehicleInfo vehicleInfo, Location currentLocation, Integer totalTrips, BigDecimal earnings) {

		super(id, name, email, password, phone, rating, UserType.DRIVER, createdTime, lastUpdateTime);

		this.driverLicense = driverLicense;
		this.vehicleInfo = vehicleInfo;
		this.currentLocation = currentLocation;
		this.totalTrips = totalTrips;
		this.earnings = earnings;
		this.defaultStatus();
	}

	public Driver(String id, String name, String email, String password, String phone, Double rating, UserType type,
			LocalDateTime createdTime, LocalDateTime lastUpdateTime, DriverLicense driverLicense,
			VehicleInfo vehicleInfo, Location currentLocation, Integer totalTrips, BigDecimal earnings,
			DriverStatus status) {

		super(id, name, email, password, phone, rating, type, createdTime, lastUpdateTime);

		this.driverLicense = driverLicense;
		this.vehicleInfo = vehicleInfo;
		this.currentLocation = currentLocation;
		this.totalTrips = totalTrips;
		this.earnings = earnings;
		this.setStatus(status);
	}

	public Driver() {
		super();
		this.setType(UserType.DRIVER);
		this.defaultStatus();
	}

	// getters and setters

	@Override
	public void defaultStatus() {
		this.setStatus(DriverStatus.AVAILABLE);
	}
	
	@DynamoDbAttribute("driverLicense")
	public DriverLicense getDriverLicense() {
		return this.driverLicense;
	}

	public void setDriverLicense(DriverLicense driverLicense) {
		this.driverLicense = driverLicense;
	}

	@DynamoDbAttribute("vehicleInfo")
	public VehicleInfo getVehicleInfo() {
		return this.vehicleInfo;
	}

	public void setVehicleInfo(VehicleInfo vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}

	@DynamoDbAttribute("currentLocation")
	public Location getCurrentLocation() {
		return this.currentLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	@DynamoDbAttribute("totalTrips")
	public Integer getTotalTrips() {
		return this.totalTrips;
	}

	public void setTotalTrips(Integer totalTrips) {
		this.totalTrips = totalTrips;
	}

	@DynamoDbAttribute("earnings")
	@JsonSerialize(using = CurrencySerializer.class)
	public BigDecimal getEarnings() {
		return this.earnings;
	}

	public void setEarnings(BigDecimal earnings) {
		this.earnings = earnings;
	}

	@Override
	public void beforeWrite() {
		// TODO Auto-generated method stub
		
	}
}
