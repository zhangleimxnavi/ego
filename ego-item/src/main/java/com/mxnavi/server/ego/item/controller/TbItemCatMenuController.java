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
 * �ͻ�����  jsonp���󣬷������Ҫ �� jsonp�ķ�ʽ����
 * ʹ��jsonpʱ��Ҫ��������˷��ص����ݸ�ʽ
 * 	������(���ص�����)����αװ���˽ű�����ʽ
	 * ����������������ʲô �������˷��غ���Զ��ص� �ͻ���ͬ���� ����
	 * ���ص����ݣ��ѽ�����ظ����ߵĺ���
* @Title: listAll
* @param @return    ����
* @return MappingJacksonValue    ��������
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
