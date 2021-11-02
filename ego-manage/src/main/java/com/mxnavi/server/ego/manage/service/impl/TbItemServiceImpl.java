package com.mxnavi.server.ego.manage.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.SolrHttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mxnavi.server.ego.commons.pojo.EasyUiDataGrid;
import com.mxnavi.server.ego.commons.util.HttpClientUtil;
import com.mxnavi.server.ego.commons.util.IDUtils;
import com.mxnavi.server.ego.commons.util.JsonUtils;
import com.mxnavi.server.ego.dubbo.service.TbItemDubboService;
import com.mxnavi.server.ego.manage.service.TbItemService;
import com.mxnavi.server.ego.pojo.TbItem;
import com.mxnavi.server.ego.pojo.TbItemDesc;
import com.mxnavi.server.ego.pojo.TbItemParamItem;
import com.mxnavi.server.ego.redis.dao.RedisDao;


@Service
public class TbItemServiceImpl implements TbItemService{

	
	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;
	
	@Value("${com.mxnavi.search.searchUrl}")
	private String url;
	
	@Resource
	private RedisDao redisDaoImpl;

	@Resource
	private HttpSolrClient solrClient;
	
	@Override
	public EasyUiDataGrid selectItemPage(int page, int rows) {
		// TODO Auto-generated method stub
		return tbItemDubboServiceImpl.selectItemPage(page, rows);
	}



	/* (�� Javadoc)
	* <p>ͨ��key �� status ���� ��Ʒ status: </p>
	* ��֧������ع�����Ȼ��ʾʧ���ˣ������е����� �Ѿ� �����
	* @param ids
	* @param status
	* @return
	* @see com.mxnavi.server.ego.manage.service.TbItemService#updateStatusByPrimarykey(java.lang.String, byte)
	*/
	@Override
	public int updateStatusByPrimarykey(String ids, byte status) throws SolrServerException, IOException {
		
		int index = 0;
		
		String[] idsChar = ids.split(",");
		TbItem tbItem = new TbItem();
		for (String id : idsChar) {
			tbItem.setId(Long.parseLong(id));
			tbItem.setStatus(status);
			index = index + tbItemDubboServiceImpl.updateStatusByPrimarykey(tbItem);	
		}
		
		if (index == idsChar.length){

			if(status == 2||status == 3){
				//���������redis�У����������
				for (String idString : idsChar) {	
					if(redisDaoImpl.exists("item:"+idString)){
						redisDaoImpl.del("item:"+idString);
					}
					
					//���solr �ĵ�
					//����Ӧ��ֻ��ego-search��Ŀ���ܲ���solr������Ӧ�� ͨ�� httpClient ���� ego-search��Ŀ�Ľӿ�
					solrClient.deleteById(idString);
					solrClient.commit();
				}

			}	

			return 1;
			
		}else{
			return 0;
		}
	}



	/* (�� Javadoc)
	* <p>Description: </p>
<p>Description:������Ʒ�������Ʒ����Ʒ��������Ʒ��������3�� </p>
	* @param tbItem
	* @param tbItemDesc
	* @return
	* @throws Exception
	* @see com.mxnavi.server.ego.manage.service.TbItemService#insTbItem(com.mxnavi.server.ego.pojo.TbItem, com.mxnavi.server.ego.pojo.TbItemDesc)
	*/
	@Override
	public int insTbItem(TbItem tbItem, String desc,String itemParams) throws Exception {
		
		Date date = new Date();
		
		tbItem.setId(IDUtils.genItemId());
		tbItem.setCreated(date);
		tbItem.setUpdated(date);
		
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(tbItem.getId());
		tbItemDesc.setCreated(date);
		tbItemDesc.setUpdated(date);
		tbItemDesc.setItemDesc(desc);
		
		TbItemParamItem tbItemParamItem = new TbItemParamItem();
		tbItemParamItem.setCreated(date);
		tbItemParamItem.setItemId(tbItem.getId());
		tbItemParamItem.setParamData(itemParams);
		tbItemParamItem.setUpdated(date);
		
/*		������Ʒʱ��ʹ�� httpClient��ͬ������ ͬ�� solr
		Map map = new HashMap<>();
		map.put("desc", desc);
		map.put("tbItem",tbItem);
		String response = HttpClientUtil.doPostJson(url, JsonUtils.objectToJson(map));
		System.out.println(response);*/
		
		//������Ʒʱ��ʹ�� httpClient��  �첽 ���� ͬ�� solr
		new Thread(){
			public void run() {
				Map map = new HashMap<>();
				map.put("desc", desc);
				map.put("tbItem",tbItem);
				HttpClientUtil.doPostJson(url, JsonUtils.objectToJson(map));
			};
		}.start();
		
		return tbItemDubboServiceImpl.insTbItem(tbItem,tbItemDesc,tbItemParamItem);

	}


	
	
	
	
	
	
}
