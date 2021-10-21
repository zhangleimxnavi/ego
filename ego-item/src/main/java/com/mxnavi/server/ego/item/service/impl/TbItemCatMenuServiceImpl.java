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
	
	/* (�� Javadoc)
	* <p>Description: </p>
	* @return
	* @see com.mxnavi.server.ego.item.service.TbItemCatMenuService#listAll()
	*/
	@Override
	public TbItemCatMenuResult listAll() {
		//���ؽ��
		TbItemCatMenuResult result = new TbItemCatMenuResult();
		//ȡ��pidΪ0��������Ŀ����ֵ������ �ݹ麯�������ջ�ȡ���������  ���ظ��ͻ��˵� һ����� List
		List<TbItemCat> lists = tbItemCatDubboServiceImpl.selTbItemCatByPid(0);
		result.setData(parseItemCatToNodes(lists));
	
		return result;
	}
	
	
	/**
	 * �ݹ麯��������һ�� ��Ʒ��Ŀ�� list���ݹ��ÿ����Ŀ�µ����е�����Ŀ����ת���ɿͻ�����Ҫ�ĸ�ʽ
	 * category.getDataService
			({"data":[
			{
			"u":"/products/1.html",
			"n":"<a href='/products/1.html'>ͼ�顢���񡢵����鿯</a>",
			"i":[
			
				{"u":"/products/2.html",
				"n":"�����鿯",
				"i":[
				"/products/3.html|������",
				"/products/4.html|����ԭ��",
				"/products/5.html|������־",
				"/products/6.html|��ý��ͼ��"
				]
			},
			
			{"u":"/products/7.html","n":"����","i":["/products/8.html|����","/products/9.html|Ӱ��","/products/10.html|��������"]},
			{"u":"/products/65.html","n":"��̨ͼ��","i":["/products/66.html|����/���/�ղ�","/products/67.html|���ù���","/products/68.html|�Ļ�/ѧ��","/products/69.html|�ٶ���ѧ/��ѧ"]},
			{"u":"/products/70.html","n":"����","i":["/products/71.html|������","/products/72.html|Ӱӡ��","/products/73.html|��װ��"]}
			....................
			....................
			...........��� {u n i} �ṹ
			]
			
			},
			....................
			....................
			....................
			...........��� {u n i} �ṹ
			]
			
	* @Title: parseItemCatToNodes
	* @param @param lists
	* @param @return    ����
	* @return List<Object>    ��������
	* @throws
	 */
	public List <Object> parseItemCatToNodes(List <TbItemCat> lists){
		//���մ�Ž����list
		List<Object> listMenus = new ArrayList<Object>();
		
		for (TbItemCat tbItemCat : lists) {
			//�ӵ�һ�㸸��Ŀ��ʼ��������ÿһ����Ŀ�������ݿͻ���Ҫ��ĸ�ʽ�����ݷ�װ�� TbItemCatMenuNodeʵ������
			if(tbItemCat.getIsParent()){
				TbItemCatMenuNode node = new TbItemCatMenuNode();
				node.setU("/products/" + tbItemCat.getId() + ".html");
				node.setN("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
				node.setI(this.parseItemCatToNodes(tbItemCatDubboServiceImpl.selTbItemCatByPid(tbItemCat.getId())));
				
				listMenus.add(node);
			}else{
			// ������Ǹ���Ŀ��ֱ�Ӱѿͻ���Ҫ����ַ��� ��ӵ� ���ڽ����list��
				listMenus.add("/products/" + tbItemCat.getId() + ".html" +"|" + tbItemCat.getName());
			}
		}
		return listMenus;
	}
	
	

}
