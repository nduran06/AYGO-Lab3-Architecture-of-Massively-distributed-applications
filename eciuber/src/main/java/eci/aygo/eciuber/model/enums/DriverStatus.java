package eci.aygo.eciuber.model.enums;

import eci.aygo.eciuber.model.intf.UserStatus;

public enum DriverStatus implements UserStatus {

	AVAILABLE("available"),
	BUSY("busy"),
	OFFLINE("offline"),
	SUSPENDED("suspended"),
	ON_BREAK("on_break"),
	PENDING_APPROVAL("pending_approval");

	private final String displayName;

	DriverStatus(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}
}
