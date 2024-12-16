package eci.aygo.eciuber.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import eci.aygo.eciuber.config.serializer.LocalDateTimeSerializer;
import eci.aygo.eciuber.model.enums.RideStatus;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@JsonIgnoreProperties(ignoreUnknown = true)
public class RideStatusChange {

	private String id;
	private RideStatus fromStatus;
	private RideStatus toStatus;
	private String reason;
	private String changedBy;
	private LocalDateTime timestamp;

	public RideStatusChange(String id, RideStatus fromStatus, RideStatus toStatus, String reason, String changedBy,
			LocalDateTime timestamp) {

		this.id = id;
		this.fromStatus = fromStatus;
		this.toStatus = toStatus;
		this.reason = reason;
		this.changedBy = changedBy;
		this.timestamp = timestamp;
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

	@DynamoDbAttribute("fromStatus")
	public RideStatus getFromStatus() {
		return fromStatus;
	}

	public void setFromStatus(RideStatus fromStatus) {
		this.fromStatus = fromStatus;
	}

	@DynamoDbAttribute("toStatus")
	public RideStatus getToStatus() {
		return toStatus;
	}

	public void setToStatus(RideStatus toStatus) {
		this.toStatus = toStatus;
	}

	@DynamoDbAttribute("reason")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@DynamoDbAttribute("changedBy")
	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	@DynamoDbAttribute("timestamp")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
