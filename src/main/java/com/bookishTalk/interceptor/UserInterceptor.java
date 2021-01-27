package com.bookishTalk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserInterceptor extends HandlerInterceptorAdapter {
		
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		    throws Exception {
			
			HttpSession session= request.getSession();
			if(session.getAttribute("userDetail")==null||session.getAttribute("userDetail").equals("")) {
				System.out.println("inside pre");
				session.setAttribute("loogedIn", false);
				
			}
			else{session.setAttribute("loogedIn", true);}	
			return true;
		}

		@Override
		public void postHandle(
				HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
				throws Exception {
			System.out.println("inside post");
		}

		@Override
		public void afterCompletion(
				HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
				throws Exception {
			HttpSession session= request.getSession();
			session.removeAttribute("loogedIn");
			System.out.println("inside completion");
		}
}
