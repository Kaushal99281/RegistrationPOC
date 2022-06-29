package com.amanTech.registration.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.amanTech.registration.dto.LoginDetails;
import com.amanTech.registration.dto.UserDetail;
import com.amanTech.registration.repo.EmailService;
import com.amanTech.registration.repo.UserDetailRepo;
import com.amanTech.registration.service.Admin;
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
	@Autowired
	Admin adminObj;

	@GetMapping("/registerHome")
	public ModelAndView registerHome() {
		ModelAndView mvObj = new ModelAndView("registration");
		mvObj.addObject("userObj", new UserDetail());
		return mvObj;
	}

	@PostMapping("/registerUser")
	public ModelAndView registerUser(@ModelAttribute UserDetail userObj) {
		userObj.setPassword(Hashing.sha256().hashString(userObj.getPassword(), StandardCharsets.UTF_8).toString());
		ModelAndView mv;
		if (registerUser.register(userObj)) {
			return loginHome();
		}
		mv = new ModelAndView("registration");
		mv.addObject("userObj", new UserDetail());
		return mv;
	}

	@GetMapping("/loginHome")
	public ModelAndView loginHome() {
		ModelAndView mv = new ModelAndView("login");
		return mv.addObject("loginObj", new LoginDetails());
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

	@PostMapping("/unlockAccount")
	public String unlock(@RequestParam(name = "username") String username) {

		return adminObj.unlockAccount(username);
	}

	@PostMapping("/getUser")
	public ResponseEntity<List<UserDetail>> getUser(@RequestParam(name = "username") String username) {
		return new ResponseEntity<>(userDetailRepo.findByMobNo(username), HttpStatus.OK);
	}

	@GetMapping("/showUser")
	public ModelAndView showUser() {
		ModelAndView mv = new ModelAndView("showUser");
		mv.addObject("userList", userDetailRepo.findAll());
		return mv;
	}
}
