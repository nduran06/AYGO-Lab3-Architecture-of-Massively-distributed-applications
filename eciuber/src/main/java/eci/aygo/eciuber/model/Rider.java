package eci.aygo.eciuber.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import eci.aygo.eciuber.config.serializer.CurrencySerializer;
import eci.aygo.eciuber.model.enums.PaymentMethod;
import eci.aygo.eciuber.model.enums.RiderStatus;
import eci.aygo.eciuber.model.enums.UserType;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rider extends User {

	private Integer totalRides;
	private BigDecimal totalSpent;
	private String preferredLanguage;
	private PaymentMethod preferredPaymentMethod;
	private List<PaymentMethod> paymentMethods;
	private List<Location> savedLocations;

	public Rider(String id, String name, String email, String password, String phone, Double rating,
			LocalDateTime createdTime, LocalDateTime lastUpdateTime, Integer totalRides, BigDecimal totalSpent,
			String preferredLanguage, PaymentMethod preferredPaymentMethod, List<PaymentMethod> paymentMethods,
			List<Location> savedLocations) {

		super(id, name, email, password, phone, rating, UserType.RIDER, createdTime, lastUpdateTime);

		this.totalRides = totalRides;
		this.totalSpent = totalSpent;
		this.preferredLanguage = preferredLanguage;
		this.preferredPaymentMethod = preferredPaymentMethod;
		this.paymentMethods = paymentMethods;
		this.savedLocations = savedLocations;
		this.defaultStatus();
	}

	public Rider(String id, String name, String email, String password, String phone, Double rating, UserType type,
			LocalDateTime createdTime, LocalDateTime lastUpdateTime, Integer totalRides, BigDecimal totalSpent,
			String preferredLanguage, PaymentMethod preferredPaymentMethod, List<PaymentMethod> paymentMethods,
			List<Location> savedLocations, RiderStatus status) {

		super(id, name, email, password, phone, rating, type, createdTime, lastUpdateTime);

		this.totalRides = totalRides;
		this.totalSpent = totalSpent;
		this.preferredLanguage = preferredLanguage;
		this.preferredPaymentMethod = preferredPaymentMethod;
		this.paymentMethods = paymentMethods;
		this.savedLocations = savedLocations;
		this.setStatus(status);
	}

	public Rider() {
		super();
		this.setType(UserType.RIDER);
	}

	@Override

    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }
	// getters and setters

	@Override
	public void defaultStatus() {
		this.setStatus(RiderStatus.ACTIVE);
	}
	
	@DynamoDbAttribute("totalRides")
	public Integer getTotalRides() {
		return totalRides;
	}

	public void setTotalRides(Integer totalRides) {
		this.totalRides = totalRides;
	}

	@DynamoDbAttribute("totalSpent")
	@JsonSerialize(using = CurrencySerializer.class)
	public BigDecimal getTotalSpent() {
		return totalSpent;
	}

	public void setTotalSpent(BigDecimal totalSpent) {
		this.totalSpent = totalSpent;
	}

	@DynamoDbAttribute("preferredLanguage")
	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	@DynamoDbAttribute("preferredPaymentMethod")
	public PaymentMethod getPreferredPaymentMethod() {
		return preferredPaymentMethod;
	}

	public void setPreferredPaymentMethod(PaymentMethod preferredPaymentMethod) {
		this.preferredPaymentMethod = preferredPaymentMethod;
	}

	@DynamoDbAttribute("paymentMethods")
	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	@DynamoDbAttribute("savedLocations")
	public List<Location> getSavedLocations() {
		return savedLocations;
	}

	public void setSavedLocations(List<Location> savedLocations) {
		this.savedLocations = savedLocations;
	}

	@Override
	public void beforeWrite() {
		// TODO Auto-generated method stub
		
	}
}
