package com.emgram.noticeboard.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.emgram.noticeboard.Model.UserModel;
import com.emgram.noticeboard.Service.service;
import com.sun.net.httpserver.Authenticator.Result;

@CrossOrigin(origins="http://localhost:8080")
@RestController
public class Control {
	 @Autowired
	    service service;
	     
	    @RequestMapping("/")
	    public @ResponseBody String root_test()throws Exception{  
	        return "Hello World";
	    }
	 
	    @RequestMapping("/now")
	    public @ResponseBody List<UserModel> now()throws Exception{
	        return service.getDual();
	    }
	    
	    
	    @RequestMapping(value ="/login", method =RequestMethod.GET)
	    @ResponseStatus(value = HttpStatus.OK)
	    public @ResponseBody List<UserModel> LoginPage()throws Exception{
	        return service.getDual();
	        
	        
	    }
	    
	   
	    @PostMapping("/login2")
	    @ResponseStatus(value = HttpStatus.OK)
	    public String LoginPage2(@RequestBody UserModel user) throws Exception{//vue 에서 온파일 모델형식으로 받는다) 
	        //return "{\"result\":\"sentiad2\"}";
	    	//System.out.println(user.getPW());
	        return service.testyohan(user.getId(),user.getPW());
	    }
	
	    
	 
	    
	    /*
	    @GetMapping("/test")
	    public String Test() throws Exception {
	    	return service.Gettest();
	    }
	    */
	    
	    
	    
	    
	    
	    //회원가입 해보기 
	    @PostMapping("/test")
	    @ResponseStatus(value = HttpStatus.OK)
	    public void JoinPage(@RequestBody UserModel user) throws Exception{
	    	service.NewUser(user.getId(),user.getPW(),user.getName());
	    }
	    
	    
	    @PostMapping(value = "/test3")
	    public Map<String, Object> join(@RequestBody UserModel user) throws Exception{
	        Map<String, Object> response = new HashMap<>();
	        //다오에게 보낸것에 결과를 받고 그것을 리턴하기 위해서 값을 넣는더 put
	        
	        response.put("a", "b"); 
			return response;
	    }

	    
	
	    
}

//public @ResponseBody String now()throws Exception{ 