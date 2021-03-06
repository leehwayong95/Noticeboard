package com.emgram.noticeboard.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.emgram.noticeboard.Model.CommentModel;
import com.emgram.noticeboard.Model.PostModel;
import com.emgram.noticeboard.Service.BoardService;
import com.emgram.noticeboard.Service.JwtService;

import CustomException.NoinfoException;
import CustomException.PostDeleteException;
import CustomException.PostEditException;
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
	    		@RequestParam(value = "index")int index,
	    		HttpServletRequest req) 
	    {
	    	//조회수 기록을 위한 id 복호화
	    	String id = jwt.getId(jwt.get(req.getHeader("jwt-auth-token")));
	    	PostModel targetPost = service.getPost(index);
	    	//작성자는 조회수 포함 안하는 조건문
	    	if(!targetPost.getId().equals(id))
	    		service.insertViewcount(id, targetPost.getPostindex());
	    	return targetPost;
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
	    		status = HttpStatus.NOT_ACCEPTABLE;
	    	}
	    	return new ResponseEntity<Map<String, Object>>(resultMap, status);
	    }
	    
	    @GetMapping("/board/post/download")
	    public @ResponseBody ResponseEntity<Resource> downloadfile(
	    		@RequestParam(value = "index")int index,
	    		HttpServletRequest req) {
	    	Resource file = null;
	    	HttpHeaders header = new HttpHeaders();
	    	try {
				file = service.getFile(index);
				header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"");
				header.setCacheControl("no-cache");
				
				String mediaType = Files.probeContentType(Paths.get(file.getFile().getAbsolutePath()));
				if (mediaType == null) {
					mediaType = "octet-stream";
				}
				header.setContentType(MediaType.parseMediaType(mediaType));
			} catch (IOException e) {
				file = null;
				header.add("status", "false");
			}
	    	return new ResponseEntity<Resource>(file, header, HttpStatus.ACCEPTED);
	    }
	    
	    @PostMapping("/edit")
	    public ResponseEntity<Map<String, Object>> editPost(
	    		@ModelAttribute PostModel post,
	    		HttpServletRequest req)
	    {
	    	Map<String, Object> resultMap = new HashMap<>();
	    	HttpStatus status = null;
	    	post.setId(jwt.getId(jwt.get(req.getHeader("jwt-auth-token"))));
	    	try {
	    		resultMap.put("status",service.editPost(post));
	    		status = HttpStatus.ACCEPTED;
	    	} catch (PostEditException e) {
	    		resultMap.put("status", false);
	    		resultMap.put("reason", e.getMessage());
	    		status = HttpStatus.NOT_ACCEPTABLE;
	    	}
	    	return new ResponseEntity<Map<String, Object>>(resultMap, status);
	    }
	    
	    @PostMapping("/write")
	    public ResponseEntity<Map<String, Object>> writePost(
	    		@ModelAttribute PostModel post,
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
	    		System.out.println("err >> " + e.getMessage());
	    		e.printStackTrace();
	    		resultMap.put("message", e.getMessage());
	    		status = HttpStatus.INTERNAL_SERVER_ERROR;
	    	} catch (Exception e) 
	    	{
				System.out.println("err >> " + e.getMessage());
				e.printStackTrace();
				resultMap.put("message",e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
	    	return new ResponseEntity<Map<String, Object>>(resultMap, status);
	    }
	    
	    @PostMapping("/addcomment")
	    public ResponseEntity<Map<String, Object>> addComment(
	    		@RequestBody CommentModel comment,
	    		HttpServletRequest req) {
	    	String userID = jwt.getId(jwt.get(req.getHeader("jwt-auth-token")));
	    	comment.setId(userID);
	    	Map<String, Object> resultMap = new HashMap<>();
	    	HttpStatus status = null;
	    	if(comment.getPostindex() != null)
	    	{
		    	try {
		    		service.addComment(comment);
		    		resultMap.put("status",true);
		    		status = HttpStatus.ACCEPTED;
		    	} catch (Exception e)
		    	{
		    		resultMap.put("status", false);
		    		resultMap.put("reason", "DBErrorFail");
		    		status = HttpStatus.INTERNAL_SERVER_ERROR;
		    	}
	    	} else {
	    		resultMap.put("status", false);
	    		resultMap.put("reason", "NoSetPostindex");
	    		status = HttpStatus.BAD_REQUEST;
	    	}
	    	return new ResponseEntity<Map<String, Object>>(resultMap, status);
	    }
	    
	    @GetMapping("/loadcomment")
	    public List<CommentModel> loadComments(
	    		@RequestParam(value = "index") int index) {
	    	try {
	    		return service.loadComments(index);
	    	} catch (NoinfoException e) {
	    		return null;
	    	}
	    }
	    
	    @GetMapping("/deletecomment")
	    public ResponseEntity<Map<String, Object>> deleteComment(
	    		@RequestParam(value = "index") int index,
	    		HttpServletRequest req) {
	    	String userID = jwt.getId(jwt.get(req.getHeader("jwt-auth-token")));
	    	Map<String, Object> resultMap = new HashMap<>();
	    	HttpStatus status = null;
	    	try {
	    		if (service.deleteComment(index, userID)) {
		    		resultMap.put("status", true);
		    		status = HttpStatus.ACCEPTED;
	    		} else {
	    			resultMap.put("status", false);
	    			resultMap.put("reason", "Cant delete Comment");
	    			status = HttpStatus.BAD_REQUEST;
	    		}
	    	} catch (NoinfoException e) {
	    		resultMap.put("status", false);
	    		resultMap.put("reason", e.getMessage());
	    		status = HttpStatus.BAD_REQUEST;
	    	}
	    	return new ResponseEntity<Map<String,Object>>(resultMap, status);
	    }
	    
	    @RequestMapping("/")
	    public String test()
	    {
	    	return "fuck yeah!";
	    }
}
