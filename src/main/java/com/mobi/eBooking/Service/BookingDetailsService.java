package com.mobi.eBooking.Service;

import java.util.List;
import java.util.Map;

import com.mobi.eBooking.Model.BookingDetailsModel;
import com.mobi.eBooking.RequestClass.BookingDetailsRequest;

public interface BookingDetailsService {

	BookingDetailsModel saveBookingDetails(BookingDetailsRequest request, String userId);

	List<Map<String, Object>> countForTheDay();

	void checkIn(BookingDetailsRequest request);

	void checkOut(BookingDetailsRequest request);

	List<Map<String, Object>> countForThePreviousDay();

}
