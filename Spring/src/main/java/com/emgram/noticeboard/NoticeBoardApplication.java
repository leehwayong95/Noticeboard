package com.emgram.noticeboard;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.emgram.noticeboard.JwtInterceptor.JwtInterceptor;

@SpringBootApplication
public class NoticeBoardApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(NoticeBoardApplication.class, args);

	}
	
	@Autowired
	private JwtInterceptor jwtInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/user/**")
				.excludePathPatterns(Arrays.asList("/user/signin/**"));
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry)
	{
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("jwt-auth-token");
	}
}
