package com.emgram.noticeboard.Dao;

import java.util.List;

import com.emgram.noticeboard.Model.PostModel;
import com.emgram.noticeboard.Model.UserModel;



public interface dao {
    /* DB Select  */
   // public String getDual() throws Exception;
	
	//이름과 아이디랑 패스워드를 INSERT문에 보내는 interface 작성
	public void input(String id, String PW, String name,String permission) throws Exception;
	
	public String UserCheck(String id);
	
	public List<UserModel> LoginCheck(String id);
	
	public List<PostModel> BoardList();
	
	public PostModel BoardDetail(int postindex);
	
	public void BoardCreate(String title, String content, String writer);
	
	String createToken(String subject, long ttlMillis);
	 
    String getSubject(String token);
    
    public String NameSearch(String id);
    
    public void BoardDelete(int postindex);
    
    public void BoardUpdate(String title, String content, int postindex);
    
    public List<PostModel> PostPage(String startnum, String num);
    
    public String PageCount();
}


