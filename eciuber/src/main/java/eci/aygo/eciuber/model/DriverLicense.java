package eci.aygo.eciuber.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import eci.aygo.eciuber.config.serializer.LocalDateTimeSerializer;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@JsonIgnoreProperties(ignoreUnknown = true)
public class DriverLicense {

	private String id;
	private String licenseNumber;
	private String stateIssued;
	private Boolean verified;
	private LocalDate expiryDate;
	private LocalDateTime verificationDate;
	private String docUrl;

	public DriverLicense(String id, String licenseNumber, String stateIssued, Boolean verified, LocalDate expiryDate,
			LocalDateTime verificationDate, String docUrl) {

		this.id = id;
		this.licenseNumber = licenseNumber;
		this.stateIssued = stateIssued;
		this.verified = verified;
		this.expiryDate = expiryDate;
		this.verificationDate = verificationDate;
		this.docUrl = docUrl;
	}

	public DriverLicense(String licenseNumber, LocalDate expiryDate) {
		
		this.licenseNumber = licenseNumber;
		this.expiryDate = expiryDate;
	}
	
	public DriverLicense() {
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

	@DynamoDbAttribute("licenseNumber")
	public String getLicenseNumber() {
		return this.licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	@DynamoDbAttribute("stateIssued")
	public String getIssuingState() {
		return this.stateIssued;
	}

	public void setStateIssued(String stateIssued) {
		this.stateIssued = stateIssued;
	}

	@DynamoDbAttribute("verified")
	public Boolean getVerified() {
		return this.verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	@DynamoDbAttribute("expiryDate")
	public LocalDate getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	@DynamoDbAttribute("verificationDate")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime getVerificationDate() {
		return this.verificationDate;
	}

	public void setVerificationDate(LocalDateTime verificationDate) {
		this.verificationDate = verificationDate;
	}

	@DynamoDbAttribute("documentUrl")
	public String getDocUrl() {
		return this.docUrl;
	}

	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}

}