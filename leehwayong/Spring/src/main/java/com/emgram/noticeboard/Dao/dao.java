package com.emgram.noticeboard.Dao;

import java.util.List;

import com.emgram.noticeboard.Model.CommentModel;
import com.emgram.noticeboard.Model.PostModel;
import com.emgram.noticeboard.Model.UserModel;



public interface dao {
	//PostControl
	public List<PostModel> getPostsList(int min, int postcount);
	public int getPostCount();
	public void writePost(String title, String content, String id, String filepath);
	public PostModel getPost(int index);
	public boolean deletePost(int index);
	public boolean editPost(PostModel target);
	
	//viewcount
	public void insertViewcount(String id, String postindex);
	
	//CommentControl
	public boolean addComment(CommentModel comment);
	public List<CommentModel> loadComments(int postindex);
	public boolean deleteComment(int index, String id);
	
	//Login and Signup
	public List<UserModel> getLogin(String id, String pw);
	public void getSignup(String id, String pw, String name, int permission);
	
}


