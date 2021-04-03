package com.mobi.eBooking.Dao;

import java.util.List;

import com.mobi.eBooking.Model.RoomModel;
import com.mobi.eBooking.RequestClass.RoomRequest;

public interface RoomDAO {

	RoomModel createRoom(RoomModel room);

	RoomModel isRoomIdExist(RoomRequest request);

	List<RoomModel> getAvailableRooms();

	void updateRoomAvailability(String roomId);

	RoomModel checkRoomAvailability(String roomId, int num);

	void updateRoomAvailabilityAfterCheckOut(String roomId);

	List<RoomModel> getTotalRooms();

}
