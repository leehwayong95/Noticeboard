package com.emgram.noticeboard.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emgram.noticeboard.Model.UserModel;
import com.emgram.noticeboard.Service.LoginService;
import com.emgram.noticeboard.Service.SignupService;

import CustomException.NoinfoException;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class SignupController {
	@Autowired
	SignupService signup;
	@Autowired
	LoginService login;
	@PostMapping("/signup")
	public int checkLogin(@RequestBody UserModel user)
	{
		try
		{
			login.getLogin(user.getId(), user.getPW());
		}catch(NoinfoException e)
		{
			return signup.getSignup(user);	
		}
		return -1;
	}
}
