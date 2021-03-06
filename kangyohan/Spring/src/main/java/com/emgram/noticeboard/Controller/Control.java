package com.emgram.noticeboard.Controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.emgram.noticeboard.Model.PostModel;
import com.emgram.noticeboard.Model.UserModel;
import com.emgram.noticeboard.Service.SecurityService;
import com.emgram.noticeboard.Service.service;

@CrossOrigin(origins="http://localhost:8080")
@RestController
public class Control {
	 @Autowired
	    service service;
	 
	 @Autowired
	    private SecurityService securityService;
	 
	 @GetMapping("/gen/token")
	    public Map<String, Object> genToken(@RequestParam(value="subject") String subject) {
	        String token = securityService.createToken(subject, (2 * 1000 * 60));
	        Map<String, Object> map = new LinkedHashMap<String, Object>();
	        map.put("result", token);
	        return map;
	    }
	 
	    @ResponseBody
	    @GetMapping("/get/subject")
	    public Map<String, Object> getSubject(@RequestParam("token") String token) {
	        String subject = securityService.getSubject(token);
	        Map<String, Object> map = new LinkedHashMap<String, Object>();
	        map.put("result", subject);
	        return map;
	    }

	//===================================================================
	    //테스트
	    @RequestMapping("/")
	    public @ResponseBody String root_test()throws Exception{  
	        return "Hello World";
	    }
	    //테스트
	    @RequestMapping("/now")
	    public @ResponseBody List<UserModel> now()throws Exception{
	        return service.getDual();
	    }
	    
	    
	    @RequestMapping(value ="/login", method =RequestMethod.GET)
	    @ResponseStatus(value = HttpStatus.OK)
	    public @ResponseBody List<UserModel> LoginPage()throws Exception{
	        return service.getDual();
	    }
	    
	   
	    
	    // 로그인 하기  토큰 발급 
	    @PostMapping("/login2")
	    @ResponseStatus(value = HttpStatus.OK)
	    public Map<String, Object> LoginPage2(@RequestBody UserModel user) throws Exception{//vue 에서 온파일 모델형식으로 받는다) 
	    	
	    	
	        String Name= service.testyohan(user.getId(),user.getPW());
	        Map<String, Object> map = new LinkedHashMap<String, Object>();
	        if(Name == null) {// 로그인 실패했을 경우 
	        	map.put("result","empty");
	        }
	        else { //토큰 발급 
	        	String token = securityService.createToken(Name, (5 * 1000 * 60));
	        	map.put("result", token);
	        }
	        System.out.println(map);
	        return map;
	    }
	    
	    
	    //로그인 유지되는지 확인 
		@PostMapping("/logincheck")
		public Map<String, Object> logincheck(@RequestBody Map<String, String> token) {
			try {
				System.out.println(token);
				String code = (String) token.get("result");
				System.out.println(code);
				String subject = securityService.getSubject(code);
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				if (code == null) {
					map.put("result", "empty");
				} else {
					map.put("result", subject);
				}
				return map;
			//Map: LinkedHashMap차이점 : 키와 값이 순서가 보장된다 / HashMap에서는 순서가 지켜지지 않는다.
			} catch (Exception e) {
				Map<String, Object> map2 = new LinkedHashMap<String, Object>();
				System.out.println("알수 없는 오류 ");
				map2.put("result", "empty");
				return map2;
			}
		}
	    
		
		
		//헤더로 받아보기 
		// Httpheaders
		@RequestMapping(value ="/headlogincheck", method = RequestMethod.GET)
		public Map<String, Object> headlogincheck(@RequestHeader ("Authorization") String token){
			try {
				//System.out.println(token);
				//String code = (String) token.get("result");
				System.out.println(token);
				String subject = securityService.getSubject(token);
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				if (token == null) {
					map.put("result", "empty");
				} else {
					map.put("result", subject);
				}
				return map;

			} catch (Exception e) {
				Map<String, Object> map2 = new LinkedHashMap<String, Object>();
				System.out.println("알수 없는 오류 ");
				map2.put("result", "empty");
				return map2;
			}
		}
		
	    /*
	    @GetMapping("/test")
	    public String Test() throws Exception {
	    	return service.Gettest();
	    }
	    */
	    
	    
		
		 // 부트 스태랩 없이 게시판 리스트 가저요기 
		//1 이들어가면 처음 5개 2를 누르면 그다음 5개 반환
	    @GetMapping("/gettestboard")
	    public List<PostModel> GetTestboard(@RequestParam int clickpage) throws Exception{//vue 에서 온파일 모델형식으로 받는다) 
	    	int PageNum = clickpage;
	        PageNum = 5*(PageNum-1);
	    	return service.gettestboard(PageNum);
	    }
		
	    // 부트 스태랩 없이 게시판 리스트 가저요기 최종 몇 페이지 필요한지 반환함 
	    @GetMapping("/Allpagecount")
	    public int Allpagecount(@RequestParam(value="pageNo", required=false)int pageNo) throws Exception{
	    	int count;
	    	int plus;
	    	count=service.Allpagecount(); //몫 구하는곳 
	    	plus=service.Allpagecount(); // 나머지 구하는곳 
	    	
	    	count=count/5;
	    	// 나머지가 1이상이면 페이지 추가
	    	if(plus%5>0) {
	    		count=count+1;
	    	}
	    	else {
	    	}
	    	return count;
	    }
		
	    
	    
	    
	    //===========================================================================
	    
		
		
		
		
	    //게시판 리스트 가저요기 
	    @PostMapping("/getboard")
	   // @ResponseStatus(value = HttpStatus.OK)
	    public List<PostModel> Getboard(@RequestBody PostModel Post) throws Exception{//vue 에서 온파일 모델형식으로 받는다) 
	    	//RequestBody에서 다양하게 받는 방법을 알고 싶다. ======================================================
	        return service.getboard(Post.getId());
	    }
	    
	   
	    //해당 게시글 가져오기 
	    @PostMapping("/getboardtext")
	    //@ResponseStatus(value = HttpStatus.OK)
	    public List<PostModel> Getboardtext(@RequestBody PostModel Post) throws Exception{//vue 에서 온파일 모델형식으로 받는다) 
	    	//RequestBody에서 다양하게 받는 방법을 알고 싶다. ======================================================
	        System.out.println(Post.getPostindex());
	    	return service.getboardtext(Post.getPostindex());
	    }
	    
	    
	    // 게시글 보이게 하기
	    @PostMapping("/testgetboardtext")
	    //@ResponseStatus(value = HttpStatus.OK)
	    public PostModel boardtext(@RequestBody PostModel Post) throws Exception{//vue 에서 온파일 모델형식으로 받는다) 
	    	//RequestBody에서 다양하게 받는 방법을 알고 싶다. ======================================================
	        return service.testgetboardtext(Post.getPostindex());
	    }
	    
	    // 게시글 삭제
	    @PostMapping("/deleteboardtext")
	    //@ResponseStatus(value = HttpStatus.OK)
	    public void deleteboardtext(@RequestBody PostModel Post,HttpServletRequest req) throws Exception{//vue 에서 온파일 모델형식으로 받는다) 
	    	//String jwt = req.getHeader("Authorization");
	    	//헤더 가져오기 
	    	service.deleteboardtext(Post.getPostindex());
	    	
	    	
	    	
	    	//RequestBody에서 다양하게 받는 방법을 알고 싶다. ======================================================
	    }
	    
	    //게시글 추가 
	    @PostMapping("/insertboardtext")
	    //@ResponseStatus(value = HttpStatus.OK)
	    public void insertboardtext(@RequestBody PostModel Post) throws Exception{//vue 에서 온파일 모델형식으로 받는다) 
	    	service.insertboardtext(Post.getTitle(),Post.getContent(),Post.getId());
	    	//RequestBody에서 다양하게 받는 방법을 알고 싶다. ======================================================
	    }
	    
	    //게시글 업데이트 
	    @PostMapping("/updateboardtext")
	    //@ResponseStatus(value = HttpStatus.OK)
	    public void updateboardtext(@RequestBody PostModel Post) throws Exception{//vue 에서 온파일 모델형식으로 받는다) 
	    	service.updateboardtext(Post.getTitle(),Post.getContent(),Post.getId(),Post.getPostindex());
	    	//RequestBody에서 다양하게 받는 방법을 알고 싶다. ======================================================
	    }
	    
	    
	    /*
	    //회원가입 해보기 
	    @PostMapping("/test") 
	    //@ResponseStatus(value = HttpStatus.OK)
	    public void JoinPage(@RequestBody UserModel user) throws Exception{
	    	service.NewUser(user.getId(),user.getPW(),user.getName());
	    }
	    
	    */

	    //회원가입 해보기 
	    @PostMapping("/test") 
	    //@ResponseStatus(value = HttpStatus.OK)
	    public String JoinPage(@RequestBody UserModel user) throws Exception{
	    	try {
	    	service.NewUser(user.getId(),user.getPW(),user.getName());
	    	String Act = "1";
	    	return Act;
	    	}
	    	catch (Exception e){
	    		String Act = "0";
		    	return Act;
	    	}
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