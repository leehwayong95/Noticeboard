package com.emgram.noticeboard.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emgram.noticeboard.Service.LoginService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/signup")
public class SignupController {
	@Autowired
	LoginService login;
	@GetMapping("args")
	public int checkLogin(
			@RequestParam(value = "id")String id,
			@RequestParam(value = "pw")String pw
			)
	{
		if (login.getLogin(id,pw)==-1)
		{
			return 0;
		}
		else
			return -1;
	}
}
