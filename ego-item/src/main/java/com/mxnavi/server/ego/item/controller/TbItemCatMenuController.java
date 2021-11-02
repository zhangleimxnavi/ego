/**
 * 
 */
package com.mxnavi.server.ego.item.controller;

import javax.annotation.Resource;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxnavi.server.ego.item.pojo.TbItemCatMenuResult;
import com.mxnavi.server.ego.item.service.TbItemCatMenuService;

/**
 * @author zhangleic
 *
 */
@Controller
public class TbItemCatMenuController {
	
	@Resource
	private TbItemCatMenuService tbItemCatMenuServiceImpl;
	
/**
 * 客户端是  jsonp请求，服务端需要 以 jsonp的方式返回
 * 使用jsonp时，要求服务器端返回的数据格式
 * 	函数名(返回的数据)：即伪装成了脚本的形式
	 * 函数名：函数名是什么 服务器端返回后就自动回调 客户端同名的 函数
	 * 返回的数据：把结果返回给上诉的函数
* @Title: listAll
* @param @return    参数
* @return MappingJacksonValue    返回类型
* @throws
 */
	@RequestMapping("/rest/itemcat/all")
	@ResponseBody
	public MappingJacksonValue listAll(){
		TbItemCatMenuResult result = tbItemCatMenuServiceImpl.listAll();
		
		MappingJacksonValue mjv = new MappingJacksonValue(result);
		mjv.setJsonpFunction("category.getDataService");
		
		return mjv;
		
		
	}
	
	

}
