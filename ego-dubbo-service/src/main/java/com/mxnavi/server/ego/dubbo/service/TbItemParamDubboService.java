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
	* @param @return    ����
	* @return EasyUiDataGrid    ��������
	* @throws
	*/
	EasyUiDataGrid listAll(int page, int rows);
	
	
	int delByIds(String [] ids) throws Exception;
	
	
	
	
}