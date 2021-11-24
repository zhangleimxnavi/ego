/**
 * 
 */
package com.mxnavi.server.ego.passport.controller;

import javax.annotation.Resource;
import javax.print.attribute.standard.JobOriginatingUserName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxnavi.server.ego.commons.pojo.EgoResult;
import com.mxnavi.server.ego.commons.util.CookieUtils;
import com.mxnavi.server.ego.commons.util.JsonUtils;
import com.mxnavi.server.ego.passport.service.TbUserService;
import com.mxnavi.server.ego.pojo.TbUser;
import com.mxnavi.server.ego.redis.dao.RedisDao;
import com.mxnavi.server.ego.redis.dao.impl.RedisDaoImpl;

/**
 * @author zhangleic
 *
 */

@Controller
public class LoginController {
	
	
	
	@Resource
	private TbUserService tbUserServiceImpl;
	
	@Resource
	private RedisDao redisDaoImpl;
	
	@RequestMapping("/user/showLogin")
	public String showLogin(@RequestHeader("Referer") String RefererUrl,String otherUrl,Model model){

		System.out.println("redirect "+ RefererUrl);
		System.out.println("redirectUrl: "+ otherUrl);
		if(otherUrl!=null &&!otherUrl.equals("")){
			model.addAttribute("redirect", otherUrl);
		}else{
			model.addAttribute("redirect", RefererUrl);
		}
		
		
		return "login";
	}

	
	
	
	@RequestMapping("/user/login")
	@ResponseBody
	public EgoResult login(String username,String password,HttpServletRequest request,HttpServletResponse response){
		
		EgoResult result = new EgoResult();
		
		TbUser user = new TbUser();
		user.setUsername(username);
		user.setPassword(password);
		
		TbUser userSelected = tbUserServiceImpl.selByUserNameAndPassword(user);
		if(userSelected != null){
			result.setStatus("200");
			//存 redis
			String key = userSelected.getId().toString();
			redisDaoImpl.set(key, JsonUtils.objectToJson(userSelected));
			redisDaoImpl.expire(key, 60*60*24*7);
			//产生cookie
			CookieUtils.setCookie(request, response,"TT_TOKEN", key, 60*60*24*7);
			
			
		}else{
			result.setData("账户密码有误");
		}
		
		return result;
		
		
	}

	
	@RequestMapping("/user/token/{cookieValue}")
	@ResponseBody
	public Object userToken(@PathVariable String cookieValue,String callback){
		
		EgoResult result = tbUserServiceImpl.getInfoByCookie(cookieValue);
		
		if(callback != null && !callback.equals("")){
		//只是返回了 jsonp格式，其实还是  EgoResult
				MappingJacksonValue mjv = new MappingJacksonValue(result);
				mjv.setJsonpFunction(callback);
				return mjv;
		}
		//返回  正常的 EgoResult
		return result;


	}
	
	
	
	
	
	@RequestMapping("/user/logout/{cookieValue}")
	@ResponseBody
	public Object userLogout(@PathVariable String cookieValue,String callback,HttpServletRequest request,HttpServletResponse response){
		
		EgoResult result = tbUserServiceImpl.logout(cookieValue, request, response);
		
		if(callback != null && !callback.equals("")){
		//只是返回了 jsonp格式，其实还是  EgoResult
				MappingJacksonValue mjv = new MappingJacksonValue(result);
				mjv.setJsonpFunction(callback);
				return mjv;
		}
		//返回  正常的 EgoResult
		return result;


	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
