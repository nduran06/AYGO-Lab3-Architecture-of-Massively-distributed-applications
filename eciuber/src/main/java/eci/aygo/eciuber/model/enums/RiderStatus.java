package eci.aygo.eciuber.model.enums;

import eci.aygo.eciuber.model.intf.UserStatus;

public enum RiderStatus implements UserStatus {

	ACTIVE("active"),
	INACTIVE("inactive"),
	SUSPENDED("suspended"),
	BLOCKED("blocked"),
	PENDING_VERIFICATION("pending_verification");

	private final String displayName;

	RiderStatus(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}
}