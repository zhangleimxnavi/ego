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
	* @param @return    ����
	* @return TbItemDesc    ��������
	* @throws
	*/
	TbItemDesc selByItemId(long itemId);

}
