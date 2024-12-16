package eci.aygo.eciuber.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import eci.aygo.eciuber.config.db.UserStatusConverter;
import eci.aygo.eciuber.config.serializer.PhoneSerializer;
import eci.aygo.eciuber.model.enums.UserType;
import eci.aygo.eciuber.model.intf.UserStatus;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbConvertedBy;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

@DynamoDbBean
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = Driver.class, name = "DRIVER"),
		@JsonSubTypes.Type(value = Rider.class, name = "RIDER") })
public abstract class User {

	//@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected String id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private Double rating;
	private UserType type;
	private UserStatus status;
	private LocalDateTime createdTime;
	private LocalDateTime lastUpdateTime;

	public User(String id, String name, String email, String password, String phone, Double rating, UserType type,
			LocalDateTime createdTime, LocalDateTime lastUpdateTime) {

		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.rating = rating;
		this.type = type;
		this.createdTime = createdTime;
		this.lastUpdateTime = lastUpdateTime;
	}

	public User(String id, String name, String email, String password, String phone, Double rating, UserType type,
			UserStatus status, LocalDateTime createdTime, LocalDateTime lastUpdateTime) {

		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.rating = rating;
		this.type = type;
		this.status = status;
		this.createdTime = createdTime;
		this.lastUpdateTime = lastUpdateTime;
	}

	@JsonCreator
	public User() {

	}

	public abstract void defaultStatus();

	// getters and setters

	@DynamoDbPartitionKey
	@DynamoDbAttribute("orderId")
	@JsonProperty("id")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@DynamoDbAttribute("name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@DynamoDbSecondaryPartitionKey(indexNames = { "email-index" })
	@DynamoDbAttribute("email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@DynamoDbAttribute("password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@DynamoDbAttribute("phone")
	@JsonSerialize(using = PhoneSerializer.class)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@DynamoDbAttribute("rating")
	public Double getRating() {
		return this.rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	@DynamoDbAttribute("type")
	public UserType getType() {
		return this.type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	@DynamoDbAttribute("status")
	@DynamoDbConvertedBy(value = UserStatusConverter.class)
	public UserStatus getStatus() {
		return this.status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	@DynamoDbAttribute("createdTime")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	@DynamoDbAttribute("lastUpdateTime")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public abstract void beforeWrite();

}
