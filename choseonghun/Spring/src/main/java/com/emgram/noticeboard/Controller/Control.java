package com.emgram.noticeboard.Controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	    
	    
	    //회원가입을 한다
	    @RequestMapping(value = "/input",method= {RequestMethod.POST} )
	    public String SignUp(@RequestBody UserModel user) throws Exception {
	    	//만약 id가 존재하면 그 false가 출력되고 id가 없으면 success가 출력된다.
	    	if(service.UserCheck(user.getId()) == null){
	    		service.input(user.getId(),user.getPW(),user.getName(),user.getPermission());
	    		return "SUCCESS";
	    	}
	    	else {
	    		return "FAILED";
	    	}
	    }
	    
	    @RequestMapping(value="/logincheck",method= {RequestMethod.POST})
	    public Map<String, Object> LoginCheck(@RequestBody UserModel user) throws Exception{
	    	List<UserModel> tempList = new ArrayList<UserModel>();
	    	Map<String, Object> map = new LinkedHashMap<String, Object>();
	    	String result="result";
	    	tempList = service.LoginCheck(user.getId());
	    	if(user.getId().equals(tempList.get(0).getId())) {
	    		if(user.getPW().equals(tempList.get(0).getPW())) {
	    			String token = service.createToken(tempList.get(0).getId(),(10 * 1000 * 60) );
	    			map.put(result, token);
	    			return map;
	    		}
	    		else {
	    			map.put(result,"FALSE");
	    			return map;
	    		}
	    	}
	    	else {
	    		map.put(result, "FALSE");
	    		return map;
	    	}
	    }
	    
	    @RequestMapping(value="/boardlist",method= {RequestMethod.POST})
	    public List<PostModel> BoardList() throws Exception{
	    	return service.BoardList();
	    }
	    
	    @RequestMapping(value="/boarddetail", method= {RequestMethod.POST})
	    public PostModel BoardDetail(@RequestBody PostModel postindex){
	    	PostModel a = new PostModel();
	    	a= service.BoardDetail(postindex.getPostindex());
	    	return a;
	    }
	    
	    @RequestMapping(value="/boardcreate", method= {RequestMethod.POST})
	    public void BoardCreate(@RequestBody PostModel post) {
	    	service.BoardCreate(post.getTitle(), post.getContent(), post.getWriter());
	    }
	    
	    @RequestMapping(value="/tokencheck", method= {RequestMethod.POST})
	    public String TokenCheck(@RequestHeader("Authorization") String token) {
	    	try{
	    		//토큰을 넣었을 때의 값을 읽어올 것
	    		return service.getSubject(token);
	    	}
	    	catch(Exception e) {
	    		//만약에 만료되었을 떄 FALSE 값 반환
	    		return "FALSE";
	    	}
	    }
	    
	    @RequestMapping(value="/tokenname", method= {RequestMethod.POST})
	    public String TokenName(@RequestHeader("Authorization") String token) {
	    	try {
	    		return service.NameSearch(service.getSubject(token));
	    	}
	    	catch(Exception e) {
	    		return "FALSE";
	    	}
	    }
	    
	    @RequestMapping(value="/userpermission", method= {RequestMethod.POST})
	    public List<UserModel> UserPermission(@RequestHeader("Authorization") String token) {
	    	String id = service.getSubject(token);
	    	return service.LoginCheck(id);
	    	//try catch로 만료기간 해볼것
	    }
	    
	    @RequestMapping(value="/boarddelete", method= {RequestMethod.POST})
	    public String BoardDelete(@RequestBody PostModel post) {
	    	try {
	    		service.BoardDelete(post.getPostindex());
	    		return "SUCCESS";
	    	} catch(Exception e) {
	    		return "FALSE";
	    	}
	    }
	    
	    @RequestMapping(value="/boardupdate", method= {RequestMethod.POST})
	    public String BoardUpdate(@RequestBody PostModel post) {
	    	try {
	    	service.BoardUpdate(post.getTitle(),post.getContent(), post.getPostindex());
	    	return "SUCCESS";
	    	} 
	    	catch(Exception e) {
	    		return "FALSE";
	    	}
	    }
	    
	    @RequestMapping(value="/pagecount", method= {RequestMethod.POST})
	    public String PageCount() {
	    	return service.PageCount();
	    }
	    
	    @RequestMapping(value="/postpage", method= {RequestMethod.POST})
	    public List<PostModel> PostPage(@RequestBody Map<String,Object> map){
	    	String startnum = map.get("startnum").toString();
	    	String num = map.get("num").toString();
	    	return service.PostPage(startnum, num);
	    }
}

//public @ResponseBody String now()throws Exception{