package com.emgram.noticeboard.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.emgram.noticeboard.Model.PostModel;
import com.emgram.noticeboard.Service.service;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class Control {
	 @Autowired
	    service service;
	     
	    @RequestMapping("/")
	    public @ResponseBody String root_test()throws Exception{  
	        return "Hello World";
	    }
	 
	    @RequestMapping("/board")
	    public @ResponseBody List<PostModel> now()throws Exception{
	        return service.getPostsList();
	    }
	    
	    @RequestMapping("/test")
	    public String test()
	    {
	    	return "fuck yeah!";
	    }
}
