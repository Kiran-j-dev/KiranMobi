package com.mobi.eBooking.Controller;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobi.eBooking.Dao.RoomDAO;
import com.mobi.eBooking.Model.BookingDetailsModel;
import com.mobi.eBooking.Model.RoomModel;
import com.mobi.eBooking.RequestClass.BookingDetailsRequest;
import com.mobi.eBooking.Service.BookingDetailsService;

@CrossOrigin(origins = "*")
@RestController
public class BookingController {

	@Autowired
	BookingDetailsService bookingDetailsService;

	@Autowired
	RoomDAO roomDAO;

	@PostMapping("book")
	public Map<String, Object> bookRoom(@RequestBody BookingDetailsRequest request, HttpServletRequest httpRequest) {
		Map<String, Object> response = new LinkedHashMap<>();
		Enumeration<String> headerValues = httpRequest.getHeaderNames();
		String userId = null;
		if (headerValues.hasMoreElements()) {
			userId = httpRequest.getHeader("userId").toString();
		}
		BookingDetailsModel book = bookingDetailsService.saveBookingDetails(request, userId);
		if (book != null) {
			response.put("Success", "Room Successfully Booked");
		} else {
			response.put("Error", "Room Already Booked");
		}
		return response;
	}

	@GetMapping("countForTheDay")
	public Map<String, Object> countForTheDay() {
		Map<String, Object> response = new LinkedHashMap<>();
		List<RoomModel> rooms = roomDAO.getTotalRooms();
		int totalRooms = 0;
		for (int z = 0; z < rooms.size(); z++) {
			totalRooms++;
		}
		int dayCount = 0;
		int available = 0;
		List<Map<String, Object>> count = bookingDetailsService.countForTheDay();
		List<RoomModel> list = roomDAO.getAvailableRooms();
		for (int i = 0; i < count.size(); i++) {
			dayCount++;
		}
		for (int j = 0; j < list.size(); j++) {
			available++;
		}
		response.put("CountOfTheDay", dayCount);
		response.put("Total Rooms Booked", totalRooms - available);
		response.put("Total Available Rooms", available);
		return response;
	}

	@PostMapping("checkIn")
	public Map<String, Object> checkIn(@RequestBody BookingDetailsRequest request) {
		Map<String, Object> response = new LinkedHashMap<>();
		bookingDetailsService.checkIn(request);
		response.put("Success", "You Checked In");
		return response;
	}

	@PostMapping("checkOut")
	public Map<String, Object> checkOut(@RequestBody BookingDetailsRequest request) {
		Map<String, Object> response = new LinkedHashMap<>();
		bookingDetailsService.checkOut(request);
		response.put("Success", "Checked Out.Thansks Visit Again");
		return response;
	}
}
