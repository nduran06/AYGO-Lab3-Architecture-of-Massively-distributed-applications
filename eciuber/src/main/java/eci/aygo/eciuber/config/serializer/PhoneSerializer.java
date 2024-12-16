package eci.aygo.eciuber.config.serializer;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class PhoneSerializer extends JsonSerializer<String> {
	
	@Override
	public void serialize(String phone, JsonGenerator generator, SerializerProvider provider) throws IOException {
		
		if (phone != null) {
			
			// Format: +X-XXX-XXX-XXXX
			String formatted = phone.replaceAll("(\\d{3})(\\d{3})(\\d{4})", "+1-$1-$2-$3");
			generator.writeString(formatted);
		}
	}
}
