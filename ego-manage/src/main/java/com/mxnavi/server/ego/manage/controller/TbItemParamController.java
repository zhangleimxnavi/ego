/**
 * 
 */
package com.mxnavi.server.ego.manage.controller;

import java.util.List;

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
	
	
	@RequestMapping("/item/param/delete")
	@ResponseBody
	public EgoResult delByIds(String ids){
		
		EgoResult result = new EgoResult();
		String[] idsChar = ids.split(",");
		
		try {
			int index = tbItemParamServiceImpl.delByIds(idsChar);
			if(index == 1){
				result.setStatus("200");
			}
		} catch (Exception e) {
			result.setData(e.getMessage());
/*			e.printStackTrace();*/
		}
		
		return result;
	}
	
	
	
}
