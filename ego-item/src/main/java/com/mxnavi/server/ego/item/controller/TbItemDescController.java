/**
 * 
 */
package com.mxnavi.server.ego.item.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxnavi.server.ego.item.service.TbItemDescService;

/**
 * @author zhangleic
 *
 */
@Controller
public class TbItemDescController {
	
	@Resource
	private TbItemDescService tbItemDescServiceImpl;
	
	@RequestMapping(value="/item/desc/{id}.html",produces="text/html;charset=utf-8")
	@ResponseBody
	public String itemList(@PathVariable("id") long id){
		
		System.out.println("tbItemDescServiceImpl: " + tbItemDescServiceImpl);
		
		System.out.println("hello " + tbItemDescServiceImpl.selItemDescById(id));
		return tbItemDescServiceImpl.selItemDescById(id).getItemDesc();
	}

}
