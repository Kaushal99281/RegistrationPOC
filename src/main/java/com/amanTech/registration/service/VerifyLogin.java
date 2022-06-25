package com.amanTech.registration.service;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amanTech.registration.dto.UserDetail;
import com.amanTech.registration.repo.UserDetailRepo;
import com.google.common.hash.Hashing;

@Service
public class VerifyLogin {
	@Autowired
	UserDetailRepo userDetailRepo;

	public String verify(String username, String password) {
		password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();

//		check username is valid or not 
//		if valid check max attemps
// 		if attemp is less check for username password
		UserDetail userDetail;
		List<UserDetail> userDetailList;
		if (NumberUtils.isParsable(username)) {
			userDetailList = userDetailRepo.findByMobNo(username);

		} else {
			userDetailList = userDetailRepo.findByEmail(username);
		}
		if (userDetailList.isEmpty()) {
			return "Invalid Username/Password";
		} else {
			userDetail = userDetailList.get(0);
			if (userDetail.getInvalidAttemps() > 2) {
				return "Account Locked Contact Admin";
			} else {
				List<UserDetail> userValidList = userDetailRepo.validateLogin(username, password);
				if (userValidList.isEmpty()) {
					userDetail.setInvalidAttemps(userDetail.getInvalidAttemps() + 1);
					userDetailRepo.save(userDetail);
					return "Invalid Username/Password";
				} else {
					userDetail.setInvalidAttemps(0);
					userDetailRepo.save(userDetail);
					return "Welcome";
				}
			}
		}
	}
}
