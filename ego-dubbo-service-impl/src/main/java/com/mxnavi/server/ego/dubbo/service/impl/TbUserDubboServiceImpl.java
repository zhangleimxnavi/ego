/**
 * 
 */
package com.mxnavi.server.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mxnavi.server.ego.dubbo.service.TbUserDubboService;
import com.mxnavi.server.ego.mapper.TbUserMapper;
import com.mxnavi.server.ego.pojo.TbUser;
import com.mxnavi.server.ego.pojo.TbUserExample;

/**
 * @author zhangleic
 *
 */


public class TbUserDubboServiceImpl implements TbUserDubboService{
	
	@Resource
	private TbUserMapper tbUserMapper;

	/* (·Ç Javadoc)
	* <p>Description: </p>
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbUserDubboService#selByUserNameAndPassword()
	*/
	@Override
	public TbUser selByUserNameAndPassword(TbUser user) {
		TbUserExample example = new TbUserExample();
		example.createCriteria().andUsernameEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
		List<TbUser> list = tbUserMapper.selectByExample(example);
		if(list!=null&& list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
