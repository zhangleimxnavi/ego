package com.mxnavi.server.ego.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.manage.service.impl.TbItemServiceImpl;

@Controller
public class TbItemController {
	
	@Resource
	private TbItemServiceImpl tbItemServiceImpl;
	
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUiDataGrid selectItemPage(int page, int rows) {
		// TODO Auto-generated method stub
		return tbItemServiceImpl.selectItemPage(page, rows);
	}
	

}
