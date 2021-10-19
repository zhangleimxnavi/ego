/**
 * 
 */
package com.mxnavi.server.ego.dubbo.service;

import java.util.List;

import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;

/**
 * @author zhangleic
 *
 */
public interface TbItemParamDubboService {


	/**
	* @Title: listAll
	* @param @param page
	* @param @param rows
	* @param @return    参数
	* @return EasyUiDataGrid    返回类型
	* @throws
	*/
	EasyUiDataGrid listAll(int page, int rows);
	
	
	int delByIds(String [] ids) throws Exception;
	
	
	
	
}
