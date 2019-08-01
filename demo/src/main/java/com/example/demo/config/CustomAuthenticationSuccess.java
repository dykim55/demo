package com.example.demo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccess implements AuthenticationSuccessHandler {
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	  
	@Override
	public void onAuthenticationSuccess(
				HttpServletRequest request, 
				HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
		  
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  
		HttpSession session = request.getSession();
		session.setAttribute("uname", user.getUsername());
		session.setAttribute("authorities", authentication.getAuthorities());
		  
		redirectStrategy.sendRedirect(request, response, "/main");
	}
}
