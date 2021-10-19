/**
 * 
 */
package com.mxnavi.server.ego.manage.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.commons.pojo.EgoResult;
import com.mxnavi.server.ego.manage.service.TbItemParamService;
import com.mxnavi.server.ego.pojo.TbItemParam;

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
	
	
	
	@RequestMapping("/item/param/query/itemcatid/{cid}")
	@ResponseBody
	public EgoResult selByCid(@PathVariable("cid") long cid){
		
		EgoResult result = new EgoResult();
		List<TbItemParam> lists = tbItemParamServiceImpl.selByCid(cid);
		if(lists != null && lists.size() > 0){
			result.setStatus("200");
			result.setData(lists.get(0));
		}
		
		return result;
	}
	
	
	@RequestMapping("/item/param/save/{cid}")
	@ResponseBody
	public EgoResult save(@PathVariable("cid") long cid,String paramData){
		
		EgoResult result = new EgoResult();
		
		TbItemParam tbItemParam = new TbItemParam();
		Date data = new Date();
		
		tbItemParam.setCreated(data);
		tbItemParam.setUpdated(data);
		tbItemParam.setItemCatId(cid);
		tbItemParam.setParamData(paramData);
		
		int index = tbItemParamServiceImpl.insTbItemParam(tbItemParam);
		if(index == 1){
			result.setStatus("200");
		}
		
		return result;
	}
	
	
	
	
	
}
