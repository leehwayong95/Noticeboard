package com.emgram.noticeboard.Dao;

import java.util.List;

import com.emgram.noticeboard.Model.PostModel;
import com.emgram.noticeboard.Model.UserModel;



public interface dao {
    /* DB Select  */
   // public String getDual() throws Exception;
	public List<UserModel> getDual() throws Exception;
	
	//로그인 확인하기 
	public String testdb(String id,String pw) throws Exception;
	
	public String Gettest() throws Exception;
	
	
	public void NewUser(String id,String pw,String name) throws Exception;
	
	
	//게시판 리스트 불러오기 
	public List<PostModel> getboard(String id) throws Exception;
	
	public List<PostModel> getboardtext(String num) throws Exception;
	
	
	//게시글 가져오기 
	public PostModel testgetboardtext(String num) throws Exception;
	
	
	//게시판 글 삭제
	public void deleteboardtext(String num) throws Exception;
	
	
	// 게시판 글 추가 
	public void insertboardtext(String title,String content,String id) throws Exception;
	
	//게시글 수정
	public void updateboardtext(String title,String content,String id,String postindex) throws Exception;
}

