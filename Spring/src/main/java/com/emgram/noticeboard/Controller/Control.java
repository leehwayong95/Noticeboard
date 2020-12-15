package com.emgram.noticeboard.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.emgram.noticeboard.Model.PostModel;
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
	    		return "SUCCESS";
	    	}
	    	else {
	    		return "FAILED";
	    	}
	    }
	    
	    @RequestMapping(value="/logincheck",method= {RequestMethod.POST})
	    public String LoginCheck(@RequestBody UserModel user) throws Exception{
	    	List<UserModel> tempList = new ArrayList<UserModel>();
	    	tempList = service.LoginCheck(user.getId());
	    	if(user.getId().equals(tempList.get(0).getId())) {
	    		if(user.getPW().equals(tempList.get(0).getPW())) {
	    		return "TRUE";
	    		}
	    		else {
	    			return "FALSE";
	    		}
	    	}
	    	else {
	    		return "FALSE";
	    	}
	    }
	    
	    @RequestMapping(value="/boardlist",method= {RequestMethod.POST})
	    public List<PostModel> BoardList() throws Exception{
	    	return service.BoardList();
	    }
}

//public @ResponseBody String now()throws Exception{