package com.emgram.noticeboard.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emgram.noticeboard.Service.LoginService;
import com.emgram.noticeboard.Service.SignupService;

import CustomException.NoinfoException;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/signup")
public class SignupController {
	@Autowired
	SignupService signup;
	@Autowired
	LoginService login;
	@GetMapping("args")
	public int checkLogin(
			@RequestParam(value = "id")String id,
			@RequestParam(value = "pw")String pw,
			@RequestParam(value = "name")String name
			)
	{
		try
		{
			login.getLogin(id,pw);
		}catch(NoinfoException e)
		{
			return signup.getSignup(id,pw,name);	
		}
		return -1;
	}
}
