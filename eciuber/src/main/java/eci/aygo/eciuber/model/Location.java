package eci.aygo.eciuber.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

	private String id;
	private String country;
	private String city;
	private String state;
	private String address;
	private String zipCode;

	@NotNull(message = "Latitude is required")
	@DecimalMin(value = "-90.0")
	@DecimalMax(value = "90.0")
	private Double latitude;

	@NotNull(message = "Longitude is required")
	@DecimalMin(value = "-180.0")
	@DecimalMax(value = "180.0")
	private Double longitude;

	public Location(Double latitude, Double longitude) {

		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Location(String id, String country, String city, String state, String address, String zipCode,
			Double latitude, Double longitude) {

		this.id = id;
		this.country = country;
		this.city = city;
		this.state = state;
		this.address = address;
		this.zipCode = zipCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@JsonCreator
	public Location() {
	}

	@JsonIgnore
	public boolean isValid() {
		return latitude != null && longitude != null;
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

	@DynamoDbAttribute("country")
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@DynamoDbAttribute("city")
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@DynamoDbAttribute("state")
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@DynamoDbAttribute("address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@DynamoDbAttribute("zipCode")
	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@DynamoDbAttribute("latitude")
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@DynamoDbAttribute("longitude")
	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}
