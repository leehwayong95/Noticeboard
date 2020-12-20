package com.emgram.noticeboard.Dao;

import java.util.List;

import com.emgram.noticeboard.Model.PostModel;
import com.emgram.noticeboard.Model.UserModel;



public interface dao {
	public List<PostModel> getPostsList(int min, int max);
	public int getPostCount();
	public void writePost(String title, String content, String id);
	public List<UserModel> getLogin(String id, String pw);
	public void getSignup(String id, String pw, String name, int permission);
	public PostModel getPost(int index);
}


