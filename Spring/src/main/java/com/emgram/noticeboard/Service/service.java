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
    
    public String testyohan(String id,String pw)throws Exception{
        return dao.testdb(id,pw);
    }
    
    public String Gettest()throws Exception{
        return dao.Gettest();
    }
    
    public void NewUser(String id, String pw, String name)throws Exception{
        dao.NewUser(id,pw,name);
    }
}

