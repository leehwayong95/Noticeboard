package com.emgram.noticeboard.Dao;

import java.util.List;

import com.emgram.noticeboard.Model.PostModel;
import com.emgram.noticeboard.Model.UserModel;



public interface dao {
    /* DB Select  */
   // public String getDual() throws Exception;
	public List<UserModel> getDual() throws Exception;
	
	
	public String testdb(String id,String pw) throws Exception;
	
	public String Gettest() throws Exception;
	
	
	public void NewUser(String id,String pw,String name) throws Exception;
	
	public List<PostModel> getboard(String id) throws Exception;
	
}


