/**
 * 
 */
package com.mxnavi.server.ego.search.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.Module;
import com.mxnavi.server.ego.commons.util.JsonUtils;
import com.mxnavi.server.ego.pojo.TbItem;
import com.mxnavi.server.ego.search.service.TbItemService;
import com.mxnavi.server.ego.search.service.impl.TbItemServiceImpl;

/**
 * @author zhangleic
 *
 */

@Controller
public class ItemSolr {
	
	@Resource
	private TbItemService tbItemServiceImpl;
	
	
	
	@RequestMapping(value="/search/solr/init",produces="text/html;charset=utf-8")
	@ResponseBody()
	public String solrInit(){
		
		long timeStart;
		long timeEnd;
		
		try {
			timeStart = System.currentTimeMillis();
			tbItemServiceImpl.initItemSolr();
			timeEnd = System.currentTimeMillis();
		} catch (Exception e) {
			e.printStackTrace();
			return "solr 初始化失败";
		}
		
		return "solr 初始化 成功! 耗时为： " + (timeEnd - timeStart)/1000;
	}
	
	
	

	@RequestMapping("/search.html")
	public String itemSearch(String q,Model model,@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="5") int rows){
		
		Map map = null;
		try {
			String queryParam = new String(q.getBytes("iso-8859-1"), "utf-8");
			map = tbItemServiceImpl.itemSearch(queryParam,page,rows);
			
			model.addAttribute("query", queryParam);
			model.addAttribute("page", page);
			model.addAttribute("itemList", map.get("itemList"));
			model.addAttribute("totalPages", map.get("map"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*
		System.out.println("这是一个MAP：   " + map.toString());*/
		return "search";
	}
	
	
	/**
	 * 
       @RequestBody 接收 json格式的参数 数据流，并转换成对象
	 */
	@RequestMapping(value="/add/solrItem",produces="text/html;charset=utf-8")
	@ResponseBody
	public String addSolrItem(@RequestBody Map map){
		
/*		Map paramMap = JsonUtils.jsonToPojo(item, Map.class);
		*/

		String desc = map.get("desc").toString();
		LinkedHashMap tbItemMap =  (LinkedHashMap) map.get("tbItem");
/*		System.out.println("desc: "+ desc);
		System.out.println("tbItemMap : "+ tbItemMap);*/
		
		try {
			int index = tbItemServiceImpl.addSolrItem(desc, tbItemMap);
			if(index == 1){
				return "同步成功";
			}else{
				return "同步失败";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "同步失败，出现异常";
		}
		
		
	}
	
	
	

}
