package com.mxnavi.server.ego.manage.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.dubbo.service.TbItemDubboService;
import com.mxnavi.server.ego.manage.service.TbItemService;


@Service
public class TbItemServiceImpl implements TbItemService{

	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;
	
	@Override
	public EasyUiDataGrid selectItemPage(int page, int rows) {
		// TODO Auto-generated method stub
		return tbItemDubboServiceImpl.selectItemPage(page, rows);
	}

	
	
}
