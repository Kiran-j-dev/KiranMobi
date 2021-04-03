package com.mobi.eBooking.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobi.eBooking.Dao.RegistrationDAO;
import com.mobi.eBooking.Model.BookingModel;
import com.mobi.eBooking.RequestClass.RegistrationRequest;
import com.mobi.eBooking.Service.RegistrationService;
import com.mobi.eBooking.Utill.EncryptUtil;

@Service
public class RegistrationServImpl implements RegistrationService {

	@Autowired
	RegistrationDAO registrationDAO;

	@Override
	public BookingModel register(RegistrationRequest request) {

		BookingModel bookingModel = new BookingModel();
		bookingModel.setName(request.getName());
		bookingModel.setEmailId(request.getEmailId());
		bookingModel.setPhoneNumber(request.getPhoneNumber());
		bookingModel.setPassword(EncryptUtil.md5(request.getPassword()));
		boolean validEmail = EncryptUtil.checkValidMail(request.getEmailId());
		if (validEmail == true) {
			bookingModel = registrationDAO.register(bookingModel);
			return bookingModel;
		} else {
			return null;
		}
	}

	@Override
	public BookingModel isUserExist(RegistrationRequest request) {
		BookingModel bookingModel = registrationDAO.isUserExist(request);
		return bookingModel;
	}

	@Override
	public BookingModel login(String emailId, String password) {
		BookingModel bookingModel = registrationDAO.login(emailId,password);
		return bookingModel;
	}

}
