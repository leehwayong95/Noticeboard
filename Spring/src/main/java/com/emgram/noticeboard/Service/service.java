package com.emgram.noticeboard.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emgram.noticeboard.Dao.dao;
import com.emgram.noticeboard.Model.UserModel;
@Service
public class service {
	@Autowired
    dao dao;
 
    public List<UserModel> getDual()throws Exception{
        return dao.getDual();
    }
    
    public String testyohan() throws Exception{
    	return dao.testdb();
    }
    
    public int input(String id, String PW, String name) throws Exception{
    	return dao.input(id, PW, name);
    }
}

