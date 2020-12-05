package com.emgram.noticeboard.Dao;

import java.util.List;

import com.emgram.noticeboard.Model.UserModel;



public interface dao {
    /* DB Select  */
   // public String getDual() throws Exception;
	public List<UserModel> getDual() throws Exception;
	
	public String testdb() throws Exception;
}


