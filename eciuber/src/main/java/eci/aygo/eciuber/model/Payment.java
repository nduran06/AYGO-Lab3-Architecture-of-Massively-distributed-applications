package eci.aygo.eciuber.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import eci.aygo.eciuber.config.serializer.CurrencySerializer;
import eci.aygo.eciuber.config.serializer.LocalDateTimeSerializer;
import eci.aygo.eciuber.model.enums.PaymentMethod;
import eci.aygo.eciuber.model.enums.PaymentStatus;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment {

	private String id;
	private String rideId;
	private String transactionId;
	private BigDecimal amount;
	private PaymentStatus status;
	private PaymentMethod method;
	private PaymentDetails details;
	private LocalDateTime createdTime;
	private LocalDateTime processedTime;

	public Payment(String id, String rideId, String transactionId, BigDecimal amount, PaymentStatus status,
			PaymentMethod method, PaymentDetails details, LocalDateTime createdTime, LocalDateTime processedTime) {

		this.id = id;
		this.rideId = rideId;
		this.transactionId = transactionId;
		this.amount = amount;
		this.status = status;
		this.method = method;
		this.details = details;
		this.createdTime = createdTime;
		this.processedTime = processedTime;
	}

	public Payment() {
	}

	// getters and setters

	@DynamoDbPartitionKey
	@DynamoDbAttribute("id")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@DynamoDbAttribute("rideId")
	public String getRideId() {
		return this.rideId;
	}

	public void setRideId(String rideId) {
		this.rideId = rideId;
	}

	@DynamoDbAttribute("transactionId")
	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@DynamoDbAttribute("amount")
	@JsonSerialize(using = CurrencySerializer.class)
	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@DynamoDbAttribute("status")
	public PaymentStatus getStatus() {
		return this.status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	@DynamoDbAttribute("method")
	public PaymentMethod getMethod() {
		return this.method;
	}

	public void setMethod(PaymentMethod method) {
		this.method = method;
	}

	@DynamoDbAttribute("details")
	public PaymentDetails getDetails() {
		return this.details;
	}

	public void setDetails(PaymentDetails details) {
		this.details = details;
	}

	@DynamoDbAttribute("createdTime")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	@DynamoDbAttribute("processedTime")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime getProcessedTime() {
		return this.processedTime;
	}

	public void setProcessedTime(LocalDateTime processedTime) {
		this.processedTime = processedTime;
	}
}