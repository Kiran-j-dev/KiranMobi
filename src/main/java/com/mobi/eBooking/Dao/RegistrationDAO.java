package com.mobi.eBooking.Dao;

import com.mobi.eBooking.Model.BookingModel;
import com.mobi.eBooking.RequestClass.RegistrationRequest;

public interface RegistrationDAO {

	BookingModel register(BookingModel bookingModel);

	BookingModel isUserExist(RegistrationRequest request);

	BookingModel login(String emailId, String password);
	
}
