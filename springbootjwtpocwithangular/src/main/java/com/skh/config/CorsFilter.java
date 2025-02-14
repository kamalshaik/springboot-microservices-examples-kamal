package com.skh.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
/**
 * This Class is created for to overcome the CORS issue.
 * After token generation if we call any controller we are getting CORS issue.
 * To overcome below error:
 * Response to "preflight" request doesn't pass access control check: It does not have HTTP ok status
 
 Complete Error at browser side: 
 	Access to XMLHttpRequest at 'http://localhost:3000/users/'
    from origin 'http://localhost:4200' has been blocked by CORS policy:
    Response to preflight request doesn't pass access control check: It does not have HTTP ok status.
	:3000/users/:1 Failed to load resource: net::ERR_FAILED
	core.js:6228 ERROR Unknown Error
 If we don't use this class and if we not configure this class in WebSecurityConfig.java class
 we will get above mentioned errors.
 
 * 
 * @author Hp
 *
 *Reference URL:
 https://stackoverflow.com/questions/36809528/spring-boot-cors-filter-cors-preflight-channel-did-not-succeed
 *
 *
 *
 */
@Component
public class CorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    	System.out.println("CorsFilter ---> doFilterInternal()");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");
        response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else { 
            filterChain.doFilter(request, response);
        }
    }
}