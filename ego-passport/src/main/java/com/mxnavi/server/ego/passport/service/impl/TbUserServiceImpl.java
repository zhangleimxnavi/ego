/**
 * 
 */
package com.mxnavi.server.ego.passport.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mxnavi.server.ego.commons.pojo.EgoResult;
import com.mxnavi.server.ego.commons.util.CookieUtils;
import com.mxnavi.server.ego.commons.util.JsonUtils;
import com.mxnavi.server.ego.dubbo.service.TbUserDubboService;
import com.mxnavi.server.ego.passport.service.TbUserService;
import com.mxnavi.server.ego.pojo.TbUser;
import com.mxnavi.server.ego.pojo.TbUserExample;
import com.mxnavi.server.ego.redis.dao.RedisDao;

/**
 * @author zhangleic
 *
 */
@Service
public class TbUserServiceImpl implements TbUserService{

	@Resource
	private RedisDao redisDaoImpl;
	
	@Reference
	private TbUserDubboService tbUserDubboServiceImpl;
	/* (非 Javadoc)
	* <p>Description: </p>
	* @param user
	* @return
	* @see com.mxnavi.server.ego.passport.service.TbUserService#selByUserNameAndPassword(com.mxnavi.server.ego.pojo.TbUser)
	*/
	@Override
	public TbUser selByUserNameAndPassword(TbUser user) {
		TbUser tbUser = tbUserDubboServiceImpl.selByUserNameAndPassword(user);

		return tbUser;
			
	}
	/* (非 Javadoc)
	* <p>Description: </p>
	* @param cookie
	* @return
	* @see com.mxnavi.server.ego.passport.service.TbUserService#getInfoByCookie(java.lang.String)
	*/
	@Override
	public EgoResult getInfoByCookie(String cookie) {
		EgoResult result = new EgoResult();
		String json = redisDaoImpl.get(cookie);
		if(json != null && !json.equals("")){
			result.setStatus("200");
			TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
			user.setPassword(null);
			result.setData(user);
		}else{
			result.setStatus("500");
			result.setData("没有对应信息");
		}
		return result;
	}
	/* (非 Javadoc)
	* <p>Description: </p>
	* @param cookie
	* @return
	* @see com.mxnavi.server.ego.passport.service.TbUserService#logout(java.lang.String)
	*/
	@Override
	public EgoResult logout(String cookie,HttpServletRequest request,HttpServletResponse response) {
		EgoResult result = new EgoResult();

		if(cookie != null && !cookie.equals("")){
			redisDaoImpl.del(cookie);
			CookieUtils.deleteCookie(request, response, "TT_TOKEN");
			
			result.setStatus("200");

		}else{
			result.setData("退出失败");
		}
		return result;
	}

}
