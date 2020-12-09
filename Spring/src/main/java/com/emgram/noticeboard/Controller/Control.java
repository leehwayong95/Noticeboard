package com.emgram.noticeboard.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	    
	    @RequestMapping("/test")
	    public String Test(@RequestParam("id") String id) throws Exception {
	    	return service.testyohan(id);
	    }
	    
	    //회원가입을 한다
	    @RequestMapping(value = "/input",method= {RequestMethod.POST} )
	    public String SignUp(@RequestBody UserModel user) throws Exception {
	    	//만약 id가 존재하면 그 false가 출력되고 id가 없으면 success가 출력된다.
	    	if(service.UserCheck(user.getId()).equals("")){
	    		service.input(user.getId(),user.getPW(),user.getName(),user.getPermission());
	    		return "Success";
	    	}
	    	else {
	    		return "False";
	    	}
	    	
	    }
}

//public @ResponseBody String now()throws Exception{