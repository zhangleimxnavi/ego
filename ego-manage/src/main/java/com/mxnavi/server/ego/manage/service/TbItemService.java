package com.mxnavi.server.ego.manage.service;

import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;

public interface TbItemService {
	
	EasyUiDataGrid selectItemPage(int page,int rows);
	
}
