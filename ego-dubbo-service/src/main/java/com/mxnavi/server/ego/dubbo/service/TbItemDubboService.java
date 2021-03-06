package com.mxnavi.server.ego.dubbo.service;

import java.util.List;

import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.pojo.TbItem;
import com.mxnavi.server.ego.pojo.TbItemDesc;
import com.mxnavi.server.ego.pojo.TbItemParamItem;

public interface TbItemDubboService {
	
	EasyUiDataGrid selectItemPage(int page,int rows);
	
	int updateStatusByPrimarykey(TbItem tbItem);
	

	/**
	 * @throws Exception 
	* @Title: insTbItem
	* @param @param tbItem
	* @param @param tbItemDesc
	* @param @return    参数
	* @return int    返回类型
	* @throws
	*/
	int insTbItem(TbItem tbItem, TbItemDesc tbItemDesc,TbItemParamItem tbItemParamItem) throws Exception;
	

	List<TbItem> listAll();
	
	TbItem selById(long id);
}
