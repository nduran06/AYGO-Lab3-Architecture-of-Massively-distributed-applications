package eci.aygo.eciuber.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import eci.aygo.eciuber.config.serializer.CurrencySerializer;
import eci.aygo.eciuber.config.serializer.LocalDateTimeSerializer;
import eci.aygo.eciuber.config.serializer.LocationDeserializer;
import eci.aygo.eciuber.model.enums.RideStatus;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ride {

	private String id;
	private Rider rider;
	private Driver driver;
	@JsonDeserialize(using = LocationDeserializer.class)
	private Location pickup;
	@JsonDeserialize(using = LocationDeserializer.class)
	private Location destination;
	private Double distance;
	private Integer duration;
	private RideStatus status;
	private BigDecimal estimatedFare;
	private BigDecimal actualFare;
	private LocalDateTime requestedTime;
	private LocalDateTime startedTime;
	private LocalDateTime completedTime;
	private Payment payment;
	private List<RideStatusChange> statusHistory;

	public Ride(String id, Rider rider, Driver driver, Location pickup, Location destination, Double distance,
			Integer duration, RideStatus status, BigDecimal estimatedFare, BigDecimal actualFare,
			LocalDateTime requestedTime, LocalDateTime startedTime, LocalDateTime completedTime, Payment payment,
			List<RideStatusChange> statusHistory) {

		this.id = id;
		this.rider = rider;
		this.driver = driver;
		this.pickup = pickup;
		this.destination = destination;
		this.distance = distance;
		this.duration = duration;
		this.status = status;
		this.estimatedFare = estimatedFare;
		this.actualFare = actualFare;
		this.requestedTime = requestedTime;
		this.startedTime = startedTime;
		this.completedTime = completedTime;
		this.payment = payment;
		this.statusHistory = statusHistory;
	}
	
	public Ride() {
		
	}

	// getters and setters

	@DynamoDbPartitionKey
	@DynamoDbAttribute("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@DynamoDbAttribute("rider")
	public Rider getRider() {
		return rider;
	}

	public void setRider(Rider rider) {
		this.rider = rider;
	}

	@DynamoDbAttribute("driver")
	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	@DynamoDbAttribute("pickup")
	public Location getPickup() {
		return pickup;
	}

	public void setPickup(Location pickup) {
		this.pickup = pickup;
	}

	@DynamoDbAttribute("destination")
	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location destination) {
		this.destination = destination;
	}

	@DynamoDbAttribute("distance")
	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	@DynamoDbAttribute("duration")
	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@DynamoDbAttribute("status")
	public RideStatus getStatus() {
		return status;
	}

	public void setStatus(RideStatus status) {
		this.status = status;
	}

	@DynamoDbAttribute("estimatedFare")
	@JsonSerialize(using = CurrencySerializer.class)
	public BigDecimal getEstimatedFare() {
		return estimatedFare;
	}

	public void setEstimatedFare(BigDecimal estimatedFare) {
		this.estimatedFare = estimatedFare;
	}

	@DynamoDbAttribute("actualFare")
	@JsonSerialize(using = CurrencySerializer.class)
	public BigDecimal getActualFare() {
		return actualFare;
	}

	public void setActualFare(BigDecimal actualFare) {
		this.actualFare = actualFare;
	}

	@DynamoDbAttribute("requestedTime")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime getRequestedTime() {
		return requestedTime;
	}

	public void setRequestedTime(LocalDateTime requestedTime) {
		this.requestedTime = requestedTime;
	}

	@DynamoDbAttribute("startedTime")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime getStartedTime() {
		return startedTime;
	}

	public void setStartedTime(LocalDateTime startedTime) {
		this.startedTime = startedTime;
	}

	@DynamoDbAttribute("completedTime")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(LocalDateTime completedTime) {
		this.completedTime = completedTime;
	}

	@DynamoDbAttribute("payment")
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@DynamoDbAttribute("statusHistory")
	public List<RideStatusChange> getStatusHistory() {
		return statusHistory;
	}

	public void setStatusHistory(List<RideStatusChange> statusHistory) {
		this.statusHistory = statusHistory;
	}

}
