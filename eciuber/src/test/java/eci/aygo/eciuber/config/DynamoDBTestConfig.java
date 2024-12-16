package eci.aygo.eciuber.config;

import java.net.URI;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded;
import com.amazonaws.services.dynamodbv2.local.shared.access.AmazonDynamoDBLocal;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@TestConfiguration
public class DynamoDBTestConfig {

	private AmazonDynamoDBLocal amazonDynamoDBLocal;

	@PostConstruct
	void initialize() {
		System.setProperty("sqlite4java.library.path", "native-libs");
		amazonDynamoDBLocal = DynamoDBEmbedded.create();
	}

	@PreDestroy
	void shutdown() {
		if (amazonDynamoDBLocal != null) {
			amazonDynamoDBLocal.shutdown();
		}
	}

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