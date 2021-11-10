/**
 * 
 */
package com.mxnavi.server.ego.item.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mxnavi.server.ego.item.service.TbItemService;
import com.mxnavi.server.ego.pojo.TbItem;

/**
 * @author zhangleic
 *
 */
@Controller
public class TbItemController {
	
	@Resource
	private TbItemService tbItemServiceImpl;
	
	
	@RequestMapping("/item/{id}.html")
	public String itemList(@PathVariable("id") long id,Model model){
		
		TbItem item = tbItemServiceImpl.selItemById(id);
		Map map = new HashMap<String,Object>();
		map.put("id", item.getId());
		map.put("title", item.getTitle());
		map.put("sellPoint", item.getSellPoint());
		map.put("price", item.getPrice());
		map.put("images", item.getImage().split(",").length > 0?item.getImage().split(","):new String[1]);
		model.addAttribute("item", map);
		return "item";
	}

}
