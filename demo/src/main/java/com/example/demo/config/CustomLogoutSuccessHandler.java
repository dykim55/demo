package com.example.demo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler  {

	@Override
	public void onLogoutSuccess(
			HttpServletRequest request,
			HttpServletResponse response, 
			Authentication authentication) throws IOException, ServletException {
		
		if (null != authentication && null != authentication.getDetails()) {
			try {
				request.getSession().invalidate();
			} catch (Exception e) {
			}
			setDefaultTargetUrl("/login?logout");
			super.onLogoutSuccess(request, response, authentication);
		}
	}

}