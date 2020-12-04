package com.emgram.noticeboard.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emgram.noticeboard.Dao.dao;
import com.emgram.noticeboard.Model.PostModel;

@Service
public class BoardService {
	@Autowired
    dao dao;
 
    public List<PostModel> getPostsList(){
        return dao.getPostsList();
    }
}
