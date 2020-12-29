package com.emgram.noticeboard.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.emgram.noticeboard.Dao.dao;
import com.emgram.noticeboard.Model.PostModel;

import CustomException.PostDeleteException;
import CustomException.PostEditException;
import CustomException.PostInsertException;

@Service
public class BoardService {
	
	private String UPLOADED_FOLDER = "./upload/";
	
	@Autowired
    dao dao;
 
    public List<PostModel> getPostsList(int page){
    	int min = (page - 1) * 10;
        return dao.getPostsList(min, 10);
    }
    
    public int getPostCount() {	
    	return dao.getPostCount();
    }
    
    public boolean writePost(PostModel post) throws PostInsertException
    {
    	try {
    		String filepath = "NULL";
    		Path path;
    		if(post.getFile() != null) {
    			byte[] bytes = post.getFile().getBytes();
				filepath = UPLOADED_FOLDER + post.getFile().getOriginalFilename();
				path = Paths.get(filepath);
				Files.write(path, bytes);
    		}
			dao.writePost(post.getTitle(), post.getContent(), post.getId(), (post.getFile() == null) ? "NULL" : filepath);
    		return true;
    	} catch (IOException e)
    	{
    		e.printStackTrace();
    		throw new PostInsertException("File Load Error");
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new PostInsertException("Post Insert Error");
    	}
    }
    
    public boolean editPost(PostModel target) throws PostEditException
    {
    	PostModel targetPost = getPost(Integer.parseInt(target.getPostindex()));
    	if(targetPost.getId().equals(target.getId())) {
    		try {
        		targetPost = target;
        		if(target.getFile() != null) {
        			byte[] bytes = target.getFile().getBytes();
        			String filepath = UPLOADED_FOLDER + target.getFile().getOriginalFilename();
    				Path path = Paths.get(filepath);
    				Files.write(path, bytes);
    				targetPost.setFilepath(filepath);
        		}
    			dao.editPost(targetPost);
        		return true;
        	} catch (IOException e)
        	{
        		e.printStackTrace();
        		throw new PostEditException("File Load Error");
        	} catch (Exception e) {
        		e.printStackTrace();
        		throw new PostEditException("Post Insert Error");
        	}
    	} else {
    		throw new PostEditException("You're not writer");
    	}	
    }
    
    public PostModel getPost(int index)
    {
    	return dao.getPost(index);
    }
    
    
    public boolean deletePost(int index, String id) throws PostDeleteException
    {
    	PostModel targetPost = getPost(index);
    	if(targetPost.getId().equals(id))
    	{//url 강제 접근시 막기위한 두번째 검사
			try {
				dao.deletePost(index);
				return true;
			} catch (Exception e) {
				throw new PostDeleteException("Already deleted");
			}
    	}
    	else
    		throw new PostDeleteException("You're not writer");
    }
    
    public Resource getFile(int index) throws IOException
    {
    	PostModel targetPost = dao.getPost(index);
    	String targetPath = targetPost.getFilepath();
    	File target = new File(targetPath);
    	String mimeType = Files.probeContentType(Paths.get(target.getAbsolutePath()));
    	if(mimeType == null) {
    		mimeType = "octet-stream";
    	}
    	return new UrlResource(target.toURI());
    }
}
