package com.emgram.noticeboard.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emgram.noticeboard.Model.UserModel;
import com.emgram.noticeboard.Service.JwtService;
import com.emgram.noticeboard.Service.LoginService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/user")
public class LoginController {
	@Autowired
	private LoginService login;
	
	@Autowired
	private JwtService jwtService;

	@PostMapping("/signin")
	public ResponseEntity<Map<String, Object>> checkLogin(
			@RequestBody UserModel user,
			HttpServletResponse res
			)
	{
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		UserModel loginuser = new UserModel();
		int permission = login.getLogin(user.getId(), user.getPW());
		System.out.println("id : " + user.getId() + ", pw : " + user.getPW());
		if(permission != -1)
		{
			try {
				loginuser.setId(user.getId());
				loginuser.setPW(user.getPW());
				loginuser.setPermission(permission);
				String token = jwtService.create(loginuser);
				res.setHeader("jwt-auth-token", token);
				resultMap.put("satus", true);
				resultMap.put("data", loginuser);
				status = HttpStatus.ACCEPTED;
			}catch (RuntimeException e)
			{
				System.out.println("정보 생성 실패");
				e.printStackTrace();
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}
		else
		{
			System.out.println("로그인 실패");
			resultMap.put("message", "check your id or pw");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping("/info")
	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req, @RequestBody UserModel user)
	{
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));
			resultMap.put("status", true);
			resultMap.put("request_body", user);
			status = HttpStatus.ACCEPTED;
		}catch(RuntimeException e)
		{
			System.out.println("정보 조회 실패");
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
		
	}
}
