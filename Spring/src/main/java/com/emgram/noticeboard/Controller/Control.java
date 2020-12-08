package com.emgram.noticeboard.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.emgram.noticeboard.Model.UserModel;
import com.emgram.noticeboard.Service.service;

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
	    public String LoginPage2(@RequestBody UserModel user) throws Exception{
	        //return "{\"result\":\"sentiad2\"}";
	    	System.out.println(user.getPW());
	        return service.testyohan(user.getId(),user.getPW());
	    }
	    
	    
	    // 제가 뷰에서 가져온 id pw get ??  a모르겟음 
	    
	    @GetMapping("/test")
	    public String Test() throws Exception {
	    	return service.Gettest();
	    }
	    
}

//public @ResponseBody String now()throws Exception{ 