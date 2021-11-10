/**
 * 
 */
package com.mxnavi.server.ego.dubbo.service;

import com.mxnavi.server.ego.pojo.TbItemDesc;

/**
 * @author zhangleic
 *
 */
public interface TbItemDescDubboService {
	

	/**
	* @Title: selByItemId
	* @param @param itemId
	* @param @return    参数
	* @return TbItemDesc    返回类型
	* @throws
	*/
	TbItemDesc selByItemId(long itemId);

}
