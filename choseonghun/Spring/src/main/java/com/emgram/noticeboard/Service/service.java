package com.emgram.noticeboard.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.emgram.noticeboard.Dao.dao;
import com.emgram.noticeboard.Model.PostModel;
import com.emgram.noticeboard.Model.UserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Service
public class service implements dao{
	@Autowired
    dao dao;
 
 
    //회원가입 정보를 DB에 넣어주는 dao와 연결된 service 메소드
    public void input(String id, String PW, String name,String permission) throws Exception{
    	dao.input(id, PW, name, permission);
    }
    
    public String UserCheck(String id) {
    	return dao.UserCheck(id);
    }
    //로그인했을 때 아이디랑 비밀번호 체크하는 메소드 생성
    public List<UserModel> LoginCheck(String id) {
    	return dao.LoginCheck(id);
    }
    
    public List<PostModel> BoardList(){
    	return dao.BoardList();
    }
    
    public PostModel BoardDetail(int postindex){
    	return dao.BoardDetail(postindex);
    }
    
    public void BoardCreate(String title, String content, String writer) {
    	dao.BoardCreate(title, content, writer);
    }
    
    @Value("${secretkey}")
    private String SECRET_KEY;
    
//	private static final String SECRET_KEY = "aasjjkjaskjdl1k2naskjkdakj34c8sa";
//	// "aasjjkjaskjdl1k2naskjkdakj34c8sa"
    public String createToken(String subject, long ttlMillis) {
        if (ttlMillis <= 0) {
            throw new RuntimeException("Expiry time must be greater than Zero : ["+ttlMillis+"] ");
        }
        // 토큰을 서명하기 위해 사용해야할 알고리즘 선택
        SignatureAlgorithm  signatureAlgorithm= SignatureAlgorithm.HS256;
 
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .signWith(signatureAlgorithm, signingKey);
        long nowMillis = System.currentTimeMillis();
        builder.setExpiration(new Date(nowMillis + ttlMillis));
        return builder.compact();
    }
  
    public String getSubject(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    
    public String NameSearch(String id) {
    	if(id.equals("FALSE")) {
    		return "FALSE";
    	}
    	else {
    		return dao.NameSearch(id);
    	}
    }
    
    public void BoardDelete(int postindex) {
    	dao.BoardDelete(postindex);
    }
    
    public void BoardUpdate(String title, String content, int postindex) {
    	dao.BoardUpdate(title, content, postindex);
    }
    
    public String PageCount() {
    	return dao.PageCount();
    }
    
    public List<PostModel> PostPage(String startnum, String num) {
    	return dao.PostPage(startnum, num);
    }
}

