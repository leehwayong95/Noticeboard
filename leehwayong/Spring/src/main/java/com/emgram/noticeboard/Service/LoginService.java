package com.emgram.noticeboard.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emgram.noticeboard.Dao.dao;
import com.emgram.noticeboard.Model.UserModel;

import CustomException.NoinfoException;

@Service
public class LoginService {
	@Autowired
	dao Dao;
	
	public UserModel getLogin(String id, String pw) throws NoinfoException
	{
		List<UserModel> userList = Dao.getLogin(id,pw);
		if(userList.isEmpty() || userList.size() > 1)
			throw new NoinfoException("Can not find Userinfo");
		else
			return userList.get(0);
	}
}
