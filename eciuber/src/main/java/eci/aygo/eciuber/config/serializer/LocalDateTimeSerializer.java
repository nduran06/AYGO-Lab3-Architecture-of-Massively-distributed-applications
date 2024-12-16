package eci.aygo.eciuber.config.serializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
	 
	private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

	@Override
	public void serialize(LocalDateTime value, JsonGenerator generator, SerializerProvider provider) throws IOException { 
		
		if (value != null) {
			generator.writeString(formatter.format(value));
		}
	}
}