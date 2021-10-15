/**
 * 
 */
package com.mxnavi.server.ego.manage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mxnavi.server.ego.dubbo.service.TbItemCatDubboService;
import com.mxnavi.server.ego.manage.service.TbItemCatService;
import com.mxnavi.server.ego.pojo.TbItemCat;

/**
 * @author zhangleic
 *
 */
@Service
public class TbItemCatServiceImpl implements TbItemCatService{
	
	@Reference
	private TbItemCatDubboService tbItemCatDubboService;

	/* (·Ç Javadoc)
	* <p>Description: </p>
	* @param pid
	* @return
	* @see com.mxnavi.server.ego.manage.service.TbItemCatService#selTbItemCatByPid(long)
	*/
	@Override
	public List<TbItemCat> selTbItemCatByPid(long pid) {
		// TODO Auto-generated method stub
		return tbItemCatDubboService.selTbItemCatByPid(pid);
	}
	

}
