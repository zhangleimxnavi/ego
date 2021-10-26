/**
 * 
 */
package com.mxnavi.server.ego.manage.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.commons.pojo.EgoResult;
import com.mxnavi.server.ego.manage.service.TbContentService;
import com.mxnavi.server.ego.pojo.TbContent;

/**
 * @author zhangleic
 *
 */
@Controller
public class TbContentController {
	
	@Resource
	private TbContentService tbContentServiceImpl;
	
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUiDataGrid listByCategory(long categoryId,int page,int rows){
		
		return tbContentServiceImpl.listByCategory(categoryId, page, rows);
		
	}
	
	
	@RequestMapping("/content/save")
	@ResponseBody
	public EgoResult insTbContent(TbContent tbContent){
		EgoResult result = new EgoResult();
		
		Date date = new Date();
		tbContent.setUpdated(date);
		tbContent.setCreated(date);
		
		int index = tbContentServiceImpl.insTbContent(tbContent);
		if(index > 0){
			result.setStatus("200");
		}else{
			result.setData("ĞÂÔöÊ§°Ü");
		}
		
		return result;
			
	}
	
	
	
	
}
