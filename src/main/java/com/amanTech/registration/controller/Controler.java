package com.amanTech.registration.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.amanTech.registration.dto.UserDetail;
import com.amanTech.registration.repo.EmailService;
import com.amanTech.registration.repo.UserDetailRepo;
import com.amanTech.registration.service.RegisterUser;
import com.amanTech.registration.service.ValidateToken;
import com.amanTech.registration.service.VerifyLogin;
import com.google.common.hash.Hashing;

@RestController
@RequestMapping("/registration")
public class Controler {
	@Autowired
	EmailService emailService;
	@Autowired
	RegisterUser registerUser;
	@Autowired
	ValidateToken validateToken;
	@Autowired
	UserDetailRepo userDetailRepo;
	@Autowired
	VerifyLogin verifyLogin;

	@GetMapping("/registerHome")
	public ModelAndView registerHome() {
		ModelAndView mvObj = new ModelAndView();
		mvObj.setViewName("registration");
		return mvObj;
	}

	@PostMapping("/registerUser")
	public String registerUser(@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName, @RequestParam(name = "email") String email,
			@RequestParam(name = "mobNo") String mobile, @RequestParam(name = "password") String password) {
		UserDetail userObj = new UserDetail(firstName, lastName, email, mobile,
				Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString(), false,0);
		return registerUser.register(userObj);
	}

	@PostMapping("/verifyEmail")
	public String verifyEmail(@RequestParam(name = "token") String token) {
		System.out.println(token);
		if (Integer.parseInt(token.substring(10, token.length())) == validateToken.getToken(token.substring(0, 10))) {
			validateToken.clearToken(token.substring(10, token.length()));
			List<UserDetail> user = userDetailRepo.findByMobNo(token.substring(0, 10));
			userDetailRepo.save(user.get(0).setActive(true));
			return "User Verified";
		}
		return "Token expired";
	}

	@PostMapping("/login")
	public String userLogin(@RequestParam(name = "username") String userName,
			@RequestParam(name = "password") String password) {
		return verifyLogin.verify(userName, password);
	}

}
