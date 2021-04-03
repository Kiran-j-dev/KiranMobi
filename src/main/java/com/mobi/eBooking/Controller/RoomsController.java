package com.mobi.eBooking.Controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobi.eBooking.Model.RoomModel;
import com.mobi.eBooking.RequestClass.RoomRequest;
import com.mobi.eBooking.Service.RoomService;

@CrossOrigin(origins = "*")
@RestController
public class RoomsController {

	@Autowired
	RoomService roomService;

	@PostMapping("createRoom")
	public Map<String, Object> createRoom(@RequestBody RoomRequest request) {
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		RoomModel roomIdCheck = roomService.isRoomExist(request);
		if (roomIdCheck == null) {
			RoomModel room = roomService.createRoom(request);
			if (room != null) {
				response.put("Success", "Room Created Suucessfully");
			} else {
				response.put("Error", "Room Not Created");
			}
		} else {
			response.put("Error", "Can't create a Room, RoomId already exist");
		}
		return response;
	}
	
	@GetMapping("availableRooms")
	public Map<String,Object> availableRooms(){
		Map<String,Object> response = new LinkedHashMap<>();
		List<Map<String, Object>> resultList = new ArrayList<>();
		List<RoomModel> room = roomService.getAvailableRooms();
		if(!room.isEmpty()){
			for (RoomModel data : room) {
				Map<String, Object> responseData = new LinkedHashMap<>();
				responseData.put("roomId", data.getRoomId());
				resultList.add(responseData);
			}
		}
		response.put("Success", "Available Room Listed");
		response.put("AvailableRooms", resultList);
		return response;
	}

}
