package com.zhiyou100.video.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CustomInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getSession().getAttribute("_front_user") != null){
			if(request.getSession().getAttribute("adminName") != null){
				return true;
			}else{
				if(request.getServletPath().startsWith("/front/user")){
					return true;
				}else{
					return false;
				}				
			}			
		}else{
			if(request.getSession().getAttribute("adminName") != null){
				if(request.getServletPath().startsWith("/admin")){
					return true;
				}else{
					return false;
				}
			}else{				
				return false;
			}			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*if(request.getSession().getAttribute("_front_user")!=null){
			if(request.getSession().getAttribute("adminName")!=null){
				return true;
			}else{
				if(request.getServletPath().endsWith(".do")){
					return true;
				}else{
					return false;
				}
			}
		}else{
			if(request.getSession().getAttribute("adminName")!=null){
				if(request.getServletPath().startsWith("/front/user")){
					return false;
				}else{
					return true;
				}
			}else{
				if(request.getServletPath().startsWith("/front/video")||request.getServletPath().startsWith("/front/course")){
					return true;
				}else{
					return false;
				}
			}

		}*/
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
