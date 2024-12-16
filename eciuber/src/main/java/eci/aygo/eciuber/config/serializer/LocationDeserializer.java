package eci.aygo.eciuber.config.serializer;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import eci.aygo.eciuber.exception.LocationException;
import eci.aygo.eciuber.model.Location;

@Component
public class LocationDeserializer extends JsonDeserializer<Location> {

	@Override
	public Location deserialize(JsonParser jsonParser, DeserializationContext desContext) throws IOException {
		
		JsonNode node = jsonParser.getCodec().readTree(jsonParser);

		Location location = new Location();

		// latitude and longitude are required fields
		
		if (node.has("latitude")) {
			
			double lat = node.get("latitude").asDouble();
			
			if (lat < -90 || lat > 90) {
				throw new LocationException("Invalid latitude value");
			}

			location.setLatitude(lat);
		}

		if (node.has("longitude")) {
			double logt = node.get("longitude").asDouble();
			
			if (logt < -180 || logt > 180) {
				
				throw new LocationException("Invalid longitude value");
			}
			location.setLongitude(logt);
		}

		// Other fields (optional)

		if (node.has("country")) {
			location.setCity(node.get("country").asText());
		}

		if (node.has("city")) {
			location.setCity(node.get("city").asText());
		}

		if (node.has("state")) {
			location.setState(node.get("state").asText());
		}

		if (node.has("address")) {
			location.setAddress(node.get("address").asText());
		}

		if (node.has("zipCode")) {
			location.setZipCode(node.get("zipCode").asText());
		}

		return location;
	}
}
