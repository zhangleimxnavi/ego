/**
 * 
 */
package com.mxnavi.server.ego.manage.service;

import java.util.List;

import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;

/**
 * @author zhangleic
 *
 */
public interface TbItemParamService {
	
	EasyUiDataGrid listAll(int page, int rows);
	
	int delByIds(String [] ids) throws Exception;
	
	
}
