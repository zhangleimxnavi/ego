/**
 * 
 */
package com.mxnavi.server.ego.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.commons.pojo.EgoResult;
import com.mxnavi.server.ego.manage.service.TbItemParamService;

/**
 * @author zhangleic
 *
 */
@Controller
public class TbItemParamController {
	
	@Resource
	private TbItemParamService tbItemParamServiceImpl;
	
	@RequestMapping("/item/param/list")
	@ResponseBody
	public EasyUiDataGrid listAll(int page, int rows){
		return tbItemParamServiceImpl.listAll(page, rows);
	}
	
}
