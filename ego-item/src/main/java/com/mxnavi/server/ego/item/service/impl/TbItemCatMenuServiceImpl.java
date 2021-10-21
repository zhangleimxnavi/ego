/**
 * 
 */
package com.mxnavi.server.ego.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mxnavi.server.ego.dubbo.service.TbItemCatDubboService;
import com.mxnavi.server.ego.item.pojo.TbItemCatMenuNode;
import com.mxnavi.server.ego.item.pojo.TbItemCatMenuResult;
import com.mxnavi.server.ego.item.service.TbItemCatMenuService;
import com.mxnavi.server.ego.pojo.TbItemCat;

/**
 * @author zhangleic
 *
 */
@Service
public class TbItemCatMenuServiceImpl implements TbItemCatMenuService{

	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;
	
	/* (非 Javadoc)
	* <p>Description: </p>
	* @return
	* @see com.mxnavi.server.ego.item.service.TbItemCatMenuService#listAll()
	*/
	@Override
	public TbItemCatMenuResult listAll() {
		//返回结果
		TbItemCatMenuResult result = new TbItemCatMenuResult();
		//取出pid为0的所有类目，传值并调用 递归函数，最终获取到结果就是  返回给客户端的 一个大的 List
		List<TbItemCat> lists = tbItemCatDubboServiceImpl.selTbItemCatByPid(0);
		result.setData(parseItemCatToNodes(lists));
	
		return result;
	}
	
	
	/**
	 * 递归函数，接收一个 商品类目的 list，递归出每个类目下的所有的子类目，并转换成客户端需要的格式
	 * category.getDataService
			({"data":[
			{
			"u":"/products/1.html",
			"n":"<a href='/products/1.html'>图书、音像、电子书刊</a>",
			"i":[
			
				{"u":"/products/2.html",
				"n":"电子书刊",
				"i":[
				"/products/3.html|电子书",
				"/products/4.html|网络原创",
				"/products/5.html|数字杂志",
				"/products/6.html|多媒体图书"
				]
			},
			
			{"u":"/products/7.html","n":"音像","i":["/products/8.html|音乐","/products/9.html|影视","/products/10.html|教育音像"]},
			{"u":"/products/65.html","n":"港台图书","i":["/products/66.html|艺术/设计/收藏","/products/67.html|经济管理","/products/68.html|文化/学术","/products/69.html|少儿文学/国学"]},
			{"u":"/products/70.html","n":"其它","i":["/products/71.html|工具书","/products/72.html|影印版","/products/73.html|套装书"]}
			....................
			....................
			...........多个 {u n i} 结构
			]
			
			},
			....................
			....................
			....................
			...........多个 {u n i} 结构
			]
			
	* @Title: parseItemCatToNodes
	* @param @param lists
	* @param @return    参数
	* @return List<Object>    返回类型
	* @throws
	 */
	public List <Object> parseItemCatToNodes(List <TbItemCat> lists){
		//最终存放结果的list
		List<Object> listMenus = new ArrayList<Object>();
		
		for (TbItemCat tbItemCat : lists) {
			//从第一层父类目开始，遍历出每一层类目，并根据客户端要求的格式把数据封装在 TbItemCatMenuNode实体类中
			if(tbItemCat.getIsParent()){
				TbItemCatMenuNode node = new TbItemCatMenuNode();
				node.setU("/products/" + tbItemCat.getId() + ".html");
				node.setN("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
				node.setI(this.parseItemCatToNodes(tbItemCatDubboServiceImpl.selTbItemCatByPid(tbItemCat.getId())));
				
				listMenus.add(node);
			}else{
			// 如果不是父类目，直接把客户端要求的字符串 添加到 存在结果的list中
				listMenus.add("/products/" + tbItemCat.getId() + ".html" +"|" + tbItemCat.getName());
			}
		}
		return listMenus;
	}
	
	

}
