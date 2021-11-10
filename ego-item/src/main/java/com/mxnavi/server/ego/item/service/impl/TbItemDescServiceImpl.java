/**
 * 
 */
package com.mxnavi.server.ego.item.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mxnavi.server.ego.dubbo.service.TbItemDescDubboService;
import com.mxnavi.server.ego.item.service.TbItemDescService;
import com.mxnavi.server.ego.pojo.TbItemDesc;

/**
 * @author zhangleic
 *
 */
@Service
public class TbItemDescServiceImpl implements TbItemDescService{

	@Reference
	private TbItemDescDubboService tbItemDescDubboServiceImpl;
	/* (·Ç Javadoc)
	* <p>Description: </p>
	* @param id
	* @return
	* @see com.mxnavi.server.ego.item.service.TbItemDescService#selItemDescById(long)
	*/
	@Override
	public TbItemDesc selItemDescById(long id) {
		
		return tbItemDescDubboServiceImpl.selByItemId(id);
	}

}
