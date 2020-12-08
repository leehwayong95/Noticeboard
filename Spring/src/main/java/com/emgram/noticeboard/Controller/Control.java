package com.emgram.noticeboard.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	        return "NoticeBoard-client/src/components/Test.Vue";
	    }
	 
	    @RequestMapping("/now")
	    public @ResponseBody List<UserModel> now()throws Exception{
	        return service.getDual();
	    }
	    
	    @RequestMapping("/test")
	    public String Test() throws Exception {
	    	return service.testyohan();
	    }
	    
	    @RequestMapping(value = "/input",method= {RequestMethod.POST} )
	    public int input(@RequestBody UserModel user) throws Exception {
	    	return service.input(user.getId(),user.getPW(),user.getName());
	    }
}

//public @ResponseBody String now()throws Exception{