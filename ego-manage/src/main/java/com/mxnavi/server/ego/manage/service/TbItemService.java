package com.mxnavi.server.ego.manage.service;

import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.pojo.TbItem;

public interface TbItemService {
	
	EasyUiDataGrid selectItemPage(int page,int rows);
	
	int updateStatusByPrimarykey(String ids,byte status);
	
}
