package eci.aygo.eciuber.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import eci.aygo.eciuber.config.serializer.CurrencySerializer;
import eci.aygo.eciuber.config.serializer.LocalDateTimeSerializer;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDetails {

	private String id;
	private Ride ride;
	private BigDecimal baseAmount;
	private BigDecimal distance;
	private BigDecimal time;
	private BigDecimal tax;
	private BigDecimal surgePricing;
	private BigDecimal driverPayout;
	private BigDecimal platformFee;
	private BigDecimal totalAmount;
	private LocalDateTime createdTime;
	private LocalDateTime processedTime;

	public PaymentDetails(String id, Ride ride, BigDecimal baseAmount, BigDecimal distance, BigDecimal time,
			BigDecimal tax, BigDecimal surgePricing, BigDecimal driverPayout, BigDecimal platformFee,
			BigDecimal totalAmount, LocalDateTime createdTime, LocalDateTime processedTime) {

		this.id = id;
		this.ride = ride;
		this.baseAmount = baseAmount;
		this.distance = distance;
		this.time = time;
		this.tax = tax;
		this.surgePricing = surgePricing;
		this.driverPayout = driverPayout;
		this.platformFee = platformFee;
		this.totalAmount = totalAmount;
		this.createdTime = createdTime;
		this.processedTime = processedTime;
	}
	
	public PaymentDetails(){}

	// getters and setters

	@DynamoDbPartitionKey
	@DynamoDbAttribute("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@DynamoDbAttribute("ride")
	public Ride getRide() {
		return ride;
	}

	public void setRide(Ride ride) {
		this.ride = ride;
	}

	@DynamoDbAttribute("baseAmount")
	@JsonSerialize(using = CurrencySerializer.class)
	public BigDecimal getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = baseAmount;
	}

	@DynamoDbAttribute("distance")
	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	@DynamoDbAttribute("time")
	public BigDecimal getTime() {
		return time;
	}

	public void setTime(BigDecimal time) {
		this.time = time;
	}

	@DynamoDbAttribute("tax")
	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	@DynamoDbAttribute("surgePricing")
	@JsonSerialize(using = CurrencySerializer.class)
	public BigDecimal getSurgePricing() {
		return surgePricing;
	}

	public void setSurgePricing(BigDecimal surgePricing) {
		this.surgePricing = surgePricing;
	}

	@DynamoDbAttribute("driverPayout")
	@JsonSerialize(using = CurrencySerializer.class)
	public BigDecimal getDriverPayout() {
		return driverPayout;
	}

	public void setDriverPayout(BigDecimal driverPayout) {
		this.driverPayout = driverPayout;
	}

	@DynamoDbAttribute("platformFee")
	@JsonSerialize(using = CurrencySerializer.class)
	public BigDecimal getPlatformFee() {
		return platformFee;
	}

	public void setPlatformFee(BigDecimal platformFee) {
		this.platformFee = platformFee;
	}

	@DynamoDbAttribute("totalAmount")
	@JsonSerialize(using = CurrencySerializer.class)
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	@DynamoDbAttribute("createdTime")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	@DynamoDbAttribute("processedTime")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime getProcessedTime() {
		return processedTime;
	}

	public void setProcessedTime(LocalDateTime processedTime) {
		this.processedTime = processedTime;
	}

}
