package com.emgram.noticeboard.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emgram.noticeboard.Dao.dao;
import com.emgram.noticeboard.Model.PostModel;
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
    //로그인했을 때 아이디랑 비밀번호 체크하는 메소드 생성
    public List<UserModel> LoginCheck(String id) {
    	return dao.LoginCheck(id);
    }
    
    public List<PostModel> BoardList(){
    	return dao.BoardList();
    }
    
    public PostModel BoardDetail(int postindex){
    	return dao.BoardDetail(postindex);
    }
    
    public void BoardCreate(String title, String content, String writer) {
    	dao.BoardCreate(title, content, writer);
    }
}

