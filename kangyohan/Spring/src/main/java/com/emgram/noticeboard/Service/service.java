package com.emgram.noticeboard.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emgram.noticeboard.Dao.dao;
import com.emgram.noticeboard.Model.PostModel;
import com.emgram.noticeboard.Model.UserModel;
@Service
public class service {
	@Autowired
    dao dao;
 
    public List<UserModel> getDual()throws Exception{
        return dao.getDual();
    }
    
    public String testyohan(String id,String pw)throws Exception{
        return dao.testdb(id,pw);
    }
    
    public String Gettest()throws Exception{
        return dao.Gettest();
    }
    
    public void NewUser(String id, String pw, String name)throws Exception{
        dao.NewUser(id,pw,name);
    }
    
    public List<PostModel> getboard(String id)throws Exception{
        return dao.getboard(id);
    }
    
    //부트스트랩 없이 리스트 가져오기
    public List<PostModel> gettestboard(int clickpage )throws Exception{
        return dao.gettestboard(clickpage);
    }
    //부트스트랩  리스트 전체 개수 가져오기
    public int Allpagecount()throws Exception{
        return dao.Allpagecount();
    }
    
    public List<PostModel> getboardtext(String id)throws Exception{
        return dao.getboardtext(id);
    }

    public PostModel testgetboardtext(String id)throws Exception{
        return dao.testgetboardtext(id);
    }
    
    public void deleteboardtext(String id)throws Exception{
    	dao.deleteboardtext(id);
    }
    
    public void insertboardtext(String title,String content,String id)throws Exception{
    	dao.insertboardtext(title,content,id);
    }
    
    public void updateboardtext(String title,String content,String id,String postindex)throws Exception{
    	dao.updateboardtext(title,content,id,postindex);
    }
    
    
    
    
}
    

