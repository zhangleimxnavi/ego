/**
 * 
 */
package com.mxnavi.server.ego.dubbo.service.impl;

import javax.annotation.Resource;

import com.mxnavi.server.ego.dubbo.service.TbItemDescDubboService;
import com.mxnavi.server.ego.mapper.TbItemDescMapper;
import com.mxnavi.server.ego.pojo.TbItemDesc;

/**
 * @author zhangleic
 *
 */
public class TbItemDescDubboServiceImpl implements TbItemDescDubboService{

	@Resource
	private TbItemDescMapper tbItemDescMapper;
	
	/* (·Ç Javadoc)
	* <p>Description: </p>
	* @return
	* @see com.mxnavi.server.ego.dubbo.service.TbItemDescDubboService#selByItemId()
	*/
	@Override
	public TbItemDesc selByItemId(long itemId) {
		// TODO Auto-generated method stub
		return tbItemDescMapper.selectByPrimaryKey(itemId);
	}

}
