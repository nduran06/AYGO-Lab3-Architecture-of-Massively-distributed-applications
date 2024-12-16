package eci.aygo.eciuber.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import eci.aygo.eciuber.model.enums.VehicleType;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleInfo {

	private String id;
	private String make;
	private String model;
	private VehicleType type;
	private Integer year;
	private String color;
	private String plateNumber;
	private String registrationNumber;
	private String insuranceNumber;
	private LocalDate insuranceExpiry;
	private Boolean verified;

	public VehicleInfo(String id, String make, String model, VehicleType type, Integer year, String color, String plateNumber,
			String registrationNumber, String insuranceNumber, LocalDate insuranceExpiry, Boolean verified) {
		
		this.id = id;        
		this.make = make;
		this.model = model;
		this.type = type;
		this.year = year;
		this.color = color;
		this.plateNumber = plateNumber;
		this.registrationNumber = registrationNumber;
		this.insuranceNumber = insuranceNumber;
		this.insuranceExpiry = insuranceExpiry;
		this.verified = verified;
	}

	public VehicleInfo(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	public VehicleInfo(String model, String plateNumber) {
		this.model = model;
		this.plateNumber  = plateNumber;
	}
	
	@JsonCreator
	public VehicleInfo() {

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

	@DynamoDbAttribute("make")
    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @DynamoDbAttribute("model")
    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @DynamoDbAttribute("type")
    public VehicleType getType() {
        return this.type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }
    
    @DynamoDbAttribute("year")
    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @DynamoDbAttribute("color")
    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @DynamoDbAttribute("plateNumber")
    public String getPlateNumber() {
        return this.plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @DynamoDbAttribute("registrationNumber")
    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @DynamoDbAttribute("insuranceNumber")
    public String getInsuranceNumber() {
        return this.insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    @DynamoDbAttribute("insuranceExpiry")
    public LocalDate getInsuranceExpiry() {
        return this.insuranceExpiry;
    }

    public void setInsuranceExpiry(LocalDate insuranceExpiry) {
        this.insuranceExpiry = insuranceExpiry;
    }

    @DynamoDbAttribute("verified")
    public Boolean getVerified() {
        return this.verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
}
