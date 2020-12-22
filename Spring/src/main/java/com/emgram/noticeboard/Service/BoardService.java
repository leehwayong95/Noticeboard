package com.emgram.noticeboard.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emgram.noticeboard.Dao.dao;
import com.emgram.noticeboard.Model.PostModel;

import CustomException.PostDeleteException;
import CustomException.PostEditException;
import CustomException.PostInsertException;

@Service
public class BoardService {
	@Autowired
    dao dao;
 
    public List<PostModel> getPostsList(int page){
    	int min = (page - 1) * 10;
        return dao.getPostsList(min, 10);
    }
    
    public int getPostCount() {
    	return dao.getPostCount();
    }
    
    public boolean writePost(PostModel post) throws Exception
    {
    	try {
    		dao.writePost(post.getTitle(), post.getContent(), post.getId());
    		return true;
    	} catch (Exception e)
    	{
    		throw new PostInsertException("Post Insert Error on DB");
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
    
    public boolean editPost(PostModel target) throws PostEditException
    {
    	PostModel targetPost = getPost(Integer.parseInt(target.getPostindex()));
    	if(targetPost.getId().equals(target.getId())) {
    		dao.editPost(target);
    		return true;
    	} else {
    		throw new PostEditException("You're not writer");
    	}
    		
    }
}
