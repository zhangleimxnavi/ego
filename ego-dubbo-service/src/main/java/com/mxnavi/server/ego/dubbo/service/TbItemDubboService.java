package com.mxnavi.server.ego.dubbo.service;

import java.util.List;

import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.pojo.TbItem;

public interface TbItemDubboService {
	
	EasyUiDataGrid selectItemPage(int page,int rows);

}
