package com.mobi.eBooking.Service;

import com.mobi.eBooking.Model.BookingModel;
import com.mobi.eBooking.RequestClass.RegistrationRequest;

public interface RegistrationService {

	BookingModel register(RegistrationRequest request);

	BookingModel isUserExist(RegistrationRequest request);

	BookingModel login(String emailId, String password);

}
