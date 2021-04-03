package com.mobi.eBooking.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobi.eBooking.Dao.RoomDAO;
import com.mobi.eBooking.Model.RoomModel;
import com.mobi.eBooking.RequestClass.RoomRequest;
import com.mobi.eBooking.Service.RoomService;

@Service
public class RoomServImpl implements RoomService {
	
	@Autowired
	RoomDAO roomDAO;

	@Override
	public RoomModel createRoom(RoomRequest request) {
		RoomModel room = new RoomModel();
		room.setRoomId(request.getRoomId());
		room = roomDAO.createRoom(room);
		return room;
	}

	@Override
	public RoomModel isRoomExist(RoomRequest request) {
		RoomModel room = roomDAO.isRoomIdExist(request);
		return room;
	}

	@Override
	public List<RoomModel> getAvailableRooms() {
		List<RoomModel> room = roomDAO.getAvailableRooms();
		return room;
	}

}
