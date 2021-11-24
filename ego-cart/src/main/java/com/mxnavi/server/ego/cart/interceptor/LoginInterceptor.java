/**
 * 
 */
package com.mxnavi.server.ego.cart.interceptor;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mxnavi.server.ego.commons.pojo.EgoResult;
import com.mxnavi.server.ego.commons.util.CookieUtils;
import com.mxnavi.server.ego.commons.util.HttpClientUtil;
import com.mxnavi.server.ego.commons.util.JsonUtils;

/**
 * @author zhangleic
 *
 */
public class LoginInterceptor implements HandlerInterceptor{

	
	
	
	
	/* (·Ç Javadoc)
	* <p>Description: </p>
	* @param arg0
	* @param arg1
	* @param arg2
	* @return
	* @throws Exception
	* @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
	

		

/*		System.out.println(request.getRequestURL());*/
		String cookieValue = CookieUtils.getCookieValue(request, "TT_TOKEN");
		
		if(cookieValue!=null && !cookieValue.equals("")){
			String responseJson = HttpClientUtil.doPost("http://localhost:8084/user/token/"+cookieValue);
			EgoResult result = JsonUtils.jsonToPojo(responseJson, EgoResult.class);
			
			if(result.getStatus().equals("200")){
				System.out.println( "it is ture" );
				return true;
			}
			
		}
		
		response.sendRedirect("http://localhost:8084/user/showLogin?otherUrl="+request.getRequestURL()+"?num="+request.getParameter("num"));
		return false;
	}

	
	
	
	
	/* (·Ç Javadoc)
	* <p>Description: </p>
	* @param arg0
	* @param arg1
	* @param arg2
	* @param arg3
	* @throws Exception
	* @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	*/
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (·Ç Javadoc)
	* <p>Description: </p>
	* @param arg0
	* @param arg1
	* @param arg2
	* @param arg3
	* @throws Exception
	* @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	*/
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}


}
