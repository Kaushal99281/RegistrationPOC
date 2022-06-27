package com.amanTech.registration.service;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amanTech.registration.repo.UserDetailRepo;

@Service
public class Admin {
	@Autowired
	UserDetailRepo userDetailRepo;
	
	public String unlockAccount(String username) {
		
		if(NumberUtils.isParsable(username)) {
			userDetailRepo.save(userDetailRepo.findByMobNo(username).get(0).setActive(true));
		}
		else {
			userDetailRepo.save(userDetailRepo.findByEmail(username).get(0).setActive(true));
		}
		return "User Unlocked";
	}

}
