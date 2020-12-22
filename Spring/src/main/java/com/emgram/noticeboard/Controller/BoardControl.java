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

import CustomException.PostDeleteException;
import CustomException.PostInsertException;

@CrossOrigin(origins = "http://localhost/")
@RestController
@RequestMapping("/api")
public class BoardControl {
	 	@Autowired
	    BoardService service;
	 	
	 	@Autowired
	 	JwtService jwt;
	 
	    @GetMapping("/board")
	    public @ResponseBody List<PostModel> getList(
	    		@RequestParam(value="page")int page)
	    {
	        return service.getPostsList(page);
	    }
	    
	    @GetMapping("/postcount")
	    public @ResponseBody int getPostCount()
	    {
	    	return service.getPostCount();
	    }
	    
	    @GetMapping("/board/post")
	    public @ResponseBody PostModel getPost(
	    		@RequestParam(value = "index")int index) 
	    {
	    	return service.getPost(index);
	    }
	    
	    @GetMapping("/board/post/delete")
	    public @ResponseBody ResponseEntity<Map<String, Object>> deletePost(
	    		@RequestParam(value ="index")int index,
	    		HttpServletRequest req)
	    {
	    	String header = req.getHeader("jwt-auth-token");
	    	String id = jwt.getId(jwt.get(header));
	    	Map<String, Object> resultMap = new HashMap<>();
	    	HttpStatus status = null;
	    	try
	    	{
	    		boolean result = service.deletePost(index, id);
	    		resultMap.put("status", result);
	    		status = HttpStatus.ACCEPTED;
	    	} catch (PostDeleteException e) {
	    		resultMap.put("status", false);
	    		resultMap.put("reason", e.getMessage());
	    		status = HttpStatus.BAD_REQUEST;
	    	}
	    	return new ResponseEntity<Map<String, Object>>(resultMap, status);
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
