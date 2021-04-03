package com.mobi.eBooking.RequestClass;

public class RoomRequest {

	private String roomId;
	
	private boolean availability;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
}
