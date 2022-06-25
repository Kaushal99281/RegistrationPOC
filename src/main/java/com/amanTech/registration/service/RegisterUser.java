package com.amanTech.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amanTech.registration.dto.EmailDetails;
import com.amanTech.registration.dto.UserDetail;
import com.amanTech.registration.repo.UserDetailRepo;

@Service
public class RegisterUser {
	@Autowired
	UserDetailRepo userDetailRepo;
	@Autowired
	EmailServiceImpl emailService;
	@Autowired
	ValidateToken validateToken;

	public String register(UserDetail userDetailObj) {
		// if mobileNo or email already exist return user already exist
		if (userDetailRepo.findByEmail(userDetailObj.getEmail()).isEmpty()
				&& userDetailRepo.findByMobNo(userDetailObj.getMobNo()).isEmpty()) {
			validateToken.generateToken(userDetailObj.getMobNo());
			userDetailRepo.save(userDetailObj);
			emailService.sendMailWithAttachment(new EmailDetails(userDetailObj.getEmail(),userDetailObj.getMobNo()+String.valueOf(validateToken.getToken(userDetailObj.getMobNo()))));
			return "User registred successfully check inbox to verify";
		}
		else {
			return "E-mail/Phone Already exists";
		}
	}
}
