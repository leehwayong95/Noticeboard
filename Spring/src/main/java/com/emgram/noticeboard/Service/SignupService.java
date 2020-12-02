package com.emgram.noticeboard.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emgram.noticeboard.Dao.dao;

@Service
public class SignupService {
	@Autowired
	dao Dao;
	public int getSignup(String id, String pw, String name)
	{
		try
		{
			Dao.getSignup(id, pw, name);
			return 0;
		}catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
}
