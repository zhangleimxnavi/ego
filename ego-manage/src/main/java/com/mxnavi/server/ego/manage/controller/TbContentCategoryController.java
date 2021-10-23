/**
 * 
 */
package com.mxnavi.server.ego.manage.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxnavi.server.ego.commons.pojo.EasyUiTree;
import com.mxnavi.server.ego.commons.pojo.EgoResult;
import com.mxnavi.server.ego.commons.util.IDUtils;
import com.mxnavi.server.ego.manage.service.TbContentCategoryService;
import com.mxnavi.server.ego.pojo.TbContentCategory;
	


/**
 * @author zhangleic
 *
 */
@Controller
public class TbContentCategoryController {
		
	@Resource
	private TbContentCategoryService tbContentCategoryServiceImpl;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUiTree> selByPid(@RequestParam(defaultValue="0") long id){
		
		List<EasyUiTree> treeList = new ArrayList<EasyUiTree>();
		
		List<TbContentCategory> list = tbContentCategoryServiceImpl.selByPid(id);
		for (TbContentCategory tbContentCategory : list) {
			EasyUiTree easyUiTree = new EasyUiTree();
			easyUiTree.setId(tbContentCategory.getId());
			easyUiTree.setText(tbContentCategory.getName());
			easyUiTree.setState(tbContentCategory.getIsParent()?"closed":"open");
			treeList.add(easyUiTree);
		}
		
		return treeList;
	}
	
	
	@RequestMapping("/content/category/create")
	@ResponseBody
	public EgoResult insertTbContentCategory(TbContentCategory tbContentCategory){
		
		EgoResult result = new EgoResult();
		
		Date date = new Date();
		
		tbContentCategory.setId(IDUtils.genItemId());
		tbContentCategory.setStatus(1);
		tbContentCategory.setSortOrder(1);
		tbContentCategory.setIsParent(false);
		tbContentCategory.setCreated(date);
		tbContentCategory.setUpdated(date);
		
		
		
		try {
			int index = tbContentCategoryServiceImpl.insertTbContentCategory(tbContentCategory);
			if(index == 1){
				result.setStatus("200");
				Map map = new HashMap<>();
				map.put("id", tbContentCategory.getId());
				result.setData(map);
			}
		} catch (Exception e) {
			result.setData(e.getMessage());
/*			e.printStackTrace();*/
		}
		
		return result;
		
	}
	
	
	@RequestMapping("/content/category/update")
	@ResponseBody
	public EgoResult updTbContentCategory(TbContentCategory tbContentCategory){
		EgoResult result = new EgoResult();
		
		Date date = new Date();
		tbContentCategory.setUpdated(date);
		
		int index = tbContentCategoryServiceImpl.updTbContentCategory(tbContentCategory);
		if(index  == 1){
			result.setStatus("200");
		}else{
			result.setData("ÐÞ¸Ä´íÎó£¬ÓÐÖØÃû");
		}
		
		return result;
	}
	
	
	
	@RequestMapping("/content/category/delete/")
	@ResponseBody
	public EgoResult delByid(long id){
		EgoResult result = new EgoResult();
		
		int index = tbContentCategoryServiceImpl.delByid(id);
		if(index == 1){
			result.setStatus("200");
		}else{
			result.setData("É¾³ý´íÎó");
		}
		
		return result;
	}
	
	
	
}
