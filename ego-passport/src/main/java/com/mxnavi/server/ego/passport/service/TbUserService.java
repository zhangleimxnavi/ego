/**
 * 
 */
package com.mxnavi.server.ego.passport.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mxnavi.server.ego.commons.pojo.EgoResult;
import com.mxnavi.server.ego.pojo.TbUser;

/**
 * @author zhangleic
 *
 */
public interface TbUserService {
	
	TbUser selByUserNameAndPassword(TbUser user);
	
	EgoResult getInfoByCookie(String cookie);
	
	EgoResult logout(String cookie,HttpServletRequest request,HttpServletResponse response);
}
