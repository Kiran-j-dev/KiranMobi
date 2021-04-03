package com.mobi.eBooking.Service;

import java.util.List;

import com.mobi.eBooking.Model.RoomModel;
import com.mobi.eBooking.RequestClass.RoomRequest;

public interface RoomService {

	RoomModel createRoom(RoomRequest request);

	RoomModel isRoomExist(RoomRequest request);

	List<RoomModel> getAvailableRooms();

}
