/**
 * 
 */
package com.mxnavi.server.ego.dubbo.service;

import java.util.List;

import com.mxnavi.server.ego.pojo.TbItemCat;

/**
 * @author zhangleic
 *
 */
public interface TbItemCatDubboService {
	
	List<TbItemCat> selTbItemCatByPid(long pid);
	
	TbItemCat selTbItemCatById(long id);

}
