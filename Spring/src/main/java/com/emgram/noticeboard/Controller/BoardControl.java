package com.emgram.noticeboard.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.emgram.noticeboard.Model.PostModel;
import com.emgram.noticeboard.Service.BoardService;
import com.emgram.noticeboard.Service.JwtService;

import CustomException.PostInsertException;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class BoardControl {
	 	@Autowired
	    BoardService service;
	 	
	 	@Autowired
	 	JwtService jwt;
	 
	    @GetMapping("/board")
	    public @ResponseBody List<PostModel> getList()
	    {
	        return service.getPostsList();
	    }
	    
	    @GetMapping("/board/post")
	    public @ResponseBody PostModel getPost(
	    		@RequestParam(value = "index")int index) 
	    {
	    	return service.getPost(index);
	    }
	    
	    @PostMapping("/write")
	    public ResponseEntity<Map<String, Object>> writePost(
	    		@RequestBody PostModel post,
	    		HttpServletRequest req) {
	    	Map<String, Object> resultMap = new HashMap<>();
	    	HttpStatus status = null;
	    	String userID = jwt.getId(jwt.get(req.getHeader("jwt-auth-token")));
	    	post.setId(userID);
	    	try {
	    		service.writePost(post);
	    		resultMap.put("status", true);
	    		status = HttpStatus.ACCEPTED;
	    	} catch(PostInsertException e)
	    	{
	    		System.out.println("PostInsertError");
	    		e.printStackTrace();
	    		resultMap.put("message", e.getMessage());
	    		status = HttpStatus.INTERNAL_SERVER_ERROR;
	    	} catch (Exception e) 
	    	{
				System.out.println("fuck");
				e.printStackTrace();
				resultMap.put("message",e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
	    	return new ResponseEntity<Map<String, Object>>(resultMap, status);
	    }
	    
	    @RequestMapping("/")
	    public String test()
	    {
	    	return "fuck yeah!";
	    }
}
