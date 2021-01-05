package com.emgram.noticeboard.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emgram.noticeboard.Dao.dao;
import com.emgram.noticeboard.Model.UserModel;

@Service
public class SignupService {
	@Autowired
	dao Dao;
	public int getSignup(UserModel user)
	{
		try
		{
			Dao.getSignup(user.getId(),user.getPW(),user.getName(), user.getPermission());
			return 0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
}
