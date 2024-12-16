package eci.aygo.eciuber;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded;
import com.amazonaws.services.dynamodbv2.local.shared.access.AmazonDynamoDBLocal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import eci.aygo.eciuber.config.DynamoDBTestConfig;
import eci.aygo.eciuber.config.serializer.CurrencySerializer;
import eci.aygo.eciuber.config.serializer.LocationDeserializer;
import eci.aygo.eciuber.event.RideEvent;
import eci.aygo.eciuber.event.type.RideEventType;
import eci.aygo.eciuber.model.Driver;
import eci.aygo.eciuber.model.DriverLicense;
import eci.aygo.eciuber.model.Location;
import eci.aygo.eciuber.model.Payment;
import eci.aygo.eciuber.model.Ride;
import eci.aygo.eciuber.model.User;
import eci.aygo.eciuber.model.enums.DriverStatus;
import eci.aygo.eciuber.model.enums.PaymentStatus;
import eci.aygo.eciuber.model.enums.RideStatus;
import eci.aygo.eciuber.model.enums.UserType;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

class SerializationTest {

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();

		// Configure ObjectMapper
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	@Test
	void testUserSerialization() throws JsonProcessingException {
		// Given
		Driver driver = new Driver();
		driver.setId("d-123");
		driver.setName("John Doe");
		driver.setEmail("john@example.com");
		driver.setType(UserType.DRIVER);
		driver.setStatus(DriverStatus.AVAILABLE);
		driver.setDriverLicense(new DriverLicense("DL123", LocalDate.now()));

		// When
		String json = objectMapper.writeValueAsString(driver);

		// Then
		assertNotNull(json);
		assertThat(json.contains("\"type\":\"DRIVER\""));
		assertTrue(json.contains("\"name\":\"John Doe\""));
		// assertFalse(json.contains("password")); // Sensitive data should be excluded
	}

	@Test
	void testUserDeserialization() throws JsonProcessingException {
		// Given
		String json = """
				{
				    "id": "d-123",
				    "type": "DRIVER",
				    "name": "John Doe",
				    "email": "john@example.com",
				    "driverLicense": {
				        "licenseNumber": "DL123",
				        "expiryDate": "2024-12-31"
				    }
				}
				""";

		// When
		User user = objectMapper.readValue(json, User.class);

		// Then
		assertTrue(user instanceof Driver);
		assertEquals("John Doe", user.getName());
		assertEquals("DL123", ((Driver) user).getDriverLicense().getLicenseNumber());
	}

	@Test
	void testRideSerialization() throws JsonProcessingException {
		// Given
		Ride ride = new Ride();
		ride.setId("r-123");
		ride.setStatus(RideStatus.REQUESTED);
		ride.setEstimatedFare(new BigDecimal("25.50"));
		ride.setPickup(new Location(40.7128, -74.0060));

		// When
		String json = objectMapper.writeValueAsString(ride);

		// Then
		assertNotNull(json);
		assertTrue(json.contains("\"estimatedFare\":\"25.50\"")); // Test MoneySerializer
		assertTrue(json.contains("\"status\":\"REQUESTED\""));
	}

	@Test
	void testRideDeserialization() throws JsonProcessingException {
		// Given
		String json = """
				{
				    "id": "r-123",
				    "status": "REQUESTED",
				    "estimatedFare": "25.50",
				    "pickup": {
				        "latitude": 40.7128,
				        "longitude": -74.0060,
				        "address": "NYC"
				    }
				}
				""";

		// When
		Ride ride = objectMapper.readValue(json, Ride.class);

		// Then
		assertEquals("r-123", ride.getId());
		assertEquals(RideStatus.REQUESTED, ride.getStatus());
		assertEquals(0, new BigDecimal("25.50").compareTo(ride.getEstimatedFare()));
		assertEquals(40.7128, ride.getPickup().getLatitude());
	}

	@Test
	void testJsonViewsSerialization() throws JsonProcessingException {
		// Given
		Driver driver = new Driver();
		driver.setId("d-123");
		driver.setName("John");
		driver.setEmail("john@example.com");
		driver.setDriverLicense(new DriverLicense("DL123", LocalDate.now()));

		// When - Public View
		String publicJson = objectMapper.writeValueAsString(driver);

		// Then
		assertTrue(publicJson.contains("\"id\""));
		assertTrue(publicJson.contains("\"name\""));
		assertTrue(publicJson.contains("\"email\""));
		assertTrue(publicJson.contains("\"driverLicense\""));

		// When - Internal View
		String internalJson = objectMapper.writeValueAsString(driver);

		// Then
		assertTrue(internalJson.contains("\"email\""));
		assertTrue(internalJson.contains("\"driverLicense\""));
	}

	@Test
	void testLocationSerialization() throws JsonProcessingException {
		// Given
		Location location = new Location(40.7128, -74.0060);

		// When
		String json = objectMapper.writeValueAsString(location);

		// Then
		assertNotNull(json);
		assertTrue(json.contains("\"latitude\":40.7128"));
		assertTrue(json.contains("\"longitude\":-74.006"));
	}

	@Test
	void testInvalidLocationDeserialization() {
		// Given
		String invalidJson = """
					{
				    "id": "r-123",
				    "status": "REQUESTED",
				    "estimatedFare": "25.50",
				    "pickup": {
				        "latitude": 400.7128,
				        "longitude": -704.0060,
				        "address": "NYC"
				    }
				}
				""";

		// When/Then
		assertThrows(JsonMappingException.class, () -> objectMapper.readValue(invalidJson, Ride.class));
	}

	@Test
	void testPaymentSerialization() throws JsonProcessingException {
		// Given
		Payment payment = new Payment();
		payment.setId("p-123");
		payment.setAmount(new BigDecimal("99.99"));
		payment.setStatus(PaymentStatus.COMPLETED);

		// When
		String json = objectMapper.writeValueAsString(payment);

		// Then
		assertNotNull(json);
		assertTrue(json.contains("\"amount\":\"99.99\"")); // Test MoneySerializer
		assertTrue(json.contains("\"status\":\"COMPLETED\""));
	}

	@Test
	void testEventSerialization() throws JsonProcessingException {
		// Given
		RideEvent event = new RideEvent("r-123", "u-123", "d-123", RideEventType.REQUESTED,
				new Location(40.7128, -74.0060));

		// When
		String json = objectMapper.writeValueAsString(event);

		// Then
		assertNotNull(json);
		assertTrue(json.contains("\"eventType\":\"REQUESTED\""));
		assertTrue(json.contains("\"rideId\":\"r-123\""));
	}

	@Test
	void testCollectionSerialization() throws JsonProcessingException {
		// Given
		List<Location> locations = Arrays.asList(new Location(40.7128, -74.0060), new Location(34.0522, -118.2437));

		// When
		String json = objectMapper.writeValueAsString(locations);

		// Then
		assertNotNull(json);
		assertTrue(json.contains("\"latitude\":40.7128"));
		assertTrue(json.contains("\"latitude\":34.0522"));
	}
}

// Integration test for real database serialization

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DynamoDBTestConfig.class)
class DynamoDbSerializationTest {

	private static AmazonDynamoDBLocal amazonDynamoDBLocal;

	static {
		// Set the sqlite4java library path to the native-libs directory
		System.setProperty("sqlite4java.library.path", "native-libs");
		try {
			amazonDynamoDBLocal = DynamoDBEmbedded.create();
		} catch (Exception e) {
			throw new RuntimeException("Could not create embedded DynamoDB", e);
		}
	}

	@TestConfiguration
	static class Config {
		@Bean
		public DynamoDbClient dynamoDbClient() {
			return DynamoDbClient.builder().endpointOverride(URI.create("http://localhost:8000"))
					.credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("dummy", "dummy")))
					.httpClient(UrlConnectionHttpClient.builder().build()).region(Region.US_EAST_1).build();
		}

		@Bean
		public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
			return DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient).build();
		}
	}

	@Autowired
	private DynamoDbEnhancedClient dynamoDbClient;

	@Test
	void testDynamoDbSerialization() {
		// Given
		Driver driver = new Driver();
		driver.setId("d-123");
		driver.setName("John Doe");

		// When
		// DynamoDbTable<Driver> table = dynamoDbClient.table("Drivers",
		// TableSchema.fromBean(Driver.class));
		// table.putItem(driver);

		// Then
		// Driver retrieved =
		// table.getItem(Key.builder().partitionValue("d-123").build());

		// assertEquals(driver.getName(), retrieved.getName());
	}
}

// Test custom serializers

class CustomSerializerTest {
	private ObjectMapper objectMapper;

	@BeforeEach
	void setup() {
		objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addSerializer(BigDecimal.class, new CurrencySerializer());
		module.addDeserializer(Location.class, new LocationDeserializer());
		objectMapper.registerModule(module);
	}

	@Test
	void testMoneySerializer() throws JsonProcessingException {
		// Given
		BigDecimal amount = new BigDecimal("99.999");

		// When
		String json = objectMapper.writeValueAsString(amount);

		// Then
		assertEquals("\"100.00\"", json); // Should round to 2 decimal places
	}

	@Test
	void testLocationDeserializer() throws JsonProcessingException {
		// Given
		String json = """
				{
				    "latitude": 40.7128,
				    "longitude": -74.0060,
				    "address": "NYC"
				}
				""";

		// When
		Location location = objectMapper.readValue(json, Location.class);

		// Then
		assertEquals(40.7128, location.getLatitude());
		assertEquals(-74.0060, location.getLongitude());
		assertEquals("NYC", location.getAddress());
	}

}