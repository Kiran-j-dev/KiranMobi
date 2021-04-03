package com.mobi.eBooking.Dao;

import java.util.List;
import java.util.Map;

import com.mobi.eBooking.Model.BookingDetailsModel;
import com.mobi.eBooking.RequestClass.BookingDetailsRequest;

public interface BookingDetailsDAO {

	BookingDetailsModel saveBooking(BookingDetailsModel book);

	List<Map<String, Object>> getCountForTheDay(String dt);

	void checkIn(BookingDetailsRequest request);

	void checkOut(BookingDetailsRequest request);

	BookingDetailsModel getRoomDtails(BookingDetailsRequest request);

	List<Map<String, Object>> getCountForThePreviousDay(String dt);

}
