package eci.aygo.eciuber.config.serializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CurrencySerializer extends JsonSerializer<BigDecimal> {
	
	private static final int DECIMAL_PLACES = 2;

	@Override
	public void serialize(BigDecimal value, JsonGenerator generator, SerializerProvider serializers) throws IOException {
		
		if (value != null) {

			BigDecimal formatted = value.setScale(DECIMAL_PLACES, RoundingMode.HALF_UP);
			generator.writeString(formatted.toString());
		}
	}
}