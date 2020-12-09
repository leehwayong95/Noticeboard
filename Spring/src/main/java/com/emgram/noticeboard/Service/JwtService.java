package com.emgram.noticeboard.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.emgram.noticeboard.Model.UserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtService {
	@Value("${jwt.salt")
	private String salt;
	
	@Value("${jwt.expmin}")
	private Long expireMin;
	
	public String create (final UserModel user)
	{
		System.out.println("time : " + expireMin);
		final JwtBuilder builder = Jwts.builder();
		builder.setHeaderParam("typ","JWT");
		
		builder.setSubject("logintoken")
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*expireMin))
				.claim("Userid", user.getId())
				.claim("permission", user.getPermission())
				.claim("name", user.getName());
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
		
		final String jwt = builder.compact();
		System.out.println("토큰발행 : " + jwt.toString());
		return jwt;
	}
	
	public void checkValid(final String jwt)
	{
		System.out.println("토큰 점검 : " + jwt.toString());
		Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
	}
	
	public Map<String, Object> get(final String jwt)
	{
		System.out.println("jwt token parameter : "+jwt);
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);

		}catch (final Exception e)
		{
			throw new RuntimeException("keyParsingError:fuck");
		}
		
		System.out.println("claims : " + claims.toString());
		return claims.getBody();
	}
	
	public String getName(Map<String, Object> claims)
	{
		return claims.get("name").toString();
	}
}
