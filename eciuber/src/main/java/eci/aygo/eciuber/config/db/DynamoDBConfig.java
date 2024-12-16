package eci.aygo.eciuber.config.db;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import eci.aygo.eciuber.model.Ride;
import eci.aygo.eciuber.model.User;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.core.retry.RetryPolicy;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDBConfig {
	@Value("${aws.region}")
	private String awsRegion;

	@Value("${aws.dynamodb.endpoint}")
	private String dynamoDbEndpoint;

	// Table names injected from configuration

	@Value("${aws.dynamodb.tables.user}")
	private String userTableName;
	
	@Value("${aws.dynamodb.tables.ride}")
	private String rideTableName;

	@Bean
	public DynamoDbClient dynamoDbClient() {
		// Create a single DynamoDB client that will be shared across all tables
		return DynamoDbClient.builder().region(Region.of(awsRegion)).endpointOverride(URI.create(dynamoDbEndpoint))
				.overrideConfiguration(ClientOverrideConfiguration.builder()
						.retryPolicy(RetryPolicy.builder().numRetries(3).build()).build())
				.build();
	}

	@Bean
	public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
		// Create a single enhanced client that will be shared across all tables
		return DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient).build();
	}

	// Create individual table beans for each entity type

	@Bean
	public DynamoDbTable<User> userTable(DynamoDbEnhancedClient enhancedClient) {
		return enhancedClient.table(userTableName, TableSchema.fromBean(User.class));
	}

	@Bean
	public DynamoDbTable<Ride> RideTable(DynamoDbEnhancedClient enhancedClient) {
		return enhancedClient.table(rideTableName, TableSchema.fromBean(Ride.class));
	}
}