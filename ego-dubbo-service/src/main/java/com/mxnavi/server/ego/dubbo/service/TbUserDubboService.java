/**
 * 
 */
package com.mxnavi.server.ego.dubbo.service;

import com.mxnavi.server.ego.pojo.TbUser;

/**
 * @author zhangleic
 *
 */
public interface TbUserDubboService {
	
	TbUser selByUserNameAndPassword(TbUser user);
}
