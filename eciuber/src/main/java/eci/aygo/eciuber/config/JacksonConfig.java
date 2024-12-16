package eci.aygo.eciuber.config;

import java.math.BigDecimal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import eci.aygo.eciuber.config.serializer.CurrencySerializer;
import eci.aygo.eciuber.config.serializer.LocationDeserializer;
import eci.aygo.eciuber.model.Location;
import eci.aygo.eciuber.model.enums.DriverStatus;
import eci.aygo.eciuber.model.enums.RiderStatus;
import eci.aygo.eciuber.model.intf.UserStatus;

@Configuration
public class JacksonConfig {

	@Bean
	public ObjectMapper objectMapper() {

		ObjectMapper mapper = new ObjectMapper();

		// Basic config
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		// Date/Time handling
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		// Constructor visibility
		mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		mapper.setVisibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.ANY);

		// Custom modules
		mapper.registerModule(new SimpleModule().addSerializer(BigDecimal.class, new CurrencySerializer())
				.addDeserializer(Location.class, new LocationDeserializer()));
		
		// Register the subtypes of the UserStatus
        JavaType userTypeJavaType = mapper.getTypeFactory().constructType(UserStatus.class);
        mapper.registerSubtypes(
            new NamedType(RiderStatus.class, "riderStatus"),
            new NamedType(DriverStatus.class, "driverStatus")
        );

		return mapper;
	}
}