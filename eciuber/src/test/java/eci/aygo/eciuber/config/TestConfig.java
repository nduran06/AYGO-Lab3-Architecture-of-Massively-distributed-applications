package eci.aygo.eciuber.config;

import java.math.BigDecimal;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import eci.aygo.eciuber.config.serializer.CurrencySerializer;
import eci.aygo.eciuber.config.serializer.LocationDeserializer;
import eci.aygo.eciuber.model.Location;

@TestConfiguration
public class TestConfig {

	@Bean
	public ObjectMapper objectMapper() {

		ObjectMapper mapper = new ObjectMapper();

		// Register JavaTimeModule for DateTime handling
		mapper.registerModule(new JavaTimeModule());

		// Configure general settings
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		// Register custom serializers/deserializers
		SimpleModule module = new SimpleModule();
		module.addSerializer(BigDecimal.class, new CurrencySerializer());
		module.addDeserializer(Location.class, new LocationDeserializer());
		mapper.registerModule(module);

		return mapper;
	}
}