/**
 * 
 */
package com.mxnavi.server.ego.manage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxnavi.server.ego.commons.pojo.EasyUiTree;
import com.mxnavi.server.ego.manage.service.TbItemCatService;
import com.mxnavi.server.ego.pojo.TbItemCat;

/**
 * @author zhangleic
 *
 */

@Controller
public class TbItemCatController {
	
	@Resource
	private TbItemCatService tbItemCatServiceImpl;
	
	
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUiTree> showTbItemCat(@RequestParam(value="id",defaultValue="0") long pid){
		
		List<EasyUiTree> treeLists = new ArrayList<EasyUiTree>();
		
		List<TbItemCat> lists = tbItemCatServiceImpl.selTbItemCatByPid(pid);
		for (TbItemCat tbItemCat : lists) {
			EasyUiTree easyUiTree = new EasyUiTree();
			easyUiTree.setId(tbItemCat.getId());
			easyUiTree.setText(tbItemCat.getName());
			
			String state = tbItemCat.getIsParent()?"closed":"open";
			easyUiTree.setState(state);
			treeLists.add(easyUiTree);
		}
		
		
		return treeLists;
		
	}
	
}
