package com.emgram.noticeboard.Dao;

import java.util.List;

import com.emgram.noticeboard.Model.UserModel;



public interface dao {
    /* DB Select  */
   // public String getDual() throws Exception;
	public List<UserModel> getDual() throws Exception;
	
	public String testdb() throws Exception;
	//이름과 아이디랑 패스워드를 INSERT문에 보내는 interface 작성
	public int input(String id, String PW, String name) throws Exception;
}


