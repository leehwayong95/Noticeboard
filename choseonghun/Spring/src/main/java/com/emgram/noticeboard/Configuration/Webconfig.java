//package com.emgram.noticeboard.Configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class Webconfig implements WebMvcConfigurer {
//    private static final String[] EXCLUDE_PATHS = {
//        "/api/users/create",
//        "/api/users/login",
//        "/member/**",
//        "/error/**"
//    };
// 
//    @Autowired
//    private JwtInterceptor jwtInterceptor;
// 
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(jwtInterceptor)
//                        .addPathPatterns("/**")
//                        .excludePathPatterns(EXCLUDE_PATHS);
//    }
//}
