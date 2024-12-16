package eci.aygo.eciuber.event;

import java.time.LocalDateTime;
import java.util.UUID;

public class BaseEvent {

	private final String eventId;
	private final String eventType;
	private final LocalDateTime timestamp;

	protected BaseEvent(String eventType) {

		this.eventId = UUID.randomUUID().toString();
		this.eventType = eventType;
		this.timestamp = LocalDateTime.now();
	}

	// getters
	public String getEventId() {
		return eventId;
	}

	public String getEventType() {
		return eventType;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
}
