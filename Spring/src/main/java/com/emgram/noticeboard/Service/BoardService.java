package com.emgram.noticeboard.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emgram.noticeboard.Dao.dao;
import com.emgram.noticeboard.Model.PostModel;

import CustomException.PostInsertException;

@Service
public class BoardService {
	@Autowired
    dao dao;
 
    public List<PostModel> getPostsList(int page){
    	int min = 10 * page - 9;
    	int max = 10 * page;
        return dao.getPostsList(min, max);
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
}
