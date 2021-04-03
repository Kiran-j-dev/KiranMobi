package com.mobi.eBooking.Controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobi.eBooking.Model.BookingModel;
import com.mobi.eBooking.RequestClass.RegistrationRequest;
import com.mobi.eBooking.Service.RegistrationService;
import com.mobi.eBooking.Utill.EncryptUtil;

@CrossOrigin(origins = "*")
@RestController
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;

	@PostMapping("register")
	public Map<String, Object> register(@RequestBody RegistrationRequest request) {
		Map<String, Object> response = new LinkedHashMap<>();
		BookingModel emailCheck = registrationService.isUserExist(request);
		if (emailCheck == null) {
			BookingModel bookingModel = registrationService.register(request);
			if (bookingModel != null) {
				response.put("Success", "Registered Successfully");
			} else {
				response.put("Error", "Please Provide Valid MailId");
			}
		} else {
			response.put("response", "Email_Id Already Exists");
		}
		return response;
	}

	@PostMapping("login")
	public Map<String, Object> login(@RequestBody RegistrationRequest request) {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		boolean emailCheck = true;
		if (Strings.isEmpty(request.getEmailId())) {
			data.put("Error", "UserName Should be Valid");
			return data;
		}
		if (Strings.isEmpty(request.getPassword())) {
			data.put("Error", "Password Should be Valid");
			return data;
		}
		emailCheck = EncryptUtil.checkValidMail(request.getEmailId());
		if (!emailCheck) {
			data.put("Error", "EmailId Should be Valid");
			return data;
		}
		BookingModel userCheck = registrationService.isUserExist(request);
		if (userCheck != null) {
			BookingModel login = registrationService.login(request.getEmailId(), request.getPassword());
			if (login != null) {
				data.put("Success", "Login Success");
			} else {
				data.put("Error", "PLEASE ENTER THE CORRECT PASSWORD");
			}
		} else {
			data.put("Error", "PLEASE ENTER VALID USER CREDENTIALS");
		}
		return data;
	}
}
