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
 

    
    public String testyohan(String id) throws Exception{
    	return dao.testdb(id);
    }
    
    //회원가입 정보를 DB에 넣어주는 dao와 연결된 service 메소드
    public void input(String id, String PW, String name,String permission) throws Exception{
    	dao.input(id, PW, name, permission);
    }
    
    public String UserCheck(String id) {
    	return dao.UserCheck(id);
    }
}

