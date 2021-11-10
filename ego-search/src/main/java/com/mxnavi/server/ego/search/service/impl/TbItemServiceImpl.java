/**
 * 
 */
package com.mxnavi.server.ego.search.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mxnavi.server.ego.commons.pojo.TbItemChild;
import com.mxnavi.server.ego.dubbo.service.TbContentDubboService;
import com.mxnavi.server.ego.dubbo.service.TbItemCatDubboService;
import com.mxnavi.server.ego.dubbo.service.TbItemDescDubboService;
import com.mxnavi.server.ego.dubbo.service.TbItemDubboService;
import com.mxnavi.server.ego.pojo.TbItem;
import com.mxnavi.server.ego.pojo.TbItemCat;
import com.mxnavi.server.ego.pojo.TbItemDesc;
import com.mxnavi.server.ego.search.service.TbItemService;

/**
 * @author zhangleic
 *
 */
@Service
public class TbItemServiceImpl implements TbItemService{
	
	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;
	
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;

	@Reference
	private TbItemDescDubboService tbItemDescDubboServiceImpl;
	
	@Resource
	private SolrClient httpSolrClient;
	
	/* (�� Javadoc)
	* <p>Description: ��ʼ�� solr </p>
	* @see com.mxnavi.server.ego.search.service.TbItemService#initItemSolr()
	*/
	@Override
	public void initItemSolr() throws SolrServerException, IOException {
		
		List<TbItem> list = tbItemDubboServiceImpl.listAll();
		
/*		String urlString = "http://192.168.3.35:8982/solr/collection1";
		SolrClient client = new HttpSolrClient.Builder(urlString).build();*/
	
/*		<field name="item_title" type="text_ik" indexed="true" stored="true"/>
		<field name="item_sell_point" type="text_ik" indexed="true" stored="true"/>
		<field name="item_price"  type="long" indexed="true" stored="true"/>
		<field name="item_image" type="string" indexed="false" stored="true" />
		<field name="item_category_name" type="string" indexed="true" stored="true" />
		<field name="item_desc" type="text_ik" indexed="true" stored="false" />*/
		
		
		for (TbItem tbItem : list) {
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("id", tbItem.getId());
			doc.addField("item_title", tbItem.getTitle());
			doc.addField("item_sell_point", tbItem.getSellPoint());
			doc.addField("item_price", tbItem.getPrice());		
			doc.addField("item_image", tbItem.getImage());
			
			TbItemCat TbItemCat = tbItemCatDubboServiceImpl.selTbItemCatById(tbItem.getCid());
			doc.addField("item_category_name", TbItemCat.getName());
			
			TbItemDesc tbItemDesc = tbItemDescDubboServiceImpl.selByItemId(tbItem.getId());
			
			doc.addField("item_desc", tbItemDesc.getItemDesc());
			doc.addField("item_created", tbItem.getCreated());
			
			httpSolrClient.add(doc);
			
		}
		
		httpSolrClient.commit();
		
	}
	
	
	

	/* (�� Javadoc)
	* <p>Description: ����</p>
	* @param q
	* @return
	* @see com.mxnavi.server.ego.search.service.TbItemService#itemSearch(java.lang.String)
	*/
	@Override
	public Map itemSearch(String q,int page,int rows) throws SolrServerException, IOException {
		
		Map resultMap = new HashMap<String,Object>();
		
		
		SolrQuery params = new SolrQuery();
		
		params.setQuery("item_keywords:"+q);
		
/*		System.out.println("item_keywords:"+q);*/
		
		params.setSort("item_created", ORDER.desc);
		
		
		//��ҳ
		params.setStart(0);
		params.setRows(5);
		
		
		//����������ѯ
		params.setHighlight(true);
		
		
		//���ø�����field
		params.addHighlightField("item_keywords");
		
		//������ʾ ʱ��� ǰ��׺
		params.setHighlightSimplePre("<span style='color:red'>");
		params.setHighlightSimplePost("</span>");
		
		
		
		//ִ�в�ѯ
		QueryResponse response = httpSolrClient.query(params);
		
/*		System.out.println("response:"+ response);*/
		
		
		//ȡ��������ʾ���ֵ�����
		Map<String, Map<String, List<String>>> highlightingMap = response.getHighlighting();
/*		System.out.println("highlightingMap" + highlightingMap);
		*/
		
		//����Ӧ����У�ȡ��docs{}
		SolrDocumentList lists = response.getResults();
		
/*		System.out.println("SolrDocumentList:"+ lists);*/
		
		
		TbItemChild itemChild = null;
		
		List<TbItemChild> resultList = new ArrayList<TbItemChild>();
		
		//���� Document ���
		for (SolrDocument solrDocument : lists) {
			
/*			System.out.println("-----------------------");
			System.out.println("id = " + solrDocument.getFieldValue("id"));
			System.out.println("name = " + solrDocument.getFieldValue("name"));
			*/

			
			
			itemChild = new TbItemChild();
			//id
			itemChild.setId(Long.parseLong(solrDocument.getFieldValue("id").toString()));
			
			//ȡ��������
			Map<String, List<String>> map = highlightingMap.get(solrDocument.getFieldValue("id"));
			List<String> list = map.get("item_title");
			//����и������ݣ�����ʾ���������ݣ����û������ʾ ����������
			if(list!=null&&list.size()>0){
				itemChild.setTitle(list.get(0));
			}else{
				itemChild.setTitle(solrDocument.getFieldValue("item_title").toString());
			}
			
			//����
			itemChild.setSellPoint(solrDocument.getFieldValue("item_sell_point").toString());
			//�Ӹ�
			itemChild.setPrice(Long.parseLong(solrDocument.getFieldValue("item_price").toString()));
			
			//imgaes ���飬��ֹ��ָ��
			Object image = solrDocument.getFieldValue("item_image");
			itemChild.setImages(image!=null&&!image.equals("")?image.toString().split(","):new String[1]);

			resultList.add(itemChild);
		}
		

		resultMap.put("itemList", resultList);
		long totalPages = lists.getNumFound()%rows==0?lists.getNumFound()/rows:lists.getNumFound()/rows+1;
		resultMap.put("totalPages",totalPages);

/*		response.get
		
		model.addAttribute("totalPages", arg1)*/
		
		
		return resultMap;
	}


	
	


	/* (�� Javadoc)
	* <p>Description: ������Ʒ��ͬ��solr</p>
	* @param desc
	* @param tbItem
	* @return
	* @see com.mxnavi.server.ego.search.service.TbItemService#addSolrItem(java.lang.String, com.mxnavi.server.ego.pojo.TbItem)
	*/
	@Override
	public int addSolrItem(String desc, Map tbItemMap) throws SolrServerException, IOException {
		
		
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", tbItemMap.get("id"));
		doc.addField("item_title", tbItemMap.get("title"));
		doc.addField("item_sell_point", tbItemMap.get("sellPoint"));
		doc.addField("item_price", tbItemMap.get("price"));		
		doc.addField("item_image", tbItemMap.get("image"));
		

		
		
		TbItemCat TbItemCat = tbItemCatDubboServiceImpl.selTbItemCatById(Long.parseLong(tbItemMap.get("cid").toString()));
/*		System.out.println("TbItemCat : "+ TbItemCat);*/
		doc.addField("item_category_name", TbItemCat.getName());
				
		doc.addField("item_desc", desc);
		doc.addField("item_created", new Date());
		
		//���ݸ���״̬ �ж� �Ƿ�ɹ�
		UpdateResponse response = httpSolrClient.add(doc);
		httpSolrClient.commit();
		if(response.getStatus() == 0){
			return 1;
		}
		
		return 0;
		
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
