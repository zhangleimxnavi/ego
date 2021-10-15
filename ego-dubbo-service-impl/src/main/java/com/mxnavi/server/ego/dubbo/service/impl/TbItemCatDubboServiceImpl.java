/**
 * 
 */
package com.mxnavi.server.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.mxnavi.server.ego.dubbo.service.TbItemCatDubboService;
import com.mxnavi.server.ego.mapper.TbItemCatMapper;
import com.mxnavi.server.ego.pojo.TbItemCat;
import com.mxnavi.server.ego.pojo.TbItemCatExample;

/**
 * @author zhangleic
 * ��Ʒ��� dubbo ʵ����
 *
 */


public class TbItemCatDubboServiceImpl implements TbItemCatDubboService{
	
	@Resource
	private TbItemCatMapper tbItemCatMapper;
	

	/* (�� Javadoc)
	* <p>Description: </p> select TbItemCat by pid
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbItemCatDubboService#selTbItemCatById()
	*/
	@Override
	public List<TbItemCat> selTbItemCatByPid(long pid) {
		// TODO Auto-generated method stub
		TbItemCatExample tbItemCatExample = new TbItemCatExample();
		tbItemCatExample.createCriteria().andParentIdEqualTo(pid);
		List<TbItemCat> lists =  tbItemCatMapper.selectByExample(tbItemCatExample);
		return lists;
	}
	
	
	
	
	
}
 