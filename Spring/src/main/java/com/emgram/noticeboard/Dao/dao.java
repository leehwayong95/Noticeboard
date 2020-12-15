package com.emgram.noticeboard.Dao;

import java.util.List;

import com.emgram.noticeboard.Model.PostModel;
import com.emgram.noticeboard.Model.UserModel;



public interface dao {
    /* DB Select  */
   // public String getDual() throws Exception;
	
	public String testdb(String id) throws Exception;
	//이름과 아이디랑 패스워드를 INSERT문에 보내는 interface 작성
	public void input(String id, String PW, String name,String permission) throws Exception;
	
	public String UserCheck(String id);
	
	public List<UserModel> LoginCheck(String id);
	
	public List<PostModel> BoardList();
}


